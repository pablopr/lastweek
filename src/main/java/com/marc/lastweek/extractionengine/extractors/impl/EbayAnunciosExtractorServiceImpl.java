/*
 * EbayExtractor.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.extractionengine.extractors.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.marc.lastweek.extractionengine.entities.EbayAnunciosAd;
import com.marc.lastweek.extractionengine.extractors.EbayAnunciosExtractorService;
import com.marc.lastweek.extractionengine.helpers.DomHelper;
import com.marc.lastweek.extractionengine.naming.UrlNaming;
import com.meterware.httpunit.HTMLElement;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.TableCell;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebImage;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

@Service
public class EbayAnunciosExtractorServiceImpl implements EbayAnunciosExtractorService {
	
	private final static Logger log = Logger.getLogger(EbayAnunciosExtractorServiceImpl.class);
	
//	@Autowired
//	private ClassifiedAdsService classifiedAdsService;
	
	public void processProvince(String provinceSuffix) throws SAXException, IOException {
		String provincePageUrl = UrlNaming.EBAY_PISOS_BASE_URL + provinceSuffix;
		log.info("Processing " + provincePageUrl);
		boolean doProcess = true;
		int pageNumber = 1;
		HttpUnitOptions.setScriptingEnabled( false );
		WebConversation webConversation = new WebConversation();
		WebResponse webResponse = webConversation.getResponse(provincePageUrl);
		while ( doProcess ) {
			WebTable[] webtables = webResponse.getTables();
			for (WebTable webtable : webtables) {
				if ( "nol".equals(webtable.getAttribute("class")) ) {
					for (int i = 0; i < webtable.getRowCount() ; i++) {
						for (int j = 0; j < webtable.getColumnCount(); j++) {
							if ( j == 1 ) {
								TableCell tableCell= webtable.getTableCell(i,j);
								WebLink[] weblinks = tableCell.getLinks();								
								EbayAnunciosAd ebayAd = processDetailPage( UrlNaming.EBAY_ANUNCIOS_BASE_URL + weblinks[0].getURLString() );
//								this.classifiedAdsService.createExternalClassfiedAd(EbayPisosAdapter.adapt(ebayAd));
								log.info(ebayAd);
								log.info("\n");
							} 
						}
					}
				}
			} 
			WebLink nextLink = webResponse.getLinkWith("Siguiente");
			if ( nextLink != null ) {
				nextLink.click();
				webResponse = webConversation.getCurrentPage();
				pageNumber++;
			} else {
				doProcess = false;
			}
		}
		log.info("End processing " + provincePageUrl + "at page" + pageNumber);
	}
	
	private EbayAnunciosAd processDetailPage(String detailPageUrl) throws IOException, SAXException{
		
		String title = null;
		String description = null;
		String place = null;
		String date = null;
		String price = null;
		String category = null;
		String imgUrl = "";
		
		log.info("Processing " + detailPageUrl);
		HttpUnitOptions.setScriptingEnabled( false );
		WebConversation webConversation = new WebConversation();
		WebResponse webResponse = webConversation.getResponse(detailPageUrl);
		
		HTMLElement[] possibleTitles = DomHelper.getElementsWithClassName(webResponse, "tit-head");
		title = possibleTitles[0].getText();
		
		HTMLElement descriptionDiv = webResponse.getElementWithID("section1");
		description = descriptionDiv.getText();
		
		WebImage[] images = webResponse.getImages();
		if ( images != null ) {
			for ( WebImage image: images ) {
				if ( image.getSource().contains("http://img.ebayanuncios.es") ) {
						imgUrl = image.getSource();
						break;
				}				
			}
		}
		
		WebTable webTable = webResponse.getTableStartingWithPrefix("Localidad");
		if ( webTable != null) {
			place = webTable.getTableCell(0,1).getText();
			price = webTable.getTableCell(1,1).getText();
			date = webTable.getTableCell(2,1).getText();
			category = webTable.getTableCell(3,1).getText();
		}
		return new EbayAnunciosAd(title,description, place, date, price, category, detailPageUrl,imgUrl);
	}
	
}

/*
 * EbayPisosExtractor.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.extractionengine.extractors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.marc.lastweek.commons.exceptions.InternalErrorException;
import com.marc.lastweek.extractionengine.entities.EbayPisosAd;
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

public class EbayPisosProvinceExtractor {

    private final static Logger log = Logger.getLogger(EbayPisosProvinceExtractor.class);
    
    private List<String> adsUrl;
    private List<EbayPisosAd> extractedAds;
    
    private String provinceUrlSuffix;

    private boolean doProcess;
    
    private WebConversation AdListWebConversation;
    private WebResponse AdListWebResponse;
    
    private WebConversation adDetailWebConversation;
    private WebResponse adDetailWebResponse;
    private WebTable detailTable;
    
    public EbayPisosProvinceExtractor(String provinceUrlSuffix) {
        this.adsUrl = new ArrayList<String>();
        this.extractedAds = new ArrayList<EbayPisosAd>();
        this.doProcess = true;          
        this.provinceUrlSuffix = provinceUrlSuffix;                 
    }
    
    public void doExtraction() {
        try {             
            startWebConversation();            
            processAdListPages();
            processDetailPages();
        } catch (SAXException e) {
            log.error("It is possible that the html has changed");
            log.error(e);
            throw new InternalErrorException(e);
        }
    }
    
    private void startWebConversation() {
        try {
            HttpUnitOptions.setScriptingEnabled( false );     
            String provincePageUrl = UrlNaming.EBAY_PISOS_BASE_URL + this.provinceUrlSuffix;
            this.AdListWebConversation = new WebConversation();    
            this.AdListWebResponse = this.AdListWebConversation.getResponse(provincePageUrl);
        } catch (Exception e) {
            this.doProcess = false;
            log.error(e);
        }
    }
        
    private void processAdListPages() throws SAXException  {        
        while ( this.doProcess ) {
            log.info("Processing ad list page " + this.AdListWebResponse.getURL());
            getCurrentPageAdsLinks();
            navigateToNextPage();            
        }
    }
    
    private void getCurrentPageAdsLinks() throws SAXException {              
        for (WebTable webTable: this.getCurrentPageWebTablesWithAds()) {
            this.adsUrl.add(getAdLink(webTable));
        }        
    }
    
    private List<WebTable> getCurrentPageWebTablesWithAds() throws SAXException {        
        List<WebTable> tablesWithAds = new ArrayList<WebTable>();
        WebTable[] allWebTables = this.AdListWebResponse.getTables();        
        for ( WebTable webTable: allWebTables ) {
            if ( "nol".equals(webTable.getAttribute("class"))) {
                tablesWithAds.add(webTable);
            }
        }        
        return tablesWithAds;        
    }
    
    private String getAdLink(WebTable webTableWithAd) {
        String result = "";
        for (int i = 0; i < webTableWithAd.getRowCount() ; i++) {
            for (int j = 0; j < webTableWithAd.getColumnCount(); j++) {
                if ( j == 1) {
                    TableCell tableCell= webTableWithAd.getTableCell(i,j);
                    WebLink[] weblinks = tableCell.getLinks();
                    result = UrlNaming.EBAY_PISOS_BASE_URL + weblinks[0].getURLString();                                                                                                             
                } 
            }
        }
        return result;
    }
    
    private void navigateToNextPage() {
        try {
            WebLink nextLink = this.AdListWebResponse.getLinkWith("Siguiente");
            if ( nextLink != null ) {
                nextLink.click();
                this.AdListWebResponse = this.AdListWebConversation.getCurrentPage();
            } else {
                this.doProcess = false;
            } 
        } catch (Exception e) {
            this.doProcess = false;
            log.error(e);
        }
    }
    
    private void processDetailPages() {
        for ( String detailPageUrl: this.adsUrl ) {            
            processDetailPage(detailPageUrl);
        }       
    }
    
    private void processDetailPage(String detailPageUrl) {
        log.info("Processing " + detailPageUrl);    
        try {
            this.navigateToDetailPage(detailPageUrl);
            this.getDetailTable();
            this.extractedAds.add(new EbayPisosAd(this.getTitleFromDetailPage(),
                                                 this.getDescriptionFromDetailPage(),
                                                 this.getPlaceFromDetailTable(),
                                                 this.getDateFromDetailTable(),
                                                 this.getPriceFromDetailTable(),
                                                 this.getSubCategoryFromDetailTable(),
                                                 this.getCategoryFromDetailTable(), detailPageUrl,this.getImgUrlFromDetailPage()));
            this.cleanUpDetailProcessingObjects();
        } catch (Exception e) {
            log.error("Error processing " + detailPageUrl);
            log.error(e);
        }
    }
    
    private void navigateToDetailPage(String detailPageUrl) throws IOException, SAXException {
        HttpUnitOptions.setScriptingEnabled( false );
        this.adDetailWebConversation = new WebConversation();
        this.adDetailWebResponse = this.adDetailWebConversation.getResponse(detailPageUrl);
    }
    
    private String getTitleFromDetailPage() throws SAXException {
        String title = "";
        HTMLElement[] possibleTitles = DomHelper.getElementsWithClassName(this.adDetailWebResponse, "tit-head");
        if ( possibleTitles.length > 0 ) {
            title = possibleTitles[0].getText();
        }
        return title;
    }
    
    private String getDescriptionFromDetailPage() throws SAXException {
        String description = "";
        HTMLElement descriptionDiv = this.adDetailWebResponse.getElementWithID("section1");
        description = StringUtils.abbreviate(descriptionDiv.getText(),450);
        return description;
    }
    
    private String getImgUrlFromDetailPage() throws SAXException {
        String imgUrl = "";
        WebImage[] images = this.adDetailWebResponse.getImages();
        if ( images != null ) {
            for ( WebImage image: images ) {
                if ( image.getSource().contains("http://img.ebaypisos.es") ) {
                        imgUrl = image.getSource();
                        break;
                }               
            }
        }
        return imgUrl;
    }
    
    private void getDetailTable() throws SAXException {
        this.detailTable = this.adDetailWebResponse.getTableStartingWithPrefix("Localidad");
    }
    
    private String getPlaceFromDetailTable() {        
        return this.detailTable.getTableCell(0,1).getText();                  
    }
    
    private String getPriceFromDetailTable() {
        return this.detailTable.getTableCell(1,1).getText();
    }
    
    private String getDateFromDetailTable() {       
        return this.detailTable.getTableCell(2,1).getText();
    }
    
    private String getSubCategoryFromDetailTable() {
        return this.detailTable.getTableCell(3,1).getText();

    }
    
    private String getCategoryFromDetailTable() {
        return this.detailTable.getTableCell(4,1).getText();
    }
    
    private void cleanUpDetailProcessingObjects() {
        this.adDetailWebConversation = null;
        this.adDetailWebResponse = null;
        this.detailTable = null;
    }
        
    public List<EbayPisosAd> getExtractedAds() {
        return this.extractedAds;
    }
    
}

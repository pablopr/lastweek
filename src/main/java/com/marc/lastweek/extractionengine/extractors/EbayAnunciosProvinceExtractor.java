/*
 * EbayAnunciosExtractor.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.extractionengine.extractors;

import org.apache.commons.lang.StringUtils;
import org.xml.sax.SAXException;

import com.marc.lastweek.extractionengine.helpers.DomHelper;
import com.marc.lastweek.extractionengine.naming.UrlNaming;
import com.meterware.httpunit.HTMLElement;
import com.meterware.httpunit.WebImage;


public class EbayAnunciosProvinceExtractor extends EbayProvinceExtractor {
   
       
    
    public EbayAnunciosProvinceExtractor(String provinceUrlSuffix) {
        super(provinceUrlSuffix);
        this.setBaseUrl(UrlNaming.EBAY_ANUNCIOS_BASE_URL);
    }   
    
    
    @Override
    protected String getTitleFromDetailPage() throws SAXException {
        String title = "";
        HTMLElement[] possibleTitles = DomHelper.getElementsWithClassName(this.getAdDetailWebResponse(), "tit-head");
        if ( possibleTitles.length > 0 ) {
            title = possibleTitles[0].getText();
        }
        return title;
    }
    
    @Override
    protected String getDescriptionFromDetailPage() throws SAXException {
        String description = "";
        HTMLElement descriptionDiv = this.getAdDetailWebResponse().getElementWithID("section1");
        description = StringUtils.abbreviate(descriptionDiv.getText(),450);
        return description;
    }
    
    @Override
    protected String getImgUrlFromDetailPage() throws SAXException {
        String imgUrl = "";
        WebImage[] images = this.getAdDetailWebResponse().getImages();
        if ( images != null ) {
            for ( WebImage image: images ) {
                if ( image.getSource().contains("http://img.ebayanuncios.es") ) {
                        imgUrl = image.getSource();
                        break;
                }               
            }
        }
        return imgUrl;
    }
    
    @Override
    protected String getPlaceFromDetailTable() {        
        return this.getDetailTable().getTableCell(0,1).getText();                  
    }
    
    @Override
    protected String getPriceFromDetailTable() {
        return this.getDetailTable().getTableCell(1,1).getText();
    }
    
    @Override
    protected String getDateFromDetailTable() {       
        return this.getDetailTable().getTableCell(2,1).getText();
    }
    
    @Override
    protected String getCategoryFromDetailTable() {
        return this.getDetailTable().getTableCell(3,1).getText();
    }
    
}

/*
 * EbayProvinceExtractor.java
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

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.marc.lastweek.commons.exceptions.InternalErrorException;
import com.marc.lastweek.extractionengine.entities.EbayAd;
import com.marc.lastweek.extractionengine.naming.UrlNaming;
import com.meterware.httpunit.ClientProperties;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.TableCell;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

public abstract class EbayProvinceExtractor {
 private final static Logger log = Logger.getLogger(EbayPisosProvinceExtractor.class);
     
    private int pageCounter = 0;
    
    private List<String> adsUrl;
    private List<EbayAd> extractedAds;
    private List<String> alreadyExtractedAdsUrl;
    
    private String provinceUrlSuffix;
    
    private boolean doProcess;
    
    private WebConversation AdListWebConversation;
    private WebResponse AdListWebResponse;
    
    private WebConversation adDetailWebConversation;
    private WebResponse adDetailWebResponse;
    private WebTable detailTable;
    
    private String baseUrl;

    public EbayProvinceExtractor(String provinceUrlSuffix, List<String> alreadyExtractedAds) {
        this.adsUrl = new ArrayList<String>();
        this.extractedAds = new ArrayList<EbayAd>();
        this.doProcess = true;          
        this.provinceUrlSuffix = provinceUrlSuffix;     
        this.alreadyExtractedAdsUrl = alreadyExtractedAds;
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
            log.info("starting web conversation");
            Validate.notEmpty(this.baseUrl, "baseUrl has to be set");
            String provincePageUrl = this.baseUrl + this.provinceUrlSuffix;            
            ClientProperties.getDefaultProperties().setIframeSupported( false );            
            HttpUnitOptions.setScriptingEnabled( false );
            HttpUnitOptions.setExceptionsThrownOnErrorStatus( false );
            HttpUnitOptions.setExceptionsThrownOnScriptError( false );           
            this.AdListWebConversation = new WebConversation();                     
            log.info("about to access " + provincePageUrl);
            this.AdListWebResponse = this.AdListWebConversation.getResponse(provincePageUrl);            
            log.info("web conversation started");
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
            String adUrl = getAdLink(webTable);
            if ( this.alreadyExtractedAdsUrl.contains(adUrl) ) {
                this.doProcess = false;
            } else {                              
                this.adsUrl.add(adUrl);
            }
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
        log.info("getAdLink");
        String result = "";
        for (int i = 0; i < webTableWithAd.getRowCount() ; i++) {
            for (int j = 0; j < webTableWithAd.getColumnCount(); j++) {
                if ( j == 1) {
                    TableCell tableCell= webTableWithAd.getTableCell(i,j);
                    WebLink[] weblinks = tableCell.getLinks();
                    result = UrlNaming.EBAY_PISOS_BASE_URL + weblinks[0].getURLString();
                    log.info(result);
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
                this.pageCounter++;
                if ( this.pageCounter == 3 ) this.doProcess = false; 
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
            try {
                this.navigateToDetailPage(detailPageUrl);
                this.processDetailPage(detailPageUrl);
                this.cleanUpDetailProcessingObjects();       
            } catch (Exception e) {
                log.error("Error processing page :" + detailPageUrl);
            }
        }       
    }
    
    private void navigateToDetailPage(String detailPageUrl) throws IOException, SAXException {
        HttpUnitOptions.setScriptingEnabled( false );
        this.adDetailWebConversation = new WebConversation();
        this.adDetailWebResponse = this.adDetailWebConversation.getResponse(detailPageUrl);
        this.detailTable = this.adDetailWebResponse.getTableStartingWithPrefix("Localidad");
    }
    
    private void cleanUpDetailProcessingObjects() {
        this.adDetailWebConversation = null;
        this.adDetailWebResponse = null;
        this.detailTable = null;
    }
    
    private void processDetailPage(String detailPageUrl) throws Exception {
        log.info("Processing: " + detailPageUrl);
        this.addEbayAd(new EbayAd(this.getTitleFromDetailPage(),
                                             this.getDescriptionFromDetailPage(),
                                             this.getPlaceFromDetailTable(),
                                             this.getDateFromDetailTable(),
                                             this.getPriceFromDetailTable(),
                                             this.getSubCategoryFromDetailTable(),
                                             this.getCategoryFromDetailTable(), detailPageUrl,this.getImgUrlFromDetailPage()));

    }    
    
    
    @SuppressWarnings("unused")
    protected String getTitleFromDetailPage() throws SAXException {
        return "";
    }
    
    @SuppressWarnings("unused")
    protected String getDescriptionFromDetailPage() throws SAXException {
        return "";
    }
    
    @SuppressWarnings("unused")
    protected String getImgUrlFromDetailPage() throws SAXException{
        return "";
    }
    
    protected String getPlaceFromDetailTable() {
        return "";
    }
    
    protected String getPriceFromDetailTable() {
        return "";
    }
    
    protected String getDateFromDetailTable() {
        return "";
    }

    protected String getSubCategoryFromDetailTable() {
        return "";
    }
    
    protected String getCategoryFromDetailTable() {
        return "";
    }
     
    public void addEbayAd(EbayAd ebayAd) {
        this.extractedAds.add(ebayAd);
    }
    
    public WebTable getDetailTable() {
        return this.detailTable;
    }
    
    public WebResponse getAdDetailWebResponse() {
        return this.adDetailWebResponse;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    public List<EbayAd> getExtractedAds() {
        return this.extractedAds;       
    }
    
    
    
    
}

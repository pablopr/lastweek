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
package com.marc.lastweek.extractionengine.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import loc.marc.commons.business.services.general.GeneralService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.services.classifiedads.ClassifiedAdsService;
import com.marc.lastweek.extractionengine.adapters.EbayAdAdapter;
import com.marc.lastweek.extractionengine.entities.EbayAd;
import com.marc.lastweek.extractionengine.services.EbayExtractorService;

@Service
public class EbayExtractorServiceImpl implements EbayExtractorService {
	
	@Autowired
	private ClassifiedAdsService classifiedAdsService;
	
	@Autowired 
	private GeneralService generalService;
	
	@Transactional
	public void importEbayPisosAds(List<EbayAd> ebayAds) {	 
	    for (EbayAd ebayAd: ebayAds) {	 
	        if ( !existsExternalClassifiedAd(ebayAd.getUrl()) ) {
	            this.classifiedAdsService.createExternalClassfiedAd(EbayAdAdapter.adapt(ebayAd));
	        }
	    }
	    	
	}
	
	private boolean existsExternalClassifiedAd(String sourceUrl) {
	    boolean existsExternalClassifiedAd = false;
	    Map<String,Object> queryParameters = new HashMap<String,Object>();
        queryParameters.put("sourceURL", sourceUrl.trim());
        ClassifiedAd classifiedAd = this.generalService.queryForObject(ClassifiedAd.class, "getClassifiedAdByUrl", queryParameters);
        if ( classifiedAd != null ) {
            existsExternalClassifiedAd = true;
        }
        return existsExternalClassifiedAd;
	}

    public void importEbayAnunciosAds(List<EbayAd> ebayAds) {
        // TODO implement
        
    }
	
	
}

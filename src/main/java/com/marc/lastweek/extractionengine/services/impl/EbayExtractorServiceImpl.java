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

import java.util.List;

import loc.marc.commons.business.entities.general.GeneralRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marc.lastweek.business.services.classifiedads.ClassifiedAdsService;
import com.marc.lastweek.extractionengine.adapters.EbayAdAdapter;
import com.marc.lastweek.extractionengine.entities.EbayAd;
import com.marc.lastweek.extractionengine.services.EbayExtractorService;

@Service
public class EbayExtractorServiceImpl implements EbayExtractorService {
	
	@Autowired
	private ClassifiedAdsService classifiedAdsService;
	
    @Autowired
    private GeneralRepository generalRepository;
	
	@Transactional
	public void importEbayPisosAds(List<EbayAd> ebayAds) {	 
	    for (EbayAd ebayAd: ebayAds) {	 
	        this.classifiedAdsService.createExternalClassfiedAd(EbayAdAdapter.adapt(ebayAd));
	    }
	    	
	}
	
    public void importEbayAnunciosAds(List<EbayAd> ebayAds) {
        // TODO implement        
    }	
	
    @Transactional(readOnly=true)
    public List<String> getExternalsAdsUrl() {
      return this.generalRepository.queryForList(String.class, "getExternalsAdsUrl", null);
    }
	
}

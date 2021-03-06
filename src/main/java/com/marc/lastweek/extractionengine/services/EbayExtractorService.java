/*
 * EbayAnunciosExtractorService.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.extractionengine.services;

import java.util.List;

import com.marc.lastweek.extractionengine.entities.EbayAd;

public interface EbayExtractorService {
    
	public void importEbayPisosAds(List<EbayAd> ebayPisosAds);
	
	public void importEbayAnunciosAds(List<EbayAd> ebayPisosAds);
	
	public List<String> getExternalsAdsUrl();

}

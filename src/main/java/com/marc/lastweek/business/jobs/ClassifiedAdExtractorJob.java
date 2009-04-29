/*
 * ClassifiedAdExtractorJob.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.jobs;

import java.util.ArrayList;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.marc.lastweek.extractionengine.extractors.EbayAnunciosProvinceExtractor;
import com.marc.lastweek.extractionengine.extractors.EbayPisosProvinceExtractor;
import com.marc.lastweek.extractionengine.naming.UrlNaming;
import com.marc.lastweek.extractionengine.services.EbayExtractorService;

public class ClassifiedAdExtractorJob extends ApplicationContextAwareQuartzJobBean {	
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
			
			ApplicationContext applicationContext = this.getApplicationContext(context);
			EbayExtractorService EbayPisosExtractorService = (EbayExtractorService) applicationContext.getBean("ebayExtractorServiceImpl");
			
			for (String province : UrlNaming.EBAY_PROVINCE_SUFIXES) {
			    log.info("Extracting :" + province);
			    // TODO pass the already extracted ads as a parameter
			    EbayPisosProvinceExtractor ebayPisosProvinceExtractor = new EbayPisosProvinceExtractor(province, new ArrayList<String>());
			    ebayPisosProvinceExtractor.doExtraction();
			    EbayPisosExtractorService.importEbayPisosAds(ebayPisosProvinceExtractor.getExtractedAds());    
			}
			
			for (String province : UrlNaming.EBAY_PROVINCE_SUFIXES) {
                log.info("Extracting :" + province);
                // TODO pass the already extracted ads as a parameter
                EbayAnunciosProvinceExtractor ebayAnunciosProvinceExtractor = new EbayAnunciosProvinceExtractor(province, new ArrayList<String>());
                ebayAnunciosProvinceExtractor.doExtraction();
                EbayPisosExtractorService.importEbayPisosAds(ebayAnunciosProvinceExtractor.getExtractedAds());    
            }           
			
	}
	
	
	

}

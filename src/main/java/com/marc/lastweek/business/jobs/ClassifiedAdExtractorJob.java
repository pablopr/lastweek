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

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.marc.lastweek.business.services.classifiedads.ClassifiedAdsService;

public class ClassifiedAdExtractorJob extends ApplicationContextAwareQuartzJobBean {	
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
			
			ApplicationContext applicationContext = this.getApplicationContext(context);
			
			ClassifiedAdsService classifiedAdsService = (ClassifiedAdsService) applicationContext.getBean("classifiedAdsServiceImpl");
			
			if ( classifiedAdsService != null ) {
				log.info("Task finished normally");
			} else {
				log.info("Task finished with errors");
			}
			
		} catch (Exception e) {
			log.info("Task finished with errors" + e);
		}
		
	}
	
	
	

}

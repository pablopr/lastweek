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

public class ClassifiedAdExtractorJob extends ApplicationContextAwareQuartzJobBean {	
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
			
			ApplicationContext applicationContext = this.getApplicationContext(context);
//			
//			ClassifiedAdsService classifiedAdsService = (ClassifiedAdsService) applicationContext.getBean("classifiedAdsServiceImpl");
//										
//			WebConversation wc = new WebConversation();
//		    WebRequest     req = new GetMethodWebRequest( "http://www.elpais.com" );
//		    WebResponse   resp = wc.getResponse( req );
//		    log.info(resp.getText());
			
		} catch (Exception e) {
			log.info("Task finished with errors" + e);
		}
		
	}
	
	
	

}

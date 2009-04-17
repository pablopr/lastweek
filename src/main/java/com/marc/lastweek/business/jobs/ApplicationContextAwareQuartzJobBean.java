/*
 * ApplicationContextAwareQuartzJobBean.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.jobs;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public abstract class ApplicationContextAwareQuartzJobBean extends QuartzJobBean{

    private static final String APPLICATION_CONTEXT_KEY = "applicationContext";
    protected static final Logger log = Logger.getLogger(ApplicationContextAwareQuartzJobBean.class);
	
	protected ApplicationContext getApplicationContext(JobExecutionContext context) throws SchedulerException  { 
		ApplicationContext appCtx = null;
        appCtx = (ApplicationContext) context.getScheduler().getContext().get(APPLICATION_CONTEXT_KEY);
        if ( appCtx == null ) {
            throw new JobExecutionException(
                    "No application context available in scheduler context for key \"" + APPLICATION_CONTEXT_KEY + "\"");
        }
        return appCtx;

	}
}

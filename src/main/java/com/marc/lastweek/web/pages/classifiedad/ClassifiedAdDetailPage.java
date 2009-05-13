/*
 * AdvertisementDetailPage.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.classifiedad;

import org.apache.wicket.PageParameters;

import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.components.classifiedaddetails.ClassifiedAdDetailPanel;
import com.marc.lastweek.web.naming.PageParametersNaming;
import com.marc.lastweek.web.pages.StandardPage;
import com.marc.lastweek.web.util.ResourceUtils;

public class ClassifiedAdDetailPage extends StandardPage {

	public ClassifiedAdDetailPage(PageParameters parameters) {
		super(parameters);
		
		/* TODO: Use this parameters to create current navigation info and link panels */
		final Long classifiedAdId = Long.valueOf(parameters
			.getLong(PageParametersNaming.PARAM_NAME_CLASSIFIED_AD_ID));
		
		final String classifiedAdActivateIdHash = parameters
			.getString(PageParametersNaming.PARAM_NAME_CLASSIFIED_AD_ACTIVATE);
		
		if (classifiedAdActivateIdHash != null){
			
			LastweekApplication.get().getClassifiedAdsService().activateClassifiedAd(classifiedAdId, classifiedAdActivateIdHash);
			this.getSession().info(ResourceUtils.getResourceString("ad.activation.done", ClassifiedAdDetailPage.this));
		}
		
		final String classifiedAdRefreshingIdHash = 
			parameters.getString(PageParametersNaming.PARAM_NAME_CLASSIFIED_AD_REFRESH);
		
		if (classifiedAdRefreshingIdHash != null) {
			LastweekApplication.get().getClassifiedAdsService().refreshClassifiedAd(classifiedAdId, classifiedAdRefreshingIdHash);
			this.getSession().info(ResourceUtils.getResourceString("ad.refreshing.done", ClassifiedAdDetailPage.this));
		}
		
		this.add(new ClassifiedAdDetailPanel("classifiedAdDetailPanel", classifiedAdId));
	}
}

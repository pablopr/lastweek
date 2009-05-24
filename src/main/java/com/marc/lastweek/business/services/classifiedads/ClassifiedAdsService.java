/*
 * ClasifiedAdsService.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.services.classifiedads;

import java.util.Calendar;
import java.util.List;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.views.classifiedad.FilterParameters;
import com.marc.lastweek.business.views.classifiedad.NewExternalClassifiedAdTO;
import com.marc.lastweek.business.views.commons.NewClassifiedAdAndUserDataTO;

public interface ClassifiedAdsService {

	public List<ClassifiedAd> findClassifiedAdsByFilterParameters(FilterParameters parameters, int start, 
			int count, Calendar date);
	
	public Integer countClassifiedAdsByFilterParameters(FilterParameters parameters, Calendar date);
	
	public ClassifiedAd createClassifiedAd(NewClassifiedAdAndUserDataTO newClassifiedAdTO);
	
	public void createExternalClassfiedAd(NewExternalClassifiedAdTO newExternalClassifiedAdTO);
	
	public ClassifiedAd activateClassifiedAd(final Long classifiedAdId, final String classifiedAdIdHash);
	
	public ClassifiedAd refreshClassifiedAd(final Long classifiedAdId, final String classifiedAdIdHash);
	
	public ClassifiedAd markClassifiedAdAsExpiringTomorrow(final Long classifiedAdId);
	
	public ClassifiedAd expireClassifiedAd(final Long classifiedAdId);
}

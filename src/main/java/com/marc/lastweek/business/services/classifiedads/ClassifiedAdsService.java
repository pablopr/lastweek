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

import java.util.List;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.views.classifiedad.FilterParameters;

public interface ClassifiedAdsService {

	public List<ClassifiedAd> filterClassifiedAds(FilterParameters parameters, int start, int count);
	
	public Integer countFilterAdvertisements(FilterParameters parameters);
}

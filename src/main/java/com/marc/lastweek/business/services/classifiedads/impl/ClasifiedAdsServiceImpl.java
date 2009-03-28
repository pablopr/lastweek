/*
 * ClasifiedAdsServiceImpl.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.services.classifiedads.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.entities.classifiedad.repository.ClassifiedAdRepository;
import com.marc.lastweek.business.services.classifiedads.ClasifiedAdsService;
import com.marc.lastweek.business.views.aaa.FilterParameters;

@Service
public class ClasifiedAdsServiceImpl implements ClasifiedAdsService {
	
	@Autowired
	ClassifiedAdRepository classifiedAdRespository;

	@Override
	public List<ClassifiedAd> filterClassifiedAds(FilterParameters parameters, int start, int count) {
		return classifiedAdRespository.filterAdvertisements(parameters, start, count);
	}

	
}

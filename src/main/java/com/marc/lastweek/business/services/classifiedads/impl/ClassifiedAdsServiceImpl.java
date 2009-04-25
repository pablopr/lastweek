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

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import loc.marc.commons.business.entities.general.GeneralRepository;
import loc.marc.commons.business.services.general.GeneralService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.entities.classifiedad.repository.ClassifiedAdRepository;
import com.marc.lastweek.business.entities.userdata.UserData;
import com.marc.lastweek.business.services.classifiedads.ClassifiedAdsService;
import com.marc.lastweek.business.views.classifiedad.FilterParameters;
import com.marc.lastweek.business.views.classifiedad.NewClassifiedAdTO;
import com.marc.lastweek.business.views.classifiedad.NewExternalClassifiedAdTO;
import com.marc.lastweek.business.views.commons.NewClassifiedAdAndUserDataTO;
import com.marc.lastweek.business.views.userdata.NewUserDataTO;

@Service
public class ClassifiedAdsServiceImpl implements ClassifiedAdsService {
	
	@Autowired
	private ClassifiedAdRepository classifiedAdRespository;
	
	@Autowired
	private GeneralService generalService;
	
	@Autowired
	private GeneralRepository generalRepository;

	// TODO: add one week before parameter (actually it must come from the controller)
	public List<ClassifiedAd> findClassifiedAdsByFilterParameters(FilterParameters parameters, int start, int count, Calendar date) {
	    if (parameters.getSearchString()!=null) {
	        return this.classifiedAdRespository.indexBasedSearch(parameters, date ,start, count);
	    } 
	    return this.classifiedAdRespository.basicSearch(parameters, date, start, count);

	}

	public Integer  countClassifiedAdsByFilterParameters(FilterParameters parameters, Calendar date) {
	    if (parameters.getSearchString()!=null) {
	        return this.classifiedAdRespository.indexBasedSearchCountResults(parameters, date);
	    }
	    return this.classifiedAdRespository.basicSearchCountResults(parameters, date);
	}
	
	@Transactional
	public void createClassifiedAd(final NewClassifiedAdAndUserDataTO newClassifiedAdAndUserDataTO){
		
		HashMap<String, Object> userDataParameters = new HashMap<String,Object>();
		UserData userData = null;
		
		if ( !StringUtils.isEmpty(newClassifiedAdAndUserDataTO.getEmail()) ) {
			userDataParameters.put("email",newClassifiedAdAndUserDataTO.getEmail());
			userData = this.generalRepository.queryForObject(UserData.class, "findUserDataByEmail", userDataParameters); 
		}
		
		if ( userData == null && !StringUtils.isEmpty(newClassifiedAdAndUserDataTO.getPhone()) ) {
			userDataParameters.put("phone",newClassifiedAdAndUserDataTO.getPhone());
			userData = this.generalRepository.queryForObject(UserData.class, "findUserDataByPhone", userDataParameters); 
		} 
		
		if ( userData == null ) {
			NewUserDataTO userDataTO = new NewUserDataTO(newClassifiedAdAndUserDataTO);
			userDataTO.setState(Integer.valueOf(UserData.STATE_ACTIVE));
			userData = this.generalService.add(UserData.class, new NewUserDataTO(newClassifiedAdAndUserDataTO));
		
		}
				
		
		NewClassifiedAdTO newClassifiedAdTO = new NewClassifiedAdTO(newClassifiedAdAndUserDataTO);
		newClassifiedAdTO.setUserDataId(userData.getId());
		newClassifiedAdTO.setState(Integer.valueOf(ClassifiedAd.STATE_ACTIVE));
		newClassifiedAdTO.setSource(Integer.valueOf(ClassifiedAd.SOURCE_OUR));
		newClassifiedAdTO.setFlag(Integer.valueOf(0));
		newClassifiedAdTO.setHashCode(newClassifiedAdAndUserDataTO.getTemporalFolder().getName());
		
		this.generalService.add(ClassifiedAd.class,newClassifiedAdTO);
	}

	public void createExternalClassfiedAd(NewExternalClassifiedAdTO newExternalClassifiedAdTO) {
		this.generalService.add(ClassifiedAd.class,newExternalClassifiedAdTO);
	}
	
}

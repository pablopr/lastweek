/*
 * MailService.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.services.mail;

import java.util.List;

import loc.marc.commons.business.services.general.GeneralService;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;


public interface MailService {

	public void sendFavoritesMail(String address, List<Long> favoritesList);
	
	public void sendActivationMail(ClassifiedAd classifiedAd);
	
	public void sendContactMail(String senderName, String senderEmail, String text, Long classifiedAdId);
	
	public void sendRefreshMail(Long classifiedAdId, GeneralService generalService);
	
	public void sendExpiredMail(Long classifiedAdId, GeneralService generalService);
}

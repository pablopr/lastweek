/*
 * MailServerImpl.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.services.mail.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.javamail.JavaMailSender;

import com.marc.lastweek.business.services.mail.MailService;

public class MailServiceImpl implements MailService {

	private JavaMailSender javaMailSender = null;

	private VelocityEngine velocityEngine = null;

	private Map<String, String> velocityTemplates = new HashMap<String, String>();    

	private String from;        
	

	public void sendFavoritesMail(String addresses, List<Long> favoritesList) {
		// TODO Auto-generated method stub

	}

	@Required
	public void setFrom(String from) {
		this.from = from;
	}    

	@Required
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Required
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	@Required
	public void setVelocityTemplates(Map<String, String> velocityTemplates) {
		this.velocityTemplates = velocityTemplates;
	}


}

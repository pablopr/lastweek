/*
 * FacebookApplication.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.facebook;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequestCycleProcessor;
import org.apache.wicket.protocol.http.request.CryptedUrlWebRequestCodingStrategy;
import org.apache.wicket.protocol.http.request.WebRequestCodingStrategy;
import org.apache.wicket.request.IRequestCodingStrategy;
import org.apache.wicket.request.IRequestCycleProcessor;

public abstract class FacebookApplication extends WebApplication {

	private String facebookApiKey;
	
	@Override
	protected void init() {
		super.init();
		mountBookmarkablePage("/facebookconnect", CrossDomainReceiverPage.class);
	}

	/*
	 * This enables the encryption of non-prettified URLs
	 */
	@Override
	protected IRequestCycleProcessor newRequestCycleProcessor() {

		return new WebRequestCycleProcessor() {
			@Override
			protected IRequestCodingStrategy newRequestCodingStrategy() {
				return new CryptedUrlWebRequestCodingStrategy(new WebRequestCodingStrategy());
			}
		};
	}  
	
	public String getFacebookApiKey() {
		return facebookApiKey;
	}
	
	public void setFacebookApiKey(String facebookApiKey) {
		this.facebookApiKey = facebookApiKey;
	}

}

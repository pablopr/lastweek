/*
 * FacebookAwarePage.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.facebook;

import javax.servlet.http.Cookie;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;

import com.marc.lastweek.web.components.facebook.util.FacebookInitScript;

public class FacebookAwarePage extends WebPage {

	private static final String COOKIE_USER = "_user";
	private static final String COOKIE_SESSION_EXPIRES = "_expires";
	private static final String COOKIE_SESSION_KEY = "_session_key";
	private static final String COOKIE_SESSION_SECRET = "_ss";
	
	public FacebookAwarePage() {
		this(null);
	}

	public FacebookAwarePage(PageParameters parameters) {
		super(parameters);
		
		this.add(new FacebookInitScript("facebookInitScript", getFacebookApiKey()));
	}

	public boolean isFacebookConnected() {
		String facebookUser = getFacebookUserCookie();
		if (facebookUser!=null) {
			return validateSignature();
		}
		return false;
	}
	
	public String getFacebookApiKey() {
		return  ((FacebookApplication)this.getApplication()).getFacebookApiKey();
	}
	
	public String getFacebookUserCookie() {
		Cookie cookie =  getWebRequestCycle().getWebRequest().getCookie(getFacebookApiKey()+COOKIE_USER);
		if (cookie!=null) {
			return cookie.getValue();
		}
		return null;
	}
	
	public String getFacebookSessionExpires() {
		Cookie cookie = getWebRequestCycle().getWebRequest().getCookie(getFacebookApiKey()+COOKIE_SESSION_EXPIRES);
		if (cookie!=null) {
			return cookie.getValue();
		}
		return null;
	}
	
	public String getFacebookSessionKeyCookie() {
		Cookie cookie =  getWebRequestCycle().getWebRequest().getCookie(getFacebookApiKey()+COOKIE_SESSION_KEY);
		if (cookie!=null) {
			return cookie.getValue();
		}
		return null;
	}
	
	public String getFacebookSessionSecretCookie() {
		Cookie cookie =  getWebRequestCycle().getWebRequest().getCookie(getFacebookApiKey()+COOKIE_SESSION_SECRET);
		if (cookie!=null) {
			return cookie.getValue();
		}
		return null;
	}
	
	public String getFacebookSignatureCookie() {
		Cookie cookie =  getWebRequestCycle().getWebRequest().getCookie(getFacebookApiKey());
		if (cookie!=null) {
			return cookie.getValue();
		}
		return null;
	}
	
	public boolean validateSignature() {
		// TODO: check signature is valid
		return true;
	}
	
}

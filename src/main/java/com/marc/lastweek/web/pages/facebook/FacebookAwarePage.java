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

public class FacebookAwarePage extends WebPage {

	public FacebookAwarePage() {
		this(null);
	}

	public FacebookAwarePage(PageParameters parameters) {
		super(parameters);
	}

	public boolean isFacebookConnected() {
		Cookie facebookCookie = getWebRequestCycle().getWebRequest().getCookie("7e86efc5bb70bfcbfb0c642b58edc710_user");
		if (facebookCookie!=null) {
			return true;
		}
		return false;
	}

}

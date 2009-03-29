/*
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.main;

import org.apache.wicket.markup.html.link.PageLink;

import com.marc.lastweek.web.pages.BasePage;
import com.marc.lastweek.web.pages.classifiedad.NewClassifiedAdPage;


public class MainPage extends BasePage {
	public MainPage() {
		this.add(new PageLink("newClassifiedAdLink",NewClassifiedAdPage.class));
	}
}

/*
 * SearchResultsPage.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.adslisting;

import org.apache.wicket.PageParameters;

import com.marc.lastweek.web.pages.BasePage;

public class FilterByTokenPage extends BasePage {

	/* 
	 * TODO: will receive a page parameters showing where the search comes from,
	 * this is, the search generates a subset of the result where it comes from
	 * 
	 *  The list of advertisements will be the panels AdsListPanel
	 */
	public FilterByTokenPage(PageParameters parameters) {
		super(parameters);
	}

}

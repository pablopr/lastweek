/*
 * AdvertisementDetailPage.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.classifiedad;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;

import com.marc.lastweek.web.components.ClassifiedAdDetailPanel;
import com.marc.lastweek.web.naming.PageParametersNaming;
import com.marc.lastweek.web.pages.BasePage;

public class ClassifiedAdDetailPage extends BasePage {

	public ClassifiedAdDetailPage(PageParameters parameters) {
		super(parameters);
		
		/* TODO: Use this parameters to create current navigation info and link panels */
		final Long classifiedAdId = Long.valueOf(parameters
			.getLong(PageParametersNaming.PARAM_NAME_CLASSIFIED_AD_ID));
		
		this.add(new ClassifiedAdDetailPanel("classifiedAdDetailPanel", classifiedAdId));
		this.add(new Link("backLink") {
		
			private static final long serialVersionUID = -5687954349037273279L;

			@Override
			public void onClick() {
//				this.setResponsePage();
//				System.out.println(((SignInSession)getSession()).getLastPage());
			}
		});
	}
}

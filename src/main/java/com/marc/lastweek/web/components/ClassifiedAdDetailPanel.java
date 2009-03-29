/*
 * ClassifiedAdDetailPanel.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.commons.naming.CommonNamingValues;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.util.ViewUtils;

public class ClassifiedAdDetailPanel extends Panel {

	private static final long serialVersionUID = -8566673466529089435L;

	public ClassifiedAdDetailPanel(String id, Long classifiedAdId) {
		super(id);
		
		final ClassifiedAd classifiedAd = LastweekApplication.get().getGeneralService().find(
				ClassifiedAd.class, classifiedAdId);
		
		// TODO: add image, add province and category
		this.add(new Label("classifiedAdPublicationDate",ViewUtils.labelizer(classifiedAd.getPublicationDate())));
		this.add(new Label("classifiedAdTitle",ViewUtils.labelizer(classifiedAd.getTitle())));
		this.add(new Label("classifiedAdDescription",ViewUtils.labelizer(classifiedAd.getDescription())));
		this.add(new Label("classifiedAdPrice",ViewUtils.labelizer(classifiedAd.getPrice())));
		final int sourceCode = classifiedAd.getSource().intValue();
		ExternalLink classifiedAdSourceLink = new ExternalLink("classifiedAdSourceLink", classifiedAd.getSourceURL()) {

			private static final long serialVersionUID = -5872308114085631059L;

			@Override
			public boolean isVisible() {
				if (sourceCode == ClassifiedAd.SOURCE_OUR)
					return false;
				return true;
			}
		};
		classifiedAdSourceLink.add(new Label("classifiedAdSource", ViewUtils.labelizer(
				CommonNamingValues.getSourceName(classifiedAd.getSource()))));
		this.add(classifiedAdSourceLink);
	}
}

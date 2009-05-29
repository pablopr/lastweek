/*
 * FacebookUserInfoPanel.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components.facebook.panels;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

import com.marc.lastweek.web.components.facebook.behaviors.FacebookLogoutBehavior;
import com.marc.lastweek.web.components.facebook.xfbml.FacebookProfilePic;

public class FacebookUserInfoPanel extends Panel {
	private static final long serialVersionUID = -4044485161181662642L;

	public FacebookUserInfoPanel(String id) {
		super(id);
		
		FacebookProfilePic facebookProfilePic = new FacebookProfilePic("facebookProfilePic");
		facebookProfilePic.setFacebookLogo(true);
		facebookProfilePic.setLinked(false);
		facebookProfilePic.setSize(FacebookProfilePic.SIZE_SQUARE);
		this.add(facebookProfilePic);
		
		WebMarkupContainer facebookLogoutLink = new WebMarkupContainer("facebookLogoutLink");
		facebookLogoutLink.add(new FacebookLogoutBehavior());
		this.add(facebookLogoutLink);
	}
}

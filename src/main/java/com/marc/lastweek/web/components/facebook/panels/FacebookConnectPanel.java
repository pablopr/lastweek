/*
 * @(#)FacebookConnectPanel.java
 *
 * Copyright (c) 2.009, denodo technologies, S.L. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * denodo technologies, S.L. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with denodo technologies S.L.
 */
package com.marc.lastweek.web.components.facebook.panels;

import org.apache.wicket.markup.html.panel.Panel;

import com.marc.lastweek.web.components.facebook.behaviors.FacebookLoginBehavior;
import com.marc.lastweek.web.components.facebook.xfbml.FacebookConnectButton;
import com.marc.lastweek.web.pages.facebook.FacebookAwarePage;

public class FacebookConnectPanel extends Panel {
	private static final long serialVersionUID = -7032982481076184629L;

    private FacebookConnectButton buttonLoginFacebook;
    
    public FacebookConnectPanel(String id) {
        super(id);
        
        FacebookUserInfoPanel facebookUser = new FacebookUserInfoPanel("facebookUser"){
			private static final long serialVersionUID = -1674808109694087668L;

			@Override
        	public boolean isVisible() {
        		return ((FacebookAwarePage)FacebookConnectPanel.this.getPage()).isFacebookConnected();
        	}
        };
        facebookUser.setOutputMarkupId(true);
        facebookUser.setOutputMarkupPlaceholderTag(true);
        this.add(facebookUser);
        
        buttonLoginFacebook = new FacebookConnectButton("buttonLoginFacebook"){
			private static final long serialVersionUID = -2199948587128000067L;

			@Override
        	public boolean isVisible() {
        		return !((FacebookAwarePage)FacebookConnectPanel.this.getPage()).isFacebookConnected();
        	}
        };
        buttonLoginFacebook.add(new FacebookLoginBehavior());
        this.add(buttonLoginFacebook);
    }
    
    public FacebookConnectButton getFacebookLoginButton() {
		return this.buttonLoginFacebook;
	}


}


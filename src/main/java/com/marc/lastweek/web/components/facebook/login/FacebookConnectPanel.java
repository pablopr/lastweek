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
package com.marc.lastweek.web.components.facebook.login;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

public class FacebookConnectPanel extends Panel {
    private static final long serialVersionUID = -7032982481076184629L;

    public FacebookConnectPanel(String id) {
        super(id);
        
        WebMarkupContainer facebookUser = new WebMarkupContainer("facebookUser");
        facebookUser.setOutputMarkupId(true);
        this.add(facebookUser);
        
        FacebookLoginButton buttonLoginFacebook = new FacebookLoginButton("buttonLoginFacebook");
        buttonLoginFacebook.add(new FacebookLoginBehaviour(facebookUser));
        buttonLoginFacebook.setOutputMarkupId(true);
        this.add(buttonLoginFacebook);
    }

}


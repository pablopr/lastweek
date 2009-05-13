/*
 * @(#)FacebookLoginBehaviour.java
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

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;

import com.marc.lastweek.web.components.facebook.util.CommonFacebookHTML;

public class FacebookLoginBehaviour extends AttributeModifier {
    private static final long serialVersionUID = -7311590719086044653L;
    
    private static final String EVENT_NAME = "onlogin";
    
    private WebMarkupContainer target;
    
    public FacebookLoginBehaviour(WebMarkupContainer target) {
        super(EVENT_NAME, true, new Model());
        this.target = target;
    }
    
    
    @Override
    public void bind(Component component) {
        super.bind(component);
    }

    
    @Override
    protected String newValue(final String currentValue, final String replacementValue) {
        return ("var user_box = document.getElementById(\""+ target.getMarkupId() +"\");" +
        				"user_box.innerHTML = \""+CommonFacebookHTML.USER_BOX_HTML+"\";" +
        				"FB.XFBML.Host.parseDomTree();");
    }
}


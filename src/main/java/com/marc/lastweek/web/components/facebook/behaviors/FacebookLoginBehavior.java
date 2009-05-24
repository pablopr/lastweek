/*
 * FacebookLoginBehaviour.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components.facebook.behaviors;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.model.Model;

public class FacebookLoginBehavior extends AttributeModifier {
    private static final long serialVersionUID = -7311590719086044653L;
    
    private static final String EVENT_NAME = "onlogin";
    
    public FacebookLoginBehavior() {
        super(EVENT_NAME, true, new Model());
    }
    
    
    @Override
    public void bind(Component component) {
        super.bind(component);
    }

    
    @Override
    protected String newValue(final String currentValue, final String replacementValue) {

        return ("FB.XFBML.Host.parseDomTree();" +
        		"document.getElementById(\"RES_ID_fb_login\").style.display=\"none\";");
    }
}


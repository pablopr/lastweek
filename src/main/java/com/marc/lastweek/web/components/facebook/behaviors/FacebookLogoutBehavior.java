/*
 * FacebookLogoutBehavior.java
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

public class FacebookLogoutBehavior extends AttributeModifier {
	private static final long serialVersionUID = 6316223747643689432L;

	private static final String EVENT_NAME = "onclick";
    
    public FacebookLogoutBehavior() {
        super(EVENT_NAME, true, new Model());
    }
    
    
    @Override
    public void bind(Component component) {
        super.bind(component);
    }

    
    @Override
    protected String newValue(final String currentValue, final String replacementValue) {
        return ("FB.Connect.logout(function() { refresh_page(); })");
    }
}

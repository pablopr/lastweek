/*
 * @(#)FacebookLoginButton.java
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

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;

public class FacebookLoginButton extends WebMarkupContainer {
    private static final long serialVersionUID = -7829078749283362022L;
    
    private String size = "medium";
    private String length = "long";
    private Boolean autologout = new Boolean(false);
    
    public FacebookLoginButton(String id) {
        super(id);
    }
    
    public FacebookLoginButton(String id, String size, String length) {
        super(id);
        this.size = size;
        this.length = length;
    }
    
    public FacebookLoginButton(String id, String size, Boolean autologout) {
        super(id);
        this.size = size;
        this.autologout = autologout;
    }
    
    @Override
    protected final void onComponentTag(final ComponentTag tag){
        checkComponentTag(tag, "login-button");
        tag.put("size",this.size);
        tag.put("length",this.length);
        tag.put("autologoutlink",this.autologout.toString());
    }

}


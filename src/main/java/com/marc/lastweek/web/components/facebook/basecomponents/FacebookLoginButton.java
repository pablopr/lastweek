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
package com.marc.lastweek.web.components.facebook.basecomponents;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;

public class FacebookLoginButton extends WebMarkupContainer {
	private static final long serialVersionUID = -7829078749283362022L;
	
	public static final String SIZE_SMALL = "small";
	public static final String SIZE_MEDIUM = "medium";
	public static final String SIZE_LARGE = "large";

	public static final String LENGTH_SHORT = "short";
	public static final String LENGTH_LONG = "long";
	
    private String size;
    private String length;
    private Boolean autologout;
    
    public FacebookLoginButton(String id) {
        this(id, "medium", "long", new Boolean(false));
    }
    
    public FacebookLoginButton(String id, String size, String length) {
    	this(id, size, length, new Boolean(false));
    }
    
    public FacebookLoginButton(String id, String size, String length, Boolean autologout) {
        super(id);
        this.size = size;
        this.length = length;
        this.autologout = autologout;
        this.setOutputMarkupId(true);
    }
    
    @Override
    protected final void onComponentTag(final ComponentTag tag){
        checkComponentTag(tag, "login-button");
        tag.put("size",this.size);
        tag.put("length",this.length);
        tag.put("autologoutlink",this.autologout.toString());
    }
    
    public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getLength() {
		return this.length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Boolean getAutologout() {
		return this.autologout;
	}

	public void setAutologout(Boolean autologout) {
		this.autologout = autologout;
	}
}


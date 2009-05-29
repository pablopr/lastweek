/*
 * FacebookProfilePic.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components.facebook.xfbml;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;

public class FacebookProfilePic extends WebMarkupContainer {
	private static final long serialVersionUID = -3007664904412907940L;

	public final static String SIZE_THUMB = "thumb";
	public final static String SIZE_SMALL = "small";
	public final static String SIZE_NORMAL = "normal";
	public final static String SIZE_SQUARE = "square";
	
	private String size;
	private Boolean linked;
	private Boolean facebookLogo;
	private Integer uid;
	
	public FacebookProfilePic(String id) {
		super(id);
	}
	
	public FacebookProfilePic(String id, Integer uid) {
		super(id);
		this.uid = uid;
	}
	

	@Override
	protected final void onComponentTag(final ComponentTag tag){
		checkComponentTag(tag, "profile-pic");
		
		if (this.uid!=null) {
			tag.put("uid", this.uid.toString());
		} else  {
			tag.put("uid","loggedinuser");
		}
		
		if (this.size!=null) {
			tag.put("size", this.size);
		}
		
		if (this.linked!=null) {
			tag.put("linked", this.linked.toString());
		}
		
		if (this.facebookLogo!=null) {
			tag.put("facebook-logo", this.facebookLogo);
		}
	}
	
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Boolean getLinked() {
		return this.linked;
	}

	public void setLinked(Boolean linked) {
		this.linked = linked;
	}

	public Boolean getFacebookLogo() {
		return this.facebookLogo;
	}

	public void setFacebookLogo(Boolean facebookLogo) {
		this.facebookLogo = facebookLogo;
	}
}

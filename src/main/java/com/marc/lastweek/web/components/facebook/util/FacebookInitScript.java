/*
 * FacebookInitScript.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components.facebook.util;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.Model;

public class FacebookInitScript extends WebComponent {
	private static final long serialVersionUID = 2251859792737968692L;

	public FacebookInitScript(String id, String facebookApiKey) {
		super(id, new Model(facebookApiKey));
	}
	
	/**
	 * @see org.apache.wicket.Component#onComponentTagBody(org.apache.wicket.markup.MarkupStream,
	 *      org.apache.wicket.markup.ComponentTag)
	 */
	protected void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
		String initScript = "FB.init(\""+getModelObjectAsString()+"\",\"facebookconnect\", " +
				"{\"reloadIfSessionStateChanged\":true});";
		replaceComponentTagBody(markupStream, openTag, initScript);
	}

}

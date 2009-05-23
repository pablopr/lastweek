/*
 * CommonHTML.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components.facebook.util;

import org.apache.wicket.markup.html.panel.Panel;

public class CommonFacebookHTML {

	private final static String PANEL_START = "<wicket:panel>";
	private final static String PANEL_END = "</wicket:panel>";
	
	public static String getPanelMarkup(Panel panel) {
		int startIndex;
		int endIndex;
		
		String fullMarkup = panel.getAssociatedMarkupStream(false).toString();
		startIndex = fullMarkup.indexOf(PANEL_START);
		endIndex = fullMarkup.indexOf(PANEL_END);
		
		return fullMarkup.substring(startIndex+PANEL_START.length(), endIndex).trim().replace("\"", "'");
	}
}

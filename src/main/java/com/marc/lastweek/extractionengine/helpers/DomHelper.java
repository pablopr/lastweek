/*
 * DomHelper.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.extractionengine.helpers;

import org.xml.sax.SAXException;

import com.meterware.httpunit.HTMLElement;
import com.meterware.httpunit.WebResponse;

public final class DomHelper {
	
	public static HTMLElement[] getElementsWithClassName(WebResponse wr, String className) throws SAXException {
		return wr.getElementsWithAttribute("class", className);
	}
	
}

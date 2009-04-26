/*
 * CalendarBridge.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.util.lucene.bridges;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.hibernate.search.bridge.ParameterizedBridge;
import org.hibernate.search.bridge.StringBridge;

public class CalendarBridge implements StringBridge, ParameterizedBridge {

	public static String DATE_PATTERN_PROPERTY = "datePattern";
	private String datePattern = "yyyyMMdd"; //Default pattern

	public String objectToString(Object object) {

		if (object == null) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(this.datePattern);
		Calendar calendar = (Calendar) object;
		return sdf.format(calendar.getTime());

	}

	@SuppressWarnings("unchecked")
	public void setParameterValues(Map parameters) {
		Object currentDatePattern = parameters.get(DATE_PATTERN_PROPERTY);
		if (currentDatePattern != null) {
			this.datePattern = (String) currentDatePattern;
		}
	}	
}

/*
 * DateUtils.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.util;

import java.util.Calendar;

public class DateUtils {

	public static Long getDaysFromThen(Calendar aDate) {
		long now = Calendar.getInstance().getTimeInMillis();
		long then = aDate.getTimeInMillis();
		long diffInMillis = now - then;
		return new Long(diffInMillis / 86400000);
	}
}

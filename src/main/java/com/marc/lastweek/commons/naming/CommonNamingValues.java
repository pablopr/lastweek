/*
 * CommonNamingValues.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.commons.naming;

import java.util.HashMap;
import java.util.Map;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;

public class CommonNamingValues {

	private static final Map<Integer, String> SOURCE_NAMES = new HashMap<Integer, String>();
	
	static {
		SOURCE_NAMES.put(Integer.valueOf(ClassifiedAd.SOURCE_OUR), "Nuestro");
		SOURCE_NAMES.put(Integer.valueOf(ClassifiedAd.SOURCE_EBAY), "eBay");
		SOURCE_NAMES.put(Integer.valueOf(ClassifiedAd.SOURCE_LOQUO), "LoQuo");
	}

	public static String getSourceName(Integer sourceCode) {
		return SOURCE_NAMES.get(sourceCode);
	}
}

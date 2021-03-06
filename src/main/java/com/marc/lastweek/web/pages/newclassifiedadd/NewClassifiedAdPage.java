/*
 * NewClassifiedAd.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.newclassifiedadd;

import com.marc.lastweek.business.views.commons.NewClassifiedAdAndUserDataTO;
import com.marc.lastweek.web.pages.StandardPage;

public abstract class NewClassifiedAdPage extends StandardPage {

	private static final long serialVersionUID = -7976014359463033532L;

	protected NewClassifiedAdAndUserDataTO newClassifiedAdTO;

	public NewClassifiedAdPage(NewClassifiedAdAndUserDataTO newClassifiedAdTO) {
		super();
		this.newClassifiedAdTO = newClassifiedAdTO;
	}

}

/*
 * CreateNewAdPropagandaPanel.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components.selfpropaganda;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.marc.lastweek.web.pages.newclassifiedadd.NewClassifiedAdPage;

public class CreateNewAdPropagandaPanel extends Panel {

	private static final long serialVersionUID = -7870868777482715573L;

	public CreateNewAdPropagandaPanel(String id) {
		super(id);
		this.add(new BookmarkablePageLink("createNewAd", NewClassifiedAdPage.class));
	}
}

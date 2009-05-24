/*
 * BaseSearchPage.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.marc.lastweek.web.components.SearchBox;

public class BaseSearchPage extends BasePage {

    private FeedbackPanel feedbackPanel;
    

    public BaseSearchPage() {
        this(null);
    }
    
    
    public BaseSearchPage(PageParameters pageParameters) {
        super(pageParameters);
        
		/*
		 * Search Box
		 */
		this.add(new SearchBox("searchBox", pageParameters));

		this.feedbackPanel  = new FeedbackPanel("feedbackPanel");
		this.feedbackPanel.setOutputMarkupId(true);
		add(this.feedbackPanel);
    }
}

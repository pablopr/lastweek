/*
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

public class StandardPage extends BasePage {

    private static final long serialVersionUID = 1133369278126501257L;

    private FeedbackPanel feedbackPanel;
    

    public StandardPage() {
        this(null);
    }
    
    
    public StandardPage(PageParameters pageParameters) {
        super(pageParameters);

		this.feedbackPanel  = new FeedbackPanel("feedbackPanel");
		this.feedbackPanel.setOutputMarkupId(true);
		add(this.feedbackPanel);
    }
    
}

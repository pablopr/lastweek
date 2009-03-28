/*
 * Copyright (c) 2009, denodo technologies, S.L. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * denodo technologies, S.L. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with denodo technologies S.L.
 */
package com.marc.lastweek.web.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class BasePage extends WebPage {

    private static final long serialVersionUID = 1133369278126501257L;

    private static final String CSS_URL = "css/styles.css";

    public BasePage() {
        this(null);
    }
    
    
    public BasePage(PageParameters pageParameters) {
        
        super(pageParameters);

        add(HeaderContributor.forCss(CSS_URL));

        /*
         * Add link to main page in header
         */
        add(new BookmarkablePageLink("mainPageLink", getApplication().getHomePage()));
        
    }
    
}

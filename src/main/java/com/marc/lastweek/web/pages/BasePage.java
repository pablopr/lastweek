/*
 * BasePage.java
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
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import com.marc.lastweek.web.components.selfpropaganda.FavoritesPropagandaPanel;

public class BasePage extends WebPage {

    private static final String CSS_URL = "css/styles.css";
    private static final String JQUERY_URL = "js/jquery-1.3.2.min.js";

    public BasePage() {
        this(null);
    }
    
    
    public BasePage(PageParameters pageParameters) {
        
        super(pageParameters);

        add(HeaderContributor.forCss(CSS_URL));
        add(HeaderContributor.forJavaScript(JQUERY_URL));

        add(new BookmarkablePageLink("mainPageLink", getApplication().getHomePage()));
		this.add(new FavoritesPropagandaPanel("favoritesBox"));

    }
}
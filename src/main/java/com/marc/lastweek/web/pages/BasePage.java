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
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketface.panels.FacebookConnectPanel;
import org.wicketface.markup.xfbml.FacebookConnectButton;
import org.wicketface.pages.FacebookAwarePage;

import com.marc.lastweek.web.components.selfpropaganda.FavoritesPropagandaPanel;
import com.marc.lastweek.web.session.LastweekSession;

public class BasePage extends FacebookAwarePage {

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
        
        FacebookConnectPanel facebookLogin = new FacebookConnectPanel("facebookLogin");
        facebookLogin.getFacebookLoginButton().setLength(FacebookConnectButton.LENGTH_SHORT);
        facebookLogin.getFacebookLoginButton().setSize(FacebookConnectButton.SIZE_MEDIUM);
        this.add(facebookLogin);
    }
    
    public final LastweekSession getLastweekSession() {
        return (LastweekSession) getSession();
    }
    

    
}
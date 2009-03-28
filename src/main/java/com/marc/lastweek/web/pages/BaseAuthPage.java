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
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.session.SignInSession;


public class BaseAuthPage extends BasePage {


    private static final long serialVersionUID = 2984793382247850718L;

    public BaseAuthPage() {
        this(null);
    }
    
    public BaseAuthPage(PageParameters pageParameters) {

        super(pageParameters);

        /*
         * Add link for signing out
         */
        Link signout = new Link("signOutLink") {

            private static final long serialVersionUID = -6377673361124572300L;

            @Override
            public void onClick() {
                SignInSession.get().signOut();
                setResponsePage(LastweekApplication.get().getSignInPageClass());
            }

        };
        this.add(signout);
        
        this.add(new Label("connecterdUser", SignInSession.get().getUserLogin()));

    }
    

    

    

}
/*
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.application;


import loc.marc.commons.business.services.general.GeneralService;

import org.apache.wicket.Application;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebRequestCycleProcessor;
import org.apache.wicket.protocol.http.request.CryptedUrlWebRequestCodingStrategy;
import org.apache.wicket.protocol.http.request.WebRequestCodingStrategy;
import org.apache.wicket.request.IRequestCodingStrategy;
import org.apache.wicket.request.IRequestCycleProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marc.lastweek.business.services.aaa.AaaService;
import com.marc.lastweek.business.services.classifiedads.ClassifiedAdsService;
import com.marc.lastweek.web.pages.aaa.SignInPage;
import com.marc.lastweek.web.pages.classifiedadslisting.FilterResultsPage;
import com.marc.lastweek.web.pages.main.MainPage;
import com.marc.lastweek.web.session.SignInSession;


@Component
public class LastweekApplication extends AuthenticatedWebApplication {


	@Autowired
	private AaaService aaaService;
	
	@Autowired
	private GeneralService generalService;
	
	@Autowired
	private ClassifiedAdsService classifiedService;
	
    public LastweekApplication() {
        super();
    }
    
    @Override
    public Class<? extends WebPage> getHomePage() {
        return MainPage.class;
    }

    
    @Override
    public Class<? extends WebPage> getSignInPageClass() {
        return SignInPage.class;
    }

    
    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return SignInSession.class;
    }

    
    /* (non-Javadoc)
     * @see org.apache.wicket.authentication.AuthenticatedWebApplication#init()
     */
    @Override
    protected void init() {
        
        super.init();
        
        
        /*
         * Mount bookmarkeable pages for prettyfied URLs
         */
        mountBookmarkablePage("/search", FilterResultsPage.class);
        
        
        /*
         * Add the role-based authorization strategy
         */
        getSecuritySettings().setAuthorizationStrategy(
                new MetaDataRoleAuthorizationStrategy(this));
        
        //MetaDataRoleAuthorizationStrategy.authorize(CustomerProductViewPage.class, User.ROLE_1565);

        /*
         * Remove wicket tags from result HTML
         */
        getMarkupSettings().setStripWicketTags(true);
        
    }   
    
    /*
     * This enables the encryption of non-prettified URLs 
     */
    @Override
    protected IRequestCycleProcessor newRequestCycleProcessor() {
        return new WebRequestCycleProcessor() {
            @Override
            protected IRequestCodingStrategy newRequestCodingStrategy() {
                return new CryptedUrlWebRequestCodingStrategy(new WebRequestCodingStrategy());
            }
        };
    }
    
    /*
     * Sets custom error pages
     */
    //@Override
    //public final RequestCycle newRequestCycle(final Request request, final Response response) {
    //    return new CallcenterRequestCycle (this, (WebRequest)request, (WebResponse)response);
    //}

    public static LastweekApplication get() {
        return (LastweekApplication) Application.get();
    }

	public AaaService getAaaService() {
		return this.aaaService;
	}

	public GeneralService getGeneralService() {
		return this.generalService;
	}

	public ClassifiedAdsService getClassifiedService() {
		return this.classifiedService;
	}
}

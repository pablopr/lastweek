/*
 * TestPage.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.facebook;

import com.marc.lastweek.web.components.facebook.behaviors.FacebookLoginBehavior;
import com.marc.lastweek.web.components.facebook.xfbml.FacebookConnectButton;
import com.marc.lastweek.web.pages.BasePage;

public class TestPage extends BasePage {
    
    public TestPage() {
    	FacebookConnectButton buttonLoginFacebook = new FacebookConnectButton("buttonLoginFacebook2"){
 			private static final long serialVersionUID = -2199948587128000067L;

 			@Override
         	public boolean isVisible() {
         		return !(TestPage.this.isFacebookConnected());
         	}
         };
         buttonLoginFacebook.add(new FacebookLoginBehavior());
         this.add(buttonLoginFacebook);
    }
}

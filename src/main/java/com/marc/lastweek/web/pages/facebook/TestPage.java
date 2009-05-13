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

import com.marc.lastweek.web.components.facebook.login.FacebookConnectPanel;
import com.marc.lastweek.web.pages.BasePage;

public class TestPage extends BasePage {
    
    public TestPage() {
        FacebookConnectPanel facebookLogin = new FacebookConnectPanel("facebookLogin");
        this.add(facebookLogin);
    }
}

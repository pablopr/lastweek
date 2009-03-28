/*
 * Copyright (c) 2009, denodo technologies, S.L. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * denodo technologies, S.L. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with denodo technologies S.L.
 */
package com.marc.lastweek.business.views.aaa;

import java.io.Serializable;
import java.util.List;

public class AuthenticatedUserData implements Serializable {

    private static final long serialVersionUID = -8753797787186674153L;


    private String login = null;
    private List<String> roles = null;


    public AuthenticatedUserData(String login, List<String> roles) {
        super();
        this.login = login;
        this.roles = roles;
    }


    public String getLogin() {
        return this.login;
    }
    
    
    public List<String> getRoles() {
        return this.roles;
    }    
}

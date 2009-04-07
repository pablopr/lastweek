/*
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.session;

import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.springframework.beans.factory.annotation.Autowired;

import com.marc.lastweek.business.services.aaa.AaaService;
import com.marc.lastweek.business.views.aaa.AuthenticatedUserData;
import com.marc.lastweek.commons.exceptions.IncorrectLoginException;


public class SignInSession extends AuthenticatedWebSession {    
    private static final long serialVersionUID = 6817054705718877022L;
    
    @Autowired
    AaaService aaaService;
    
    private AuthenticatedUserData user = null;
    private final Roles roles;
    private Set<Long> favorites;

    
    public SignInSession(Request request) {
        super(request);
        this.roles = new Roles();
    }

    
    @Override
    public final boolean authenticate(final String username, final String password) {

        this.user = null;
        this.roles.clear();
        this.favorites = new HashSet<Long>();

        try {
            this.user = this.aaaService.loginUser(username, password);
            this.roles.addAll(this.user.getRoles());
            return true;
        } catch (IncorrectLoginException e) {
            return false;
        }
        
    }

    
    @Override
    public Roles getRoles() {
        return this.roles;
    }


    @Override
    public void signOut() {
        super.signOut();
        this.user = null;
        this.roles.clear();
    }

    
    public AuthenticatedUserData getUser() {
        return this.user;
    }
    
    
    public String getUserLogin() {
        return (this.user == null)?  null : this.user.getLogin();
    }
    
    public static SignInSession get() {
        return (SignInSession) Session.get();
    }
    
    public Set<Long> getFavorites() {
		return this.favorites;
	}
	
	public void addFavorite(Long id) {
		this.favorites.add(id);
	}
	
	public void removeFavorite(Long id) {
		this.favorites.remove(id);
	}

}

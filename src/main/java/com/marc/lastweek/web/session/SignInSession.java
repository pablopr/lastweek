package com.marc.lastweek.web.session;

import org.apache.wicket.Application;
import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.marc.lastweek.business.services.aaa.AaaService;
import com.marc.lastweek.business.views.aaa.AuthenticatedUserData;
import com.marc.lastweek.commons.exceptions.IncorrectLoginException;
import com.marc.lastweek.web.application.LastweekApplication;


public class SignInSession extends AuthenticatedWebSession {

    
    private static final long serialVersionUID = 6817054705718877022L;

    private AuthenticatedUserData user = null;
    private final Roles roles;

    
    public SignInSession(Request request) {
        super(request);
        this.roles = new Roles();
    }

    
    @Override
    public final boolean authenticate(final String username, final String password) {

        this.user = null;
        this.roles.clear();

        try {
            WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(((LastweekApplication) Application.get()).getServletContext());   
            AaaService aaaService = (AaaService) ctx.getBean("aaaService");
            this.user = aaaService.loginUser(username, password);
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

}

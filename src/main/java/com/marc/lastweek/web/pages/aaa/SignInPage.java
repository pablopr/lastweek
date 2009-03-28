/*
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.aaa;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;

import com.marc.lastweek.web.pages.BasePage;
import com.marc.lastweek.web.session.SignInSession;



public class SignInPage extends BasePage {
    
    private static final long serialVersionUID = -6197836028963536762L;
    
    private final FeedbackPanel feedbackPanel;
    
    public SignInPage() {
        this(null);
    }
    
    
    public SignInPage(PageParameters pageParameters) {
        
        super(pageParameters);
        
        /*
         * Add feedback panel
         */

        this.feedbackPanel = new FeedbackPanel("feedbackPanel");
        this.feedbackPanel.setOutputMarkupPlaceholderTag(true);
        add(this.feedbackPanel);

        this.add(new SignInForm("signinForm"));
        
    }

    
    
    private static class SignInForm extends Form {

        
        private static final long serialVersionUID = 8314569867567481602L;

        
        private final TextField loginInput;
        private final PasswordTextField passwordInput;
        
        
        public SignInForm(String id) {
            
            super(id);

            this.loginInput = new TextField("loginInput", new Model());
            //this.loginInput.setLabel(ResourceUtils.createResourceModel("form.login", this));
            this.loginInput.setRequired(true);
            this.loginInput.add(StringValidator.maximumLength(30));
            add(this.loginInput);

            this.passwordInput = new PasswordTextField("passwordInput", new Model());
            this.passwordInput.setRequired(true);
            //this.passwordInput.setLabel(ResourceUtils.createResourceModel("form.password", this));
            this.passwordInput.setResetPassword(true);
            add(this.passwordInput);
        }


        @Override
        protected void onSubmit() {

            // Get session info
            SignInSession session = SignInSession.get();

            if (session.isSignedIn()) {
                resolveResponsePage();
            } else {
                String login = this.loginInput.getModelObjectAsString();
                String password = this.passwordInput.getModelObjectAsString();
                if (session.signIn(login, password)) {
                    resolveResponsePage();
                } else {
                    //error(ResourceUtils.createResourceModel("password.invalid", this).getString());
                }
            }
            
        }

        
        public void resolveResponsePage() {
            if (!continueToOriginalDestination()) {
                setResponsePage(getApplication().getHomePage());
            }
        }
        
    }

    
}

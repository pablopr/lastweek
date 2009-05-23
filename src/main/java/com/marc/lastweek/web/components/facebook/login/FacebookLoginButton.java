/*
 * @(#)FacebookLoginButton.java
 *
 * Copyright (c) 2.009, denodo technologies, S.L. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * denodo technologies, S.L. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with denodo technologies S.L.
 */
package com.marc.lastweek.web.components.facebook.login;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.marc.lastweek.web.components.facebook.util.CommonFacebookHTML;

public class FacebookLoginButton extends WebMarkupContainer {
    private static final long serialVersionUID = -7829078749283362022L;
    
    private String size;
    private String length;
    private Boolean autologout;
    private Panel target;
    
    public FacebookLoginButton(String id, Panel target) {
        this(id, "medium", "long", new Boolean(false), target);
    }
    
    public FacebookLoginButton(String id, String size, String length, Panel target) {
    	this(id, size, length, new Boolean(false), target);
    }
    
    public FacebookLoginButton(String id, String size, String length, Boolean autologout, Panel target) {
        super(id);
        this.size = size;
        this.length = length;
        this.autologout = autologout;
        this.target = target;
        this.setOutputMarkupId(true);
        this.add(new FacebookLoginBehaviour(this.target));
    }
    
    @Override
    protected final void onComponentTag(final ComponentTag tag){
        checkComponentTag(tag, "login-button");
        tag.put("size",this.size);
        tag.put("length",this.length);
        tag.put("autologoutlink",this.autologout.toString());
    }
    
    private class FacebookLoginBehaviour extends AttributeModifier {
        private static final long serialVersionUID = -7311590719086044653L;
        
        private static final String EVENT_NAME = "onlogin";
        
        private Panel target;
        private Component boundComponent;
        
        public FacebookLoginBehaviour(Panel target) {
            super(EVENT_NAME, true, new Model());
            this.target = target;
        }
        
        
        @Override
        public void bind(Component component) {
            super.bind(component);
            this.boundComponent = component;
        }

        
        @Override
        protected String newValue(final String currentValue, final String replacementValue) {

            return ("var user_box = document.getElementById(\""+ this.target.getMarkupId() +"\");" +
            				//"user_box.innerHTML = \""+ "Basura" +"\";" +
            				"user_box.innerHTML = \""+ CommonFacebookHTML.getPanelMarkup(target)+"\";" +
            				"user_box.style.display=\"block\";" +
            				"FB.XFBML.Host.parseDomTree();" +
            				"document.getElementById(\""+ "RES_ID_fb_login" +
            				"\").style.display=\"none\";");
        }
    }


}


/*
 * CaptchaPanel.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components.captcha;

import org.apache.wicket.extensions.markup.html.captcha.CaptchaImageResource;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

public class CaptchaPanel extends Panel{

	private static final long serialVersionUID = 4980146094259571240L;
	
	/** Random captcha password to match against. */
    final String imagePass = randomString(6, 8);
    final ValueMap properties = new ValueMap();
    
	public CaptchaPanel(String id) {
		super(id);
		this.setOutputMarkupId(true);
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        add(new CaptchaForm("captchaForm"));
		
	}

    private final class CaptchaForm extends Form{
        private static final long serialVersionUID = 1L;

        private final CaptchaImageResource captchaImageResource;

        public CaptchaForm(String id){
            super(id);

            this.captchaImageResource = new CaptchaImageResource(CaptchaPanel.this.imagePass);
            add(new Image("captchaImage", this.captchaImageResource));
            add(new RequiredTextField("password", new PropertyModel(CaptchaPanel.this.properties, "password")){
				private static final long serialVersionUID = -3699841211093951726L;

				@Override
                protected final void onComponentTag(final ComponentTag tag){
                    super.onComponentTag(tag);
                    // clear the field after each render
                    tag.put("value", "");
                }
            });
        }

        @Override 
        public void onSubmit(){
            if (!CaptchaPanel.this.imagePass.equals(getPassword())){
                error("Captcha password '" + getPassword() + "' is wrong.\n" +
                    "Correct password was: " + CaptchaPanel.this.imagePass);
            }
            else{
                info("Success!");
            }
            // force redrawing
            this.captchaImageResource.invalidate();
        }
    }


    private static int randomInt(int min, int max){
        return (int)(Math.random() * (max - min) + min);
    }

    private static String randomString(int min, int max){
        int num = randomInt(min, max);
        byte b[] = new byte[num];
        for (int i = 0; i < num; i++)
            b[i] = (byte)randomInt('a', 'z');
        return new String(b);
    }


    protected String getPassword(){
        return this.properties.getString("password");
    }
}
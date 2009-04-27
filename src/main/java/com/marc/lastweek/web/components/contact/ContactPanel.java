/*
 * ClassifiedAdDetailPanel.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components.contact;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.components.jquerytexteditor.JQueryTextEditor;
import com.marc.lastweek.web.util.ResourceUtils;

public class ContactPanel extends Panel {

	private static final long serialVersionUID = 51477335048445934L;
	
	protected final ClassifiedAd classifiedAd;

	public ContactPanel(String id, final Long userId, Long classifiedAdId) {
		super(id);
		this.classifiedAd = LastweekApplication.get()
			.getGeneralService().find(ClassifiedAd.class, classifiedAdId);
		this.add(new ContactForm("contactForm"));
	}
	
	private class ContactForm extends Form {
		
		private static final long serialVersionUID = -2757989694854220101L;
		
		protected final RequiredTextField email;
		protected final RequiredTextField name;
		protected final TextArea text;

		public ContactForm(String id) {
			super(id);
			this.name = new RequiredTextField("name", new Model());
			this.name.add(StringValidator.lengthBetween(5, 50));
			this.email = new RequiredTextField("email", new Model());
			this.email.add(EmailAddressValidator.getInstance());
			this.text = new JQueryTextEditor("text");
			this.text.add(StringValidator.lengthBetween(15, 510));
			this.text.setEscapeModelStrings(false);
			this.text.setRequired(true);
			add(this.name);
			add(this.email);
			add(this.text);
		}

		@Override
		protected void onSubmit() {
			
			LastweekApplication.get().getMailService().sendContactMail(this.name.getModelObjectAsString(), 
					this.email.getModelObjectAsString(), this.text.getModelObjectAsString(), 
					ContactPanel.this.classifiedAd);
			this.name.setModel(new Model());
			this.email.setModel(new Model());
			this.text.setModel(new Model());
			info(ResourceUtils.getResourceString("contactpanel.form.emailSent", ContactPanel.this));
		}

	}
}

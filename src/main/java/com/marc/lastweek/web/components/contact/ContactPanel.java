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

import com.marc.lastweek.web.pages.main.MainPage;

public class ContactPanel extends Panel {

	private static final long serialVersionUID = 51477335048445934L;

	public ContactPanel(String id, final Long userId, final Long classifiedAdId) {
		super(id);
		this.add(new ContactForm("contactForm"));
	}
	
	private class ContactForm extends Form {
		
		private static final long serialVersionUID = -2757989694854220101L;
		
		protected final RequiredTextField email;
		protected final TextArea text;

		public ContactForm(String id) {
			super(id);
			
			this.email = new RequiredTextField("email", new Model());
			this.email.add(EmailAddressValidator.getInstance());
//			this.text = new JQueryTextEditor("text");
			this.text = new TextArea("text", new Model());
			this.text.add(StringValidator.lengthBetween(5, 510));
			this.text.setRequired(true);
			add(this.email);
			add(this.text);
		}


		@Override
		protected void onSubmit() {
			
			this.setResponsePage(MainPage.class);

			super.onSubmit();
		}

	}
}

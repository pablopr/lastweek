/*
 * NewClassifiedAdUserDataPage.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.newclassifiedadd;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.marc.lastweek.business.views.commons.NewClassifiedAdAndUserDataTO;

public class NewClassifiedAdUserDataPage extends NewClassifiedAdPage{
	
	
	public NewClassifiedAdUserDataPage(NewClassifiedAdAndUserDataTO newClassifiedAdTO) {
		super(newClassifiedAdTO);
		this.newClassifiedAdTO = newClassifiedAdTO;

		WebMarkupContainer userDataDiv = new WebMarkupContainer("userDataDiv");
		
		userDataDiv.add(new UserDataForm("userDataForm"));
		
		Link backLink = new Link("backLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				this.setResponsePage(new NewClassifiedAdDescriptionPage(NewClassifiedAdUserDataPage.this.newClassifiedAdTO));

			}
		};
		backLink.add(new Label("backLinkLabel", "volver"));
		userDataDiv.add(backLink);
		
		this.add(userDataDiv);
	}


	private class UserDataForm extends Form {
		private static final long serialVersionUID = 9053897905303403343L;
		protected final RequiredTextField email;
		protected final TextField phone;
		protected final RequiredTextField name;


		public UserDataForm(String id) {
			super(id);
			this.name = new RequiredTextField("name", new Model(NewClassifiedAdUserDataPage.this.newClassifiedAdTO.getName()));
			this.phone = new TextField("phone", new Model(NewClassifiedAdUserDataPage.this.newClassifiedAdTO.getPhone()), String.class);

			this.email = new RequiredTextField("email", new Model(NewClassifiedAdUserDataPage.this.newClassifiedAdTO.getEmail()));
			this.email.add(EmailAddressValidator.getInstance());
			this.email.setRequired(true);

			add(this.name);
			add(this.phone);
			add(this.email);
			add(new SubmitLink("submitUserDataLink"){

				private static final long serialVersionUID = 8056648125766325902L;

				@Override
				public void onSubmit() {
					NewClassifiedAdUserDataPage.this.newClassifiedAdTO.setName(UserDataForm.this.name.getModelObjectAsString());
					NewClassifiedAdUserDataPage.this.newClassifiedAdTO.setPhone(UserDataForm.this.phone.getModelObjectAsString());
					NewClassifiedAdUserDataPage.this.newClassifiedAdTO.setEmail(UserDataForm.this.email.getModelObjectAsString());
					this.setResponsePage(new NewClassifiedAdCheckPage(NewClassifiedAdUserDataPage.this.newClassifiedAdTO));

				}

			});
		}
	}

}

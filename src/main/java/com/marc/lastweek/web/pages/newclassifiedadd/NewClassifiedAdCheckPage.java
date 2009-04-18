/*
 * NewClassifiedAdCheckPage.java
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
import org.apache.wicket.markup.html.link.Link;

import com.marc.lastweek.business.views.commons.NewClassifiedAdAndUserDataTO;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.util.ViewUtils;

public class NewClassifiedAdCheckPage extends NewClassifiedAdPage{


	public NewClassifiedAdCheckPage(NewClassifiedAdAndUserDataTO newClassifiedAdTO) {
		super(newClassifiedAdTO);
		this.newClassifiedAdTO = newClassifiedAdTO;

		WebMarkupContainer checkDiv = new WebMarkupContainer("checkDiv");
		
//		List<File> fileList = LastweekApplication.get().getImageService().getAllTemporalFiles(NewClassifiedAdCheckPage.this.newClassifiedAdTO.getImageRandomDir());
//		ImageFileListViewPanel fileListViewPanel = new ImageFileListViewPanel("fileListViewPanel", fileList);
//		checkDiv.add(fileListViewPanel);

		checkDiv.add(new Label("classifiedAdCategory", ViewUtils.labelizer(this.newClassifiedAdTO.getCategoryName())));
		checkDiv.add(new Label("classifiedAdSubcategory", ViewUtils.labelizer(this.newClassifiedAdTO.getSubcategoryName())));

		checkDiv.add(new Label("classifiedAdTitle", ViewUtils.labelizer(this.newClassifiedAdTO.getTitle())));
		Label description = new Label("classifiedAdDescription", ViewUtils.labelizer(this.newClassifiedAdTO.getDescription()));
		description.setEscapeModelStrings(false);
		checkDiv.add(description);
		checkDiv.add(new Label("classifiedAdPrice", ViewUtils.labelizer(this.newClassifiedAdTO.getPrice())));

		checkDiv.add(new Label("userDataEmail", ViewUtils.labelizer(this.newClassifiedAdTO.getEmail())));
		checkDiv.add(new Label("userDataPhone", ViewUtils.labelizer(this.newClassifiedAdTO.getPhone())));

		Link confirmationLink = new Link("confirmationLink") {
			private static final long serialVersionUID = 5478474480425390258L;

			@Override
			public void onClick() {
				LastweekApplication.get().getClassifiedAdsService().createClassifiedAd(NewClassifiedAdCheckPage.this.newClassifiedAdTO);

				setResponsePage(getApplication().getHomePage());
				getSession().info("se ha creado correctamente");
			}
		};
		// 	FIXME: localized messages from properties!!!
		confirmationLink.add(new Label("confirmationLabel", "publicar mi anuncio"));
		checkDiv.add(confirmationLink);
		
		this.add(checkDiv);
	}
}

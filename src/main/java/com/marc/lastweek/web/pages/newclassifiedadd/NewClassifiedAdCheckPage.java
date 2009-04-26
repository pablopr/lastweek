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

import java.io.File;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.file.Folder;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.views.commons.NewClassifiedAdAndUserDataTO;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.components.imagegallery.JQueryImagegallery;
import com.marc.lastweek.web.components.images.ImageFileListViewPanel;
import com.marc.lastweek.web.pages.classifiedadslisting.FavoriteClassifiedAdsPage;
import com.marc.lastweek.web.session.LastweekSession;
import com.marc.lastweek.web.util.ResourceUtils;
import com.marc.lastweek.web.util.ViewUtils;

public class NewClassifiedAdCheckPage extends NewClassifiedAdPage{


	public NewClassifiedAdCheckPage(NewClassifiedAdAndUserDataTO newClassifiedAdTO) {
		super(newClassifiedAdTO);
		this.newClassifiedAdTO = newClassifiedAdTO;

		WebMarkupContainer checkDiv = new WebMarkupContainer("checkDiv");
		
		Folder temporalFolder = NewClassifiedAdCheckPage.this.newClassifiedAdTO.getTemporalFolder();
		List<File> images = LastweekApplication.get().getImageService().getAllTemporalFiles(temporalFolder);
		
		JQueryImagegallery fileListViewPanel = new JQueryImagegallery("fileListViewPanel", images);
		checkDiv.add(fileListViewPanel);

		checkDiv.add(new Label("classifiedAdCategory", ViewUtils.labelizer(this.newClassifiedAdTO.getCategoryName())));
		checkDiv.add(new Label("classifiedAdSubcategory", ViewUtils.labelizer(this.newClassifiedAdTO.getSubcategoryName())));

		checkDiv.add(new Label("classifiedAdTitle", ViewUtils.labelizer(this.newClassifiedAdTO.getTitle())));
		Label description = new Label("classifiedAdDescription", ViewUtils.labelizer(this.newClassifiedAdTO.getDescription()));
		description.setEscapeModelStrings(false);
		checkDiv.add(description);
		checkDiv.add(new Label("classifiedAdPrice", ViewUtils.labelizer(this.newClassifiedAdTO.getPrice())));

		checkDiv.add(new Label("userDataEmail", ViewUtils.labelizer(this.newClassifiedAdTO.getEmail())));
		checkDiv.add(new Label("userDataPhone", ViewUtils.labelizer(this.newClassifiedAdTO.getPhone())));
		
		Link backLink = new Link("backLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				this.setResponsePage(new NewClassifiedAdUserDataPage(NewClassifiedAdCheckPage.this.newClassifiedAdTO));

			}
		};
		backLink.add(new Label("backLinkLabel", "volver"));
		checkDiv.add(backLink);

		Link confirmationLink = new Link("confirmationLink") {
			private static final long serialVersionUID = 5478474480425390258L;

			@Override
			public void onClick() {
				LastweekApplication.get().getImageService().saveAllImages(NewClassifiedAdCheckPage.this.newClassifiedAdTO.getTemporalFolder());
				ClassifiedAd classifiedAd = LastweekApplication.get().getClassifiedAdsService().createClassifiedAd(NewClassifiedAdCheckPage.this.newClassifiedAdTO);
				String emailAddress = classifiedAd.getUserData().getEmail();
				try {
					LastweekApplication.get().getMailService().sendActivationMail(classifiedAd);
					
					getSession().info(ResourceUtils.getResourceString("mail.sent", NewClassifiedAdCheckPage.this, emailAddress));
					
					setResponsePage(getApplication().getHomePage());
					
				} catch (Exception e) {
					getSession().error(ResourceUtils.getResourceString("mail.error", NewClassifiedAdCheckPage.this, emailAddress));
				}
			}
		};
		// 	FIXME: localized messages from properties!!!
		confirmationLink.add(new Label("confirmationLabel", "publicar mi anuncio"));
		checkDiv.add(confirmationLink);
		
		this.add(checkDiv);
	}
}

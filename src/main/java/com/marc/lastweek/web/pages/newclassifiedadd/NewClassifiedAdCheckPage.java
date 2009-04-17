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

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.LoadableDetachableModel;

import com.marc.lastweek.business.views.commons.NewClassifiedAdAndUserDataTO;
import com.marc.lastweek.web.application.LastweekApplication;

public class NewClassifiedAdCheckPage extends NewClassifiedAdPage{


	public NewClassifiedAdCheckPage(NewClassifiedAdAndUserDataTO newClassifiedAdTO) {
		super(newClassifiedAdTO);
		this.newClassifiedAdTO = newClassifiedAdTO;

		WebMarkupContainer checkDiv = new WebMarkupContainer("checkDiv");
		
//		List<File> fileList = LastweekApplication.get().getImageService().getAllTemporalFiles(NewClassifiedAdCheckPage.this.newClassifiedAdTO.getImageRandomDir());
//		ImageFileListViewPanel fileListViewPanel = new ImageFileListViewPanel("fileListViewPanel", fileList);
//		checkDiv.add(fileListViewPanel);

		checkDiv.add(new Label("classifiedAdCategory", new LoadableDetachableModel(){
			private static final long serialVersionUID = 6214064755552736602L;

			@Override
			protected Object load() {
				return NewClassifiedAdCheckPage.this.newClassifiedAdTO.getCategoryName();
			}
		}));
		checkDiv.add(new Label("classifiedAdSubcategory", new LoadableDetachableModel(){
			private static final long serialVersionUID = 970648523535816386L;

			@Override
			protected Object load() {
				return NewClassifiedAdCheckPage.this.newClassifiedAdTO.getSubcategoryName();
			}
		}));

		checkDiv.add(new Label("classifiedAdTitle", new LoadableDetachableModel(){
			private static final long serialVersionUID = 6266642250638714559L;
			@Override
			protected Object load() {
				return NewClassifiedAdCheckPage.this.newClassifiedAdTO.getTitle();
			}
		}));
		checkDiv.add(new Label("classifiedAdDescription", new LoadableDetachableModel(){
			private static final long serialVersionUID = 12279343931559149L;

			@Override
			protected Object load() {
				return NewClassifiedAdCheckPage.this.newClassifiedAdTO.getDescription();
			}
		}));
		checkDiv.add(new Label("classifiedAdPrice", new LoadableDetachableModel(){
			private static final long serialVersionUID = -4934863231126478229L;

			@Override
			protected Object load() {
				return NewClassifiedAdCheckPage.this.newClassifiedAdTO.getPrice();
			}
		}));

		checkDiv.add(new Label("userDataEmail", new LoadableDetachableModel(){
			private static final long serialVersionUID = 5750430827354561280L;

			@Override
			protected Object load() {
				return NewClassifiedAdCheckPage.this.newClassifiedAdTO.getEmail();
			}
		}));
		checkDiv.add(new Label("userDataPhone", new LoadableDetachableModel(){
			private static final long serialVersionUID = -3049466790761590794L;

			@Override
			protected Object load() {
				return NewClassifiedAdCheckPage.this.newClassifiedAdTO.getPhone();
			}
		}));

		Link confirmationLink = new Link("confirmationLink") {

			private static final long serialVersionUID = 5478474480425390258L;

			@Override
			public void onClick() {

				LastweekApplication.get().getClassifiedAdsService().createClassifiedAd(NewClassifiedAdCheckPage.this.newClassifiedAdTO);

				setResponsePage(getApplication().getHomePage());
				getSession().info("se ha creado correctamente");
			}
		};

		confirmationLink.add(new Label("confirmationLabel", "publicar mi anuncio"));
		checkDiv.add(confirmationLink);
		
		this.add(checkDiv);
	}


	public NewClassifiedAdCheckPage(PageParameters pageParameters) {
		super(pageParameters);
	}
}

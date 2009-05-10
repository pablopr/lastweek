/*
 * FavoriteClassifiedAdsPage.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.classifiedadslisting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.components.selfpropaganda.CreateNewAdPropagandaPanel;
import com.marc.lastweek.web.naming.PageParametersNaming;
import com.marc.lastweek.web.pages.StandardPage;
import com.marc.lastweek.web.pages.classifiedad.ClassifiedAdDetailPage;
import com.marc.lastweek.web.session.LastweekSession;
import com.marc.lastweek.web.util.ResourceUtils;
import com.marc.lastweek.web.util.ViewUtils;
import com.marc.lastweek.web.util.behaviours.JavaScriptAlertConfirmBehaviour;

public class FavoriteClassifiedAdsPage extends StandardPage {

	protected ListView classifiedList; 

	public FavoriteClassifiedAdsPage() {
		super();

		this.add(new CreateNewAdPropagandaPanel("createNewAd"));

		this.classifiedList = new ListView("classifiedAdsList", 
				new LoadableFavoritesModel()){

			private static final long serialVersionUID = 4626162638077489818L;

			@Override
			protected void populateItem(ListItem item) {
				ClassifiedAd classifiedAd = (ClassifiedAd)item.getModelObject();
				final Long classifiedAdId = classifiedAd.getId();

				item.add(new Label("classifiedAdPublicationDate",ViewUtils.labelizer(classifiedAd.getPublicationDate())));
				item.add(new Label("classifiedAdTitle",ViewUtils.labelizer(classifiedAd.getTitle())));
				Label description = new Label("classifiedAdDescription",ViewUtils.getDigest(classifiedAd.getDescription()));
				description.setEscapeModelStrings(false);
				item.add(description);
				item.add(new Label("classifiedAdPrice",ViewUtils.labelizer(classifiedAd.getPrice())));

				PageParameters linkParameters = new PageParameters();
				linkParameters.put(PageParametersNaming.PARAM_NAME_CLASSIFIED_AD_ID, 
						classifiedAd.getId());
				item.add(new BookmarkablePageLink("classifiedAdLink", ClassifiedAdDetailPage.class, linkParameters));

				Link deleteLink = new Link("deleteLink") {
					private static final long serialVersionUID = -3978160233917347727L;

					@Override
					public void onClick() {
						LastweekSession.get().removeFavorite(classifiedAdId);
					}

				};
				deleteLink.add(new JavaScriptAlertConfirmBehaviour("msg.delete"));
				item.add(deleteLink);
			}
		};
		this.add(this.classifiedList);

		// TODO: if list is empty, then add some kind of 'how to use favorites'

		this.add(new SendFavoritesMailForm("sendMailForm"));
	}

	public class LoadableFavoritesModel extends LoadableDetachableModel {

		private static final long serialVersionUID = 2338695327167347518L;

		@Override
		protected Object load() {
			List<Long> favoritesList = LastweekSession.get().getFavorites();
			if (favoritesList.size() > 0) {
				Map<String,Object> parameters = new HashMap<String, Object>();
				parameters.put("favorites", favoritesList);
				return LastweekApplication.get().getGeneralService().queryForList(ClassifiedAd.class,
						"getClassifiedAdsInList", parameters);
			} 
			return new ArrayList<Long>();
		}

	}

	public class SendFavoritesMailForm extends Form {
		private static final long serialVersionUID = -2958958251868355060L;

		protected TextField mailAddress; 

		public SendFavoritesMailForm(String id) {
			super(id);
			this.mailAddress = new TextField("mailAddress", new Model());
			this.mailAddress.add(EmailAddressValidator.getInstance());
			this.mailAddress.setRequired(true);
			this.mailAddress.setLabel(ResourceUtils.createResourceModel("mail.form.label", FavoriteClassifiedAdsPage.this));
			this.add(this.mailAddress);
		}

		@Override
		protected void onSubmit() {
			if (FavoriteClassifiedAdsPage.this.classifiedList.getList().size()>0) {
				String emailAddress = this.mailAddress.getModelObjectAsString();
				try {
					LastweekApplication.get().getMailService().sendFavoritesMail(emailAddress,
							LastweekSession.get().getFavorites());
					info(ResourceUtils.getResourceString("mail.sent", FavoriteClassifiedAdsPage.this, emailAddress));
				} catch (Exception e) {
					error(ResourceUtils.getResourceString("mail.error", FavoriteClassifiedAdsPage.this, emailAddress));
				}
			} else {
				warn(ResourceUtils.getResourceString("mail.empty", FavoriteClassifiedAdsPage.this));
			}
		}

	}
}

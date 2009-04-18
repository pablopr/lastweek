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
package com.marc.lastweek.web.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.views.classifiedad.ModifiedClassifiedAdTO;
import com.marc.lastweek.commons.naming.CommonNamingValues;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.session.LastweekSession;
import com.marc.lastweek.web.util.ViewUtils;

public class ClassifiedAdDetailPanel extends Panel {

	private static final long serialVersionUID = -8566673466529089435L;
	
	public ClassifiedAdDetailPanel(String id, final Long classifiedAdId) {
		super(id);
		
		final ClassifiedAd classifiedAd = LastweekApplication.get().getGeneralService().find(
				ClassifiedAd.class, classifiedAdId);
		
		final String title = classifiedAd.getTitle();
		final String description = classifiedAd.getDescription();
		final Double price = classifiedAd.getPrice();
		final Integer flag = classifiedAd.getFlag();
		final Integer state = classifiedAd.getState();
		final String hashCode = classifiedAd.getHashCode();
		final int sourceCode = classifiedAd.getSource().intValue();
		
		// TODO: add image, add province and category
		this.add(new Label("classifiedAdPublicationDate",ViewUtils.labelizer(classifiedAd.getPublicationDate())));
		this.add(new Label("classifiedAdTitle",ViewUtils.labelizer(classifiedAd.getTitle())));
		this.add(new Label("classifiedAdDescription",ViewUtils.labelizer(classifiedAd.getDescription())));
		this.add(new Label("classifiedAdPrice",ViewUtils.labelizer(classifiedAd.getPrice())));
		this.add(new Label("provinceName",ViewUtils.labelizer(classifiedAd.getProvince().getName())));
		this.add(new Label("categoryName",ViewUtils.labelizer(classifiedAd.getCategory().getName())));
		this.add(new Label("subcategoryName",ViewUtils.labelizer(classifiedAd.getSubcategory().getName())));
		this.add(new Label("userDataEmail",ViewUtils.labelizer(classifiedAd.getUserData().getEmail())));
		this.add(new Label("userDataPhone",ViewUtils.labelizer(classifiedAd.getUserData().getPhone())));
		this.add(new Label("userDataName",ViewUtils.labelizer(classifiedAd.getUserData().getName())));
		 
		ExternalLink classifiedAdSourceLink = new ExternalLink("classifiedAdSourceLink", classifiedAd.getSourceURL()) {

			private static final long serialVersionUID = -5872308114085631059L;

			@Override
			public boolean isVisible() {
				if (sourceCode == ClassifiedAd.SOURCE_OUR)
					return false;
				return true;
			}
		};
		
		classifiedAdSourceLink.add(new Label("classifiedAdSource", ViewUtils.labelizer(
				CommonNamingValues.getSourceName(classifiedAd.getSource()))));
		this.add(classifiedAdSourceLink);
		
		// TODO: Put strings in properties files
		final Label flagClassifiedAdLabel = new Label("flagClassifiedAdSpan", "Marcar anuncio como inapropiado");
		Link flagClassifiedAdLink =  new Link("flagClassifiedAdLink") {
			private static final long serialVersionUID = -4262681914874430193L;

			@Override
			public void onClick() {
				ModifiedClassifiedAdTO modifiedClassifiedAdTO = new ModifiedClassifiedAdTO(
						classifiedAdId, title, description, price, Integer.valueOf(flag.intValue() + 1), 
						state, hashCode);
				LastweekApplication.get().getGeneralService().modify(ClassifiedAd.class, modifiedClassifiedAdTO);
				this.setEnabled(false);
				flagClassifiedAdLabel.setModel(new Model("El anuncio ha sido marcado como inapropiado."));
			}
		};
		flagClassifiedAdLink.add(flagClassifiedAdLabel);
		this.add(flagClassifiedAdLink);
		
		Link addToFavoritesLink = new Link("addToFavoritesLink") {
			private static final long serialVersionUID = 8340452899324058655L;

			@Override
			public void onClick() {
				LastweekSession.get().addFavorite(classifiedAdId);
				info("Anuncio a�adido a favoritos");
				this.setVisible(false);
				
			}
			
			@Override
			public boolean isVisible() {
				if (LastweekSession.get().containsFavorite(classifiedAdId))
					return false;
				return true;
			}
			
		};
		this.add(addToFavoritesLink);
	}
}

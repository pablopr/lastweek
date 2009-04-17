/*
 * FavoritesPanel.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components.selfpropaganda;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.marc.lastweek.web.pages.classifiedadslisting.FavoriteClassifiedAdsPage;
import com.marc.lastweek.web.session.LastweekSession;

public class FavoritesPropagandaPanel extends Panel {
	private static final long serialVersionUID = 1545498293624817549L;

	public FavoritesPropagandaPanel(String id) {
		super(id);
		Link favoritesLink = new BookmarkablePageLink("seeFavorites", FavoriteClassifiedAdsPage.class);
		favoritesLink.add(new Label("favoirtesCount", new LoadableDetachableModel(){

			private static final long serialVersionUID = -5373071913434894820L;

			@Override
			protected Object load() {
				return Integer.valueOf(LastweekSession.get().favoritesCount());
			}
			
		}));
		this.add(favoritesLink);
	}
}

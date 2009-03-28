/*
 * FilterByCategoryProvincePage.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.classifiedadslisting;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.marc.lastweek.business.entities.category.Category;
import com.marc.lastweek.web.models.LoadableCategoriesListModel;
import com.marc.lastweek.web.naming.PageParametersNaming;
import com.marc.lastweek.web.pages.BasePage;

public class FilterByCategoryProvincePage extends BasePage {

	public FilterByCategoryProvincePage(PageParameters parameters) {
		super(parameters);

		final String searchTerm = parameters
				.getString(PageParametersNaming.PARAM_NAME_SEARCH_TERM);

		this.add(new ListView("subcategoriesList",
				new LoadableCategoriesListModel()) {

			private static final long serialVersionUID = -5142681180212487928L;

			@Override
			protected void populateItem(ListItem listItem) {
				Category category = (Category) listItem.getModelObject();
				listItem.add(new Label("subcategoryName", category.getName()));
				PageParameters linkParameters = new PageParameters();
				linkParameters.put(PageParametersNaming.PARAM_NAME_SEARCH_TERM,
						searchTerm);
				linkParameters.put(
						PageParametersNaming.PARAM_NAME_SUBCATEGORY_ID,
						category.getId());
				linkParameters.put(
						PageParametersNaming.PARAM_NAME_SUBCATEGORY_NAME,
						category.getName());
				listItem.add(new BookmarkablePageLink("subcategoryLink",
						FilterByCategoryPage.class, linkParameters));
			}
		});
	}
}

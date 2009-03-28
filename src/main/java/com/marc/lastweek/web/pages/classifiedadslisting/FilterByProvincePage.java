/*
 * FiletrByProvincePage.java
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
import com.marc.lastweek.business.views.classifiedad.FilterParameters;
import com.marc.lastweek.web.components.ClassifiedAdsListPanel;
import com.marc.lastweek.web.models.LoadableCategoriesListModel;
import com.marc.lastweek.web.naming.PageParametersNaming;
import com.marc.lastweek.web.pages.BasePage;

public class FilterByProvincePage extends BasePage {

	public FilterByProvincePage(PageParameters parameters) {
		super(parameters);

		final String searchTerm = parameters
				.getString(PageParametersNaming.PARAM_NAME_SEARCH_TERM);
		final Long provinceId = Long.valueOf(parameters.getLong(
				PageParametersNaming.PARAM_NAME_PROVINCE_ID));

		FilterParameters filterParameters = new FilterParameters();
		filterParameters.setSearchString(searchTerm);
		filterParameters.setProvinceId(provinceId);
		this.add(new ClassifiedAdsListPanel("classifiedAdsPanel", filterParameters));
		
		this.add(new ListView("categoriesList", 
				new LoadableCategoriesListModel()) {
			
			private static final long serialVersionUID = -5142681180212487928L;

			@Override
			protected void populateItem(ListItem listItem) {
				Category category = (Category)listItem.getModelObject();
				PageParameters linkParameters = new PageParameters();
                linkParameters.put(PageParametersNaming.PARAM_NAME_SEARCH_TERM, 
                		searchTerm);
                linkParameters.put(PageParametersNaming.PARAM_NAME_CATEGORY_ID, 
                		category.getId());
                linkParameters.put(PageParametersNaming.PARAM_NAME_CATEGORY_NAME, 
                		category.getName());
                BookmarkablePageLink categoryLink = 
                	new BookmarkablePageLink("categoryLink", 
    	            		FilterByCategoryPage.class, linkParameters);
                categoryLink.add(new Label("categoryName", category.getName()));
                listItem.add(categoryLink);
	        }
		});
	}
}

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

import com.marc.lastweek.business.entities.category.Subcategory;
import com.marc.lastweek.business.views.classifiedad.FilterParameters;
import com.marc.lastweek.web.components.ClassifiedAdsListPanel;
import com.marc.lastweek.web.models.LoadableSubcategoriesListModel;
import com.marc.lastweek.web.naming.PageParametersNaming;
import com.marc.lastweek.web.pages.BasePage;
import com.marc.lastweek.web.util.ViewUtils;

public class FilterByCategoryProvincePage extends BasePage {

	public FilterByCategoryProvincePage(PageParameters parameters) {
		super(parameters);

		final String searchTerm = parameters
				.getString(PageParametersNaming.PARAM_NAME_SEARCH_TERM);
		final Long categoryId = Long.valueOf(parameters.getLong(
				PageParametersNaming.PARAM_NAME_CATEGORY_ID));
		final String categoryName = parameters
			.getString(PageParametersNaming.PARAM_NAME_CATEGORY_NAME);
		final Long provinceId = Long.valueOf(parameters.getLong(
				PageParametersNaming.PARAM_NAME_PROVINCE_ID));
		final String provinceName = parameters
			.getString(PageParametersNaming.PARAM_NAME_PROVINCE_NAME);
		
		FilterParameters filterParameters = new FilterParameters();
		filterParameters.setSearchString(searchTerm);
		filterParameters.setCategoryId(categoryId);
		filterParameters.setProvinceId(provinceId);
		this.add(new ClassifiedAdsListPanel("classifiedAdsPanel", filterParameters));
		
		this.add(new ListView("subcategoriesList",
				new LoadableSubcategoriesListModel()) {

			private static final long serialVersionUID = -5142681180212487928L;

			@Override
			protected void populateItem(ListItem listItem) {
				Subcategory subcategory = (Subcategory) listItem.getModelObject();
				PageParameters linkParameters = new PageParameters();
				linkParameters.put(PageParametersNaming.PARAM_NAME_SEARCH_TERM,
						searchTerm);
				linkParameters.put(PageParametersNaming.PARAM_NAME_CATEGORY_ID,
						categoryId);
				linkParameters.put(PageParametersNaming.PARAM_NAME_CATEGORY_NAME,
						categoryName);
				linkParameters.put(PageParametersNaming.PARAM_NAME_SUBCATEGORY_ID,
						subcategory.getId());
				linkParameters.put(PageParametersNaming.PARAM_NAME_SUBCATEGORY_NAME,
						subcategory.getName());
				linkParameters.put(PageParametersNaming.PARAM_NAME_PROVINCE_ID,
						provinceId);
				linkParameters.put(PageParametersNaming.PARAM_NAME_PROVINCE_NAME,
						provinceName);
				BookmarkablePageLink subcategoryLink = 
					new BookmarkablePageLink("subcategoryLink",
							FilterByCategoryPage.class, linkParameters);
				subcategoryLink.add(new Label("subcategoryName", ViewUtils.labelizer(subcategory.getName())));
				listItem.add(subcategoryLink);
			}
		});
	}
}

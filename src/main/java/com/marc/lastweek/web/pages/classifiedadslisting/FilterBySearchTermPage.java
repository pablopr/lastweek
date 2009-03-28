/*
 * SearchResultsPage.java
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
import com.marc.lastweek.business.entities.province.Province;
import com.marc.lastweek.business.views.classifiedad.FilterParameters;
import com.marc.lastweek.web.components.ClassifiedAdsListPanel;
import com.marc.lastweek.web.models.LoadableCategoriesListModel;
import com.marc.lastweek.web.models.LoadableProvincesListModel;
import com.marc.lastweek.web.naming.PageParametersNaming;
import com.marc.lastweek.web.pages.BasePage;

public class FilterBySearchTermPage extends BasePage {

	public FilterBySearchTermPage(PageParameters parameters) {
		super(parameters);
		
		final String searchTerm = 
			parameters.getString(PageParametersNaming.PARAM_NAME_SEARCH_TERM);
		
		FilterParameters filterParameters = new FilterParameters();
		filterParameters.setSearchString(searchTerm);
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
		
		this.add(new ListView("provincesList", 
				new LoadableProvincesListModel()) {

			private static final long serialVersionUID = -5843308083402561880L;

			@Override
			protected void populateItem(ListItem listItem) {
				Province province = (Province)listItem.getModelObject();
				PageParameters linkParameters = new PageParameters();
                linkParameters.put(PageParametersNaming.PARAM_NAME_SEARCH_TERM, 
                		searchTerm);
                linkParameters.put(PageParametersNaming.PARAM_NAME_PROVINCE_ID, 
                		province.getId());
                linkParameters.put(PageParametersNaming.PARAM_NAME_PROVINCE_NAME, 
                		province.getName());
                BookmarkablePageLink provinceLink = 
                	new BookmarkablePageLink("provinceLink", 
    	            		FilterByProvincePage.class, linkParameters);
                provinceLink.add(new Label("provinceName", province.getName()));
                listItem.add(provinceLink);
	        }
		});
	}

}

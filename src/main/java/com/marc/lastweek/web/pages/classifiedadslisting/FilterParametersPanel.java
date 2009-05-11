/*
 * FilterParametersPanel.java
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
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import com.marc.lastweek.business.entities.category.Category;
import com.marc.lastweek.business.entities.category.Subcategory;
import com.marc.lastweek.business.entities.province.Province;
import com.marc.lastweek.business.views.classifiedad.Filter;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.naming.PageParametersNaming;
import com.marc.lastweek.web.util.ResourceUtils;

public class FilterParametersPanel extends Panel {
	private static final long serialVersionUID = -3755402668129962710L;

	public FilterParametersPanel(String id, final PageParameters parameters) {
		super(id);
		
		List<Filter> filtersList = initFilters(parameters);
		
		this.add(new ListView("filterList", filtersList) {
			private static final long serialVersionUID = 1194050258065567480L;

			@Override
			protected void populateItem(ListItem item) {
				PageParameters newParameters = (PageParameters) parameters.clone();
				Filter filter = (Filter)item.getModelObject();
				item.add(new Label("filterType", ResourceUtils.createResourceModel(filter.getFilterType(),
						FilterParametersPanel.this)));
				item.add(new Label("filterValue", filter.getFilterValue()));

				newParameters.remove(filter.getFilterType());
				if (!filter.getFilterType().equals(PageParametersNaming.PARAM_NAME_SEARCH_TERM)) {
					newParameters.remove(filter.getFilterName());
				}
				
				// If category is removed, then subcategory is removed too
				if (filter.getFilterType().equals(PageParametersNaming.PARAM_NAME_CATEGORY_ID)) {
					if (newParameters.get(PageParametersNaming.PARAM_NAME_SUBCATEGORY_ID)!=null) {
						newParameters.remove(PageParametersNaming.PARAM_NAME_SUBCATEGORY_ID);
						newParameters.remove(PageParametersNaming.PARAM_NAME_SUBCATEGORY_NAME);
					}
				}
				if (newParameters.size()>=1) {
					item.add(new BookmarkablePageLink("deleteLink",FilterResultsPage.class,newParameters));
				} else {
					item.add(new BookmarkablePageLink("deleteLink",LastweekApplication.get().getHomePage()));
				}
			}
			
		});
		
	}
	
	private List<Filter> initFilters(PageParameters parameters) {
		List<Filter> filtersList = new ArrayList<Filter>();
		
		if ( parameters.get(PageParametersNaming.PARAM_NAME_SEARCH_TERM) != null ) {
			filtersList.add(new Filter(
					PageParametersNaming.PARAM_NAME_SEARCH_TERM,
					null,
					StringEscapeUtils.unescapeHtml(parameters.getString(PageParametersNaming.PARAM_NAME_SEARCH_TERM)),
					null));
		}
		if ( parameters.get(PageParametersNaming.PARAM_NAME_CATEGORY_ID) != null ) {
			Category category = LastweekApplication.get().getGeneralService().get(Category.class, 
					new Long(parameters.getLong(PageParametersNaming.PARAM_NAME_CATEGORY_ID)));
			filtersList.add(new Filter(
					PageParametersNaming.PARAM_NAME_CATEGORY_ID,
					category.getId(),
					category.getName(),
					PageParametersNaming.PARAM_NAME_CATEGORY_NAME));
		}
		if ( parameters.get(PageParametersNaming.PARAM_NAME_SUBCATEGORY_ID ) != null ) {
			Subcategory subcategory = LastweekApplication.get().getGeneralService().get(Subcategory.class, 
					new Long(parameters.getLong(PageParametersNaming.PARAM_NAME_SUBCATEGORY_ID)));
			filtersList.add(new Filter(
					PageParametersNaming.PARAM_NAME_SUBCATEGORY_ID,
					subcategory.getId(),
					subcategory.getName(),
					PageParametersNaming.PARAM_NAME_SUBCATEGORY_NAME));
		}
		if ( parameters.get(PageParametersNaming.PARAM_NAME_PROVINCE_ID) != null ) {
			Province province = LastweekApplication.get().getGeneralService().get(Province.class, 
					new Long(parameters.getLong(PageParametersNaming.PARAM_NAME_PROVINCE_ID)));
			filtersList.add(new Filter(
					PageParametersNaming.PARAM_NAME_PROVINCE_ID,
					province.getId(),
					province.getName(),
					PageParametersNaming.PARAM_NAME_PROVINCE_NAME));
		}
		
		return filtersList;
	}
}

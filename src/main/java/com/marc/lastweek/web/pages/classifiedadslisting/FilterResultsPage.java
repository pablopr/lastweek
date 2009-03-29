/*
 * FilterResultsPage.java
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
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.marc.lastweek.business.entities.category.Category;
import com.marc.lastweek.business.entities.category.Subcategory;
import com.marc.lastweek.business.entities.province.Province;
import com.marc.lastweek.business.views.classifiedad.FilterParameters;
import com.marc.lastweek.web.components.ClassifiedAdsListPanel;
import com.marc.lastweek.web.models.LoadableCategoriesListModel;
import com.marc.lastweek.web.models.LoadableProvincesListModel;
import com.marc.lastweek.web.models.LoadableSubcategoriesListModel;
import com.marc.lastweek.web.naming.PageParametersNaming;
import com.marc.lastweek.web.pages.BasePage;

public class FilterResultsPage extends BasePage {

	protected String categoryName = "";
	protected String subcategoryName = "";
	protected String provinceName = "";
	
	public FilterResultsPage(PageParameters parameters) {
		boolean hasCategory = false;
		boolean hasSubcategory = false;
		boolean hasProvince = false;
		
		final FilterParameters filterParameters = new FilterParameters();
		
		if (parameters.get(PageParametersNaming.PARAM_NAME_SEARCH_TERM)!=null) {
			filterParameters.setSearchString(parameters.getString(PageParametersNaming.PARAM_NAME_SEARCH_TERM));
		}
		if (parameters.get(PageParametersNaming.PARAM_NAME_CATEGORY_ID)!=null) {
			hasCategory = true;
			this.categoryName = parameters.getString(PageParametersNaming.PARAM_NAME_CATEGORY_NAME);
			filterParameters.setCategoryId(new Long(parameters.getLong(PageParametersNaming.PARAM_NAME_CATEGORY_ID)));
		}
		if (parameters.get(PageParametersNaming.PARAM_NAME_SUBCATEGORY_ID)!=null) {
			hasSubcategory = true;
			this.subcategoryName = parameters.getString(PageParametersNaming.PARAM_NAME_SUBCATEGORY_NAME);
			filterParameters.setSubcategoryId(new Long(parameters.getLong(PageParametersNaming.PARAM_NAME_SUBCATEGORY_ID)));
		}
		if (parameters.get(PageParametersNaming.PARAM_NAME_PROVINCE_ID)!=null) {
			hasProvince = true;
			this.provinceName = parameters.getString(PageParametersNaming.PARAM_NAME_PROVINCE_NAME);
			filterParameters.setProvinceId(new Long(parameters.getLong(PageParametersNaming.PARAM_NAME_PROVINCE_ID)));
		}
		
		
		/*
		 * The results panel
		 */
		this.add(new ClassifiedAdsListPanel("classifiedAdsPanel", filterParameters));
		
		/*
		 * Categories
		 */
		WebMarkupContainer categoriesDiv = new WebMarkupContainer("categoriesDiv");
		ListView categoiresList = new ListView("categoriesList") {
			
			private static final long serialVersionUID = -5142681180212487928L;

			@Override
			protected void populateItem(ListItem listItem) {
				Category category = (Category)listItem.getModelObject();
				
				PageParameters linkParameters = new PageParameters();
                linkParameters.put(PageParametersNaming.PARAM_NAME_CATEGORY_ID, 
                		category.getId());
                linkParameters.put(PageParametersNaming.PARAM_NAME_CATEGORY_NAME, 
                		category.getName());
                if (filterParameters.getSearchString()!=null) {
                    linkParameters.put(PageParametersNaming.PARAM_NAME_SEARCH_TERM, 
                    		filterParameters.getSearchString());
                }
                if (filterParameters.getProvinceId()!=null) {
    				linkParameters.put(PageParametersNaming.PARAM_NAME_PROVINCE_ID,
    						filterParameters.getProvinceId());
    				linkParameters.put(PageParametersNaming.PARAM_NAME_PROVINCE_NAME,
    						FilterResultsPage.this.provinceName);
                }

                BookmarkablePageLink categoryLink = 
                	new BookmarkablePageLink("categoryLink", 
                			FilterResultsPage.class, linkParameters);
                
                categoryLink.add(new Label("categoryName", category.getName()));
	            listItem.add(categoryLink);
	        }
		};
		if (!hasCategory) {
			categoiresList.setModel(new LoadableCategoriesListModel());
		} else {
			categoiresList.setVisible(false);
		}
		categoriesDiv.add(categoiresList);
		this.add(categoriesDiv);
		
		/*
		 * Provinces
		 */
		WebMarkupContainer provincesDiv = new WebMarkupContainer("provincesDiv");
		ListView provincesList = new ListView("provincesList", 
				new LoadableProvincesListModel()) {

			private static final long serialVersionUID = -5843308083402561880L;

			@Override
			protected void populateItem(ListItem listItem) {
				Province province = (Province)listItem.getModelObject();
				
				PageParameters linkParameters = new PageParameters();
                linkParameters.put(PageParametersNaming.PARAM_NAME_PROVINCE_ID, 
                		province.getId());
                linkParameters.put(PageParametersNaming.PARAM_NAME_PROVINCE_NAME, 
                		province.getName());
                if (filterParameters.getSearchString()!=null) {
                    linkParameters.put(PageParametersNaming.PARAM_NAME_SEARCH_TERM, 
                    		filterParameters.getSearchString());
                }
                if (filterParameters.getCategoryId()!=null) {
    				linkParameters.put(PageParametersNaming.PARAM_NAME_CATEGORY_ID,
    						filterParameters.getCategoryId());
    				linkParameters.put(PageParametersNaming.PARAM_NAME_CATEGORY_NAME,
    						FilterResultsPage.this.categoryName);
                }
                if (filterParameters.getSubcategoryId()!=null) {
    				linkParameters.put(PageParametersNaming.PARAM_NAME_SUBCATEGORY_ID,
    						filterParameters.getSubcategoryId());
    				linkParameters.put(PageParametersNaming.PARAM_NAME_SUBCATEGORY_NAME,
    						FilterResultsPage.this.subcategoryName);
                }
                
                BookmarkablePageLink provinceLink = 
                	new BookmarkablePageLink("provinceLink", 
                			FilterResultsPage.class, linkParameters);
                provinceLink.add(new Label("provinceName", province.getName()));
                listItem.add(provinceLink);
	        }
		};
		if (!hasProvince) {
			provincesList.setModel(new LoadableProvincesListModel());
		} else {
			provincesList.setVisible(false);
		}
		provincesDiv.add(provincesList);
		this.add(provincesDiv);
		
		/*
		 * Subcategories
		 */
		WebMarkupContainer subcategoriesDiv = new WebMarkupContainer("subcategoriesDiv");
		ListView subcategoriesList = new ListView("subcategoriesList",
				new LoadableSubcategoriesListModel()) {

			private static final long serialVersionUID = -5142681180212487928L;

			@Override
			protected void populateItem(ListItem listItem) {
				Subcategory subcategory = (Subcategory) listItem.getModelObject();
				
				PageParameters linkParameters = new PageParameters();
				linkParameters.put(PageParametersNaming.PARAM_NAME_SUBCATEGORY_ID,
						subcategory.getId());
				linkParameters.put(PageParametersNaming.PARAM_NAME_SUBCATEGORY_NAME,
						subcategory.getName());
				if (filterParameters.getSearchString()!=null) {
                    linkParameters.put(PageParametersNaming.PARAM_NAME_SEARCH_TERM, 
                    		filterParameters.getSearchString());
                }
                if (filterParameters.getCategoryId()!=null) {
    				linkParameters.put(PageParametersNaming.PARAM_NAME_CATEGORY_ID,
    						filterParameters.getCategoryId());
    				linkParameters.put(PageParametersNaming.PARAM_NAME_CATEGORY_NAME,
    						FilterResultsPage.this.categoryName);
                }
                
				BookmarkablePageLink subcategoryLink = 
					new BookmarkablePageLink("subcategoryLink",
							FilterResultsPage.class, linkParameters);
				subcategoryLink.add(new Label("subcategoryName", subcategory.getName()));
				listItem.add(subcategoryLink);
			}
		};
		if (!hasSubcategory && hasCategory) {
			subcategoriesList.setModel(new LoadableSubcategoriesListModel());
		} else {
			subcategoriesList.setVisible(false);
		}
		subcategoriesDiv.add(subcategoriesList);
		this.add(subcategoriesDiv);
	}
	
}

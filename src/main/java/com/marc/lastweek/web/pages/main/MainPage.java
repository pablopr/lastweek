/*
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.main;


import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ImageMap;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.marc.lastweek.business.entities.category.Category;
import com.marc.lastweek.business.entities.province.Province;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.components.selfpropaganda.CreateNewAdPropagandaPanel;
import com.marc.lastweek.web.models.LoadableCategoriesListModel;
import com.marc.lastweek.web.models.LoadableProvincesListModel;
import com.marc.lastweek.web.naming.PageParametersNaming;
import com.marc.lastweek.web.pages.BaseSearchPage;
import com.marc.lastweek.web.pages.classifiedadslisting.FilterResultsPage;


public class MainPage extends BaseSearchPage {
	public MainPage() {

		this.add(new CreateNewAdPropagandaPanel("createNewAd"));

		this.add(new ListView("categoriesList", 
				new LoadableCategoriesListModel()) {

			private static final long serialVersionUID = -5142681180212487928L;

			@Override
			protected void populateItem(ListItem listItem) {
				Category category = (Category)listItem.getModelObject();
				PageParameters linkParameters = new PageParameters();
				linkParameters.put(PageParametersNaming.PARAM_NAME_CATEGORY_ID, 
						category.getId());
				linkParameters.put(PageParametersNaming.PARAM_NAME_CATEGORY_NAME, 
				        StringEscapeUtils.escapeHtml(category.getName()));

				BookmarkablePageLink categoryLink = 
					new BookmarkablePageLink("categoryLink", 
							FilterResultsPage.class, linkParameters);
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
				linkParameters.put(PageParametersNaming.PARAM_NAME_PROVINCE_ID, 
						province.getId());
				linkParameters.put(PageParametersNaming.PARAM_NAME_PROVINCE_NAME, 
				        StringEscapeUtils.escapeHtml(province.getName()));
				BookmarkablePageLink provinceLink = 
					new BookmarkablePageLink("provinceLink", 
							FilterResultsPage.class, linkParameters);
				provinceLink.add(new Label("provinceName", province.getName()));

				if (listItem.getIndex()% 2 == 1) {
					listItem.add(new SimpleAttributeModifier("class", "parallel-column"));
				}


				listItem.add(provinceLink);
			}
		});
		
		/*
		 * Clickable provinces map
		 */

		ImageMap mapProvinceMap = new ImageMap("mapProvinceMap");
	
        for(Province province:LastweekApplication.get().getGeneralService().findAll(Province.class)){ 
            String[] stringCoords = StringUtils.split(province.getCoords(), ",");
            int[] intCoords = new int[stringCoords.length];
            for (int i = 0; i<stringCoords.length; i++) {
                intCoords[i] = (new Integer(stringCoords[i].trim())).intValue();
            }
            PageParameters linkParameters = new PageParameters();
            linkParameters.put(PageParametersNaming.PARAM_NAME_PROVINCE_ID, 
                    province.getId());
            linkParameters.put(PageParametersNaming.PARAM_NAME_PROVINCE_NAME, 
                    StringEscapeUtils.escapeHtml(province.getName()));
            mapProvinceMap.addPolygonLink(intCoords, new BookmarkablePageLink("imageMapLink"+province.getId(),
		            FilterResultsPage.class, linkParameters));
		}
        this.add(mapProvinceMap);
		
	}

}

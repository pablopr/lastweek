/*
 * NewClassifiedAdProvincePage.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.newclassifiedadd;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.marc.lastweek.business.entities.province.Province;
import com.marc.lastweek.business.views.commons.NewClassifiedAdAndUserDataTO;
import com.marc.lastweek.web.models.LoadableProvincesListModel;


public class NewClassifiedAdProvincePage extends NewClassifiedAdPage {
	
	
	public NewClassifiedAdProvincePage(NewClassifiedAdAndUserDataTO newClassifiedAdTO) {
		super(newClassifiedAdTO);
//		add(new ProvincePanel("provincePanel"));
		WebMarkupContainer provinceDiv = new WebMarkupContainer("provinceDiv");
		ListView provinceListView =new ListView("provinceListView", 
				new LoadableProvincesListModel()) {

			private static final long serialVersionUID = -5843308083402561880L;

			@Override
			protected void populateItem(ListItem listItem) {
				Province province = (Province)listItem.getModelObject();
				final Long provinceId = province.getId();
				final String provinceName = province.getName();
				
				
				Link provinceLink = new Link("provinceLink") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						NewClassifiedAdProvincePage.this.newClassifiedAdTO.setProvinceId(provinceId);
						NewClassifiedAdProvincePage.this.newClassifiedAdTO.setProvinceName(provinceName);
						this.setResponsePage(new NewClassifiedAdCategoryPage(NewClassifiedAdProvincePage.this.newClassifiedAdTO));

					}
				};

				provinceLink.add(new Label("provinceLabel",provinceName));
				listItem.add(provinceLink);
			}
		};
		provinceDiv.add(provinceListView);
		
		this.add(provinceDiv);
	}


	public NewClassifiedAdProvincePage(PageParameters pageParameters) {
		super(pageParameters);
	}
	

}

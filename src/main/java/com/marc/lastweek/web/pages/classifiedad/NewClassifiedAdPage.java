/*
 * NewClassifiedAd.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.classifiedad;

import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import com.marc.lastweek.business.entities.category.Category;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.pages.BasePage;

public class NewClassifiedAdPage extends BasePage {
	

	private static final long serialVersionUID = -7976014359463033532L;
	
	private List<Category> categories;
	
	
	
	private class CategoryPanel extends Panel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1266334687818119105L;

		public CategoryPanel(String id, List<Category> categories) {
			super(id);
			add(new ListView("category",categories){

				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem item) {
					add(new Label("link",((Category)item.getModelObject()).getName()));
					
				}
				
			});
		}
	}




	public NewClassifiedAdPage() {
		super();	
		this.categories = LastweekApplication.get().getGeneralService().findAll(Category.class);
		add(new CategoryPanel("categoryPanel",this.categories));
	}




	public NewClassifiedAdPage(PageParameters pageParameters) {
		super(pageParameters);
		// TODO Auto-generated constructor stub
	}

	
	

}

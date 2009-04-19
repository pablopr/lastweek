/*
 * NewClassifiedAdCategoryPage.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.newclassifiedadd;

import java.util.HashMap;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.marc.lastweek.business.entities.category.Category;
import com.marc.lastweek.business.entities.category.Subcategory;
import com.marc.lastweek.business.views.commons.NewClassifiedAdAndUserDataTO;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.models.LoadableCategoriesListModel;

public class NewClassifiedAdCategoryPage  extends NewClassifiedAdPage{
	
	protected CategoryPanel categoryPanel;
	protected SubcategoryPanel subcategoryPanel;
	
	public NewClassifiedAdCategoryPage(NewClassifiedAdAndUserDataTO newClassifiedAdTO) {
		super(newClassifiedAdTO);
		this.categoryPanel = new CategoryPanel("categoryPanel");
		add(this.categoryPanel);
		this.subcategoryPanel = new SubcategoryPanel("subcategoryPanel");
		add(this.subcategoryPanel);
	}

	
	private class CategoryPanel extends Panel {

		private static final long serialVersionUID = -1266334687818119105L;

		public CategoryPanel(String id) {
			super(id);
			this.setOutputMarkupId(true);
			this.setOutputMarkupPlaceholderTag(true);
			CategoryPanel.this.setVisible(true);
			this.add(new ListView("categoryListView", new LoadableCategoriesListModel()) {

				private static final long serialVersionUID = 6730094093871495627L;

				@Override
				protected void populateItem(ListItem item) {
					Category  category = (Category)item.getModelObject();
					final Long categoryId = category.getId();
					final String categoryName = category.getName();
					final int countSubcategories = category.getSubcategories().size();
					
					Link categoryLink = new AjaxFallbackLink("categoryLink") {

						private static final long serialVersionUID = -1462588672710233585L;


						@Override
						public void onClick(AjaxRequestTarget target) {
							NewClassifiedAdCategoryPage.this.newClassifiedAdTO.setCategoryId(categoryId);
							NewClassifiedAdCategoryPage.this.newClassifiedAdTO.setCategoryName(categoryName);
							CategoryPanel.this.setVisible(false);

							if ( countSubcategories == 0 ) {
								this.setResponsePage(new NewClassifiedAdDescriptionPage(NewClassifiedAdCategoryPage.this.newClassifiedAdTO));
							}
							else{
								NewClassifiedAdCategoryPage.this.subcategoryPanel.setCategoryId(categoryId);
								NewClassifiedAdCategoryPage.this.subcategoryPanel.setVisible(true);
							}
							if ( target != null ) {
								target.addComponent(NewClassifiedAdCategoryPage.this.subcategoryPanel);
								target.addComponent(CategoryPanel.this);
							}
						}

					};

					categoryLink.add(new Label("categoryLabel",categoryName));
					item.add(categoryLink);
				}

			});
			
			Link backLink = new Link("backLink") {
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					this.setResponsePage(new NewClassifiedAdProvincePage(NewClassifiedAdCategoryPage.this.newClassifiedAdTO));

				}
			};
			backLink.add(new Label("backLinkLabel", "volver"));
			this.add(backLink);
		}

	}


	private class SubcategoryPanel extends Panel {

		private static final long serialVersionUID = 7478601607373577524L;
		protected Long categoryId ;

		public  SubcategoryPanel(final String id) {
			super(id);
			SubcategoryPanel.this.setVisible(false);
			this.setOutputMarkupId(true);
			this.setOutputMarkupPlaceholderTag(true);
			add(new ListView("subcategoryListView",new LoadableDetachableModel() {

				private static final long serialVersionUID = 5737843643543228915L;

				@Override
				protected Object load() {
					HashMap<String,Object> parameters = new HashMap<String, Object>();
					parameters.put("categoryId", SubcategoryPanel.this.categoryId);

					return LastweekApplication.get().getGeneralService().queryForList(Subcategory.class, "findSubcategoriesByCategoryId", parameters);
				}

			}){

				private static final long serialVersionUID = -5397807417164068536L;

				@Override
				protected void populateItem(final ListItem item) {
					Subcategory subcategory = (Subcategory)item.getModelObject();
					final Long subcategoryId = subcategory.getId();
					final String subcategoryName = subcategory.getName();
					Link categoryLink = new Link("subcategoryLink") {

						private static final long serialVersionUID = -1462588672710233585L;

						@Override
						public void onClick() {
							NewClassifiedAdCategoryPage.this.newClassifiedAdTO.setSubcategoryId(subcategoryId);
							NewClassifiedAdCategoryPage.this.newClassifiedAdTO.setSubcategoryName(subcategoryName);
							SubcategoryPanel.this.setVisible(false);
							this.setResponsePage(new NewClassifiedAdDescriptionPage(NewClassifiedAdCategoryPage.this.newClassifiedAdTO));
						}

					};				
					categoryLink.add(new Label("subcategoryLabel",subcategoryName));
					item.add(categoryLink);

				}

			});
			
			Link backLink = new Link("backLink") {
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					SubcategoryPanel.this.setVisible(false);
					NewClassifiedAdCategoryPage.this.categoryPanel.setVisible(true);

				}
			};
			backLink.add(new Label("backLinkLabel", "volver"));
			this.add(backLink);

		}
		public void setCategoryId(Long categoryId){
			this.categoryId = categoryId;
		}
	}
	
	

}

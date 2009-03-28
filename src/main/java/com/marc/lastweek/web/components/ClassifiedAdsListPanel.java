/*
 * AdsListPanel.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components;

import java.util.Iterator;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.views.aaa.FilterParameters;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.util.ViewUtils;

public class ClassifiedAdsListPanel extends Panel {
	private static final long serialVersionUID = -2481706792408827434L;

	private static final int RESULTS_PER_PAGE = 10; 

	public ClassifiedAdsListPanel(String id, FilterParameters filterParameters) {
		super(id);

		this.add(new DataView("classifiedAdsList", new FilterCalssifiedAdsProvider(filterParameters), RESULTS_PER_PAGE){
			private static final long serialVersionUID = 8440379131631972878L;

			@Override
			protected void populateItem(Item item) {
				ClassifiedAd classifiedAd = (ClassifiedAd)item.getModelObject();
				
				// TODO: add image, add province and category
				item.add(new Label("classifiedAdPublicationDate",ViewUtils.labelizer(classifiedAd.getPublicationDate())));
				item.add(new Label("classifiedAdTitle",ViewUtils.labelizer(classifiedAd.getTitle())));
				item.add(new Label("classifiedAdDescription",ViewUtils.labelizer(classifiedAd.getDescription())));
				item.add(new Label("classifiedAdPrice",ViewUtils.labelizer(classifiedAd.getPrice())));
			}

		});
	}

	class FilterCalssifiedAdsProvider implements IDataProvider  {
		private static final long serialVersionUID = 2434577380158362087L;

		private FilterParameters filterParameters;
		private Integer resultsCount;

		public FilterCalssifiedAdsProvider(FilterParameters filterParameters) {
			super();
			this.filterParameters = filterParameters;
		}

		@SuppressWarnings("unchecked")
		public Iterator iterator(int start, int count) {
			return LastweekApplication.get().getClassifiedService().filterClassifiedAds(filterParameters, start, count)
			.iterator();
		}

		public int size() {
			if (this.resultsCount == null) {
				this.resultsCount = LastweekApplication.get().getClassifiedService().countFilterAdvertisements(filterParameters);;
			}
			return this.resultsCount.intValue();
		}

		public IModel model(Object object) {
			return new ClassifiedAdModel((ClassifiedAd) object);
		}

		protected class ClassifiedAdModel extends LoadableDetachableModel {
			private static final long serialVersionUID = -4307533817106370972L;

			private Long id;

			public ClassifiedAdModel(ClassifiedAd classified) {
				this.id = classified.getId();
			}

			@Override
			protected Object load() {
				return LastweekApplication.get().getGeneralService().find(ClassifiedAd.class, this.id);
			}
		}

		public void detach() {
			//Not needed            
		}
	}


}

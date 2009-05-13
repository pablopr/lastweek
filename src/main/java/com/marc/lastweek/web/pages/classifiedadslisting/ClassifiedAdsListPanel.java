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
package com.marc.lastweek.web.pages.classifiedadslisting;

import java.util.Calendar;
import java.util.Iterator;

import org.apache.commons.lang.time.DateUtils;
import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.joda.time.DateTime;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.views.classifiedad.FilterParameters;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.components.paginator.VerticalFancyPaginator;
import com.marc.lastweek.web.naming.PageParametersNaming;
import com.marc.lastweek.web.pages.classifiedad.ClassifiedAdDetailPage;
import com.marc.lastweek.web.util.ViewUtils;

public class ClassifiedAdsListPanel extends Panel {
	private static final long serialVersionUID = -2481706792408827434L;

	private static final int RESULTS_PER_PAGE = 5; 

	public ClassifiedAdsListPanel(String id, final FilterParameters filterParameters) {
		super(id);
		
		WebMarkupContainer classifiedListBox = new WebMarkupContainer("classifiedListBox");
		DataView classifiedList = new DataView("classifiedAdsList", new FilterCalssifiedAdsProvider(filterParameters), RESULTS_PER_PAGE){
			private static final long serialVersionUID = 8440379131631972878L;

			@Override
			protected void populateItem(Item item) {
				ClassifiedAd classifiedAd = (ClassifiedAd)item.getModelObject();

				PageParameters linkParameters = new PageParameters();
                linkParameters.put(PageParametersNaming.PARAM_NAME_CLASSIFIED_AD_ID, 
                		classifiedAd.getId());
				Link classifiedAdLink = (new BookmarkablePageLink("classifiedAdLink", 
                		ClassifiedAdDetailPage.class, linkParameters));
				classifiedAdLink.add(new Label("classifiedAdTitle",ViewUtils.labelizer(classifiedAd.getTitle())));
				item.add(classifiedAdLink);
				
				item.add(new Label("classifiedAdPublicationDate",ViewUtils.labelizer(classifiedAd.getPublicationDate())));
				
				Label description = new Label("classifiedAdDescription",ViewUtils.getDigest(classifiedAd.getDescription()));
				description.setEscapeModelStrings(false);
				item.add(description);
				item.add(new Label("classifiedAdPrice",ViewUtils.labelizer(classifiedAd.getPrice())));

                
                if (item.getIndex()%2 == 1) {
                	item.add(new SimpleAttributeModifier("class", "classified-ad-summary-alt"));
                }
			}
		};
		classifiedListBox.add(classifiedList);
		classifiedListBox.setOutputMarkupId(true);
		this.add(classifiedListBox);
		
		VerticalFancyPaginator paginationLinks = new VerticalFancyPaginator("paginationLinks", classifiedList);
		this.add(paginationLinks);
        
     // No results
        WebMarkupContainer noResultsDiv = new WebMarkupContainer("noResults");
        this.add(noResultsDiv);

        if (classifiedList.getDataProvider().size() != 0) {
            noResultsDiv.setVisible(false);
        } else {
        	paginationLinks.setVisible(false);
        }

	}

	class FilterCalssifiedAdsProvider implements IDataProvider  {
		private static final long serialVersionUID = 2434577380158362087L;

		private FilterParameters filterParameters;
		private Integer resultsCount;
		private Calendar date;

		public FilterCalssifiedAdsProvider(FilterParameters filterParameters) {
			super();
			this.filterParameters = filterParameters;
			DateTime now = new DateTime(Calendar.getInstance());
			this.date = DateUtils.truncate(now.minusWeeks(1).toCalendar(getLocale()), Calendar.DAY_OF_MONTH);
		}

		@SuppressWarnings("unchecked")
		public Iterator iterator(int start, int count) {
			return LastweekApplication.get().getClassifiedAdsService().
			findClassifiedAdsByFilterParameters(this.filterParameters, start, count, this.date)
			.iterator();
		}

		public int size() {
			if (this.resultsCount == null) {
				this.resultsCount = LastweekApplication.get().getClassifiedAdsService().
				countClassifiedAdsByFilterParameters(this.filterParameters, this.date);
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

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

import loc.marc.commons.business.services.general.GeneralService;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.services.classifiedads.ClassifiedAdsService;
import com.marc.lastweek.business.views.aaa.FilterParameters;

public class ClassifiedAdsListPanel extends Panel {
	private static final long serialVersionUID = -2481706792408827434L;

	public ClassifiedAdsListPanel(String id, FilterParameters filterParameters) {
		super(id);
		
		
	}
	
	 class FilterCalssifiedAdsProvider implements IDataProvider  {
	        private static final long serialVersionUID = 2434577380158362087L;
	        
	        @SpringBean
	        private ClassifiedAdsService classifiedAdService;
	        @SpringBean
	        private GeneralService generalService;
	        
	        private FilterParameters filterParameters;
	        private Integer resultsCount;

	        public FilterCalssifiedAdsProvider(FilterParameters filterParameters) {
	            super();
	            this.filterParameters = filterParameters;
	        }

	        @SuppressWarnings("unchecked")
	        public Iterator iterator(int start, int count) {
	            return classifiedAdService.filterClassifiedAds(filterParameters, start, count)
	            .iterator();
	        }

	        public int size() {
	            if (this.resultsCount == null) {
	                this.resultsCount = classifiedAdService.countFilterAdvertisements(filterParameters);;
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
	                return generalService.find(ClassifiedAd.class, this.id);
	            }
	        }

	        public void detach() {
	            //Not needed            
	        }
	    }


}

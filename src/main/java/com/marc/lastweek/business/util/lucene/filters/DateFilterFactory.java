/*
 * DateFilterFactory.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.util.lucene.filters;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.lucene.search.Filter;
import org.apache.lucene.search.RangeFilter;
import org.hibernate.search.annotations.Factory;
import org.hibernate.search.annotations.Key;
import org.hibernate.search.filter.CachingWrapperFilter;
import org.hibernate.search.filter.FilterKey;
import org.hibernate.search.filter.StandardFilterKey;

public class DateFilterFactory extends Filter {
	private static final long serialVersionUID = -8707908223484149306L;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	private Calendar date;
	
	 public void setDate(Calendar date) {
	        this.date = date;
	    }
	    
	    @Key
	    public FilterKey getKey() {
	    	StandardFilterKey key = new StandardFilterKey();
	        key.addParameter(this.sdf.format(this.date.getTime()));
	        return key;
	    }
	    
	    @Factory
	    public Filter getFilter() {
	    	RangeFilter datesFilter = RangeFilter.More("publicationDate",  this.sdf.
	    			format(this.date.getTime()));
	        return new CachingWrapperFilter(datesFilter);
	    }

}

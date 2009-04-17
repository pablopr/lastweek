/*
 * @(#)ProvinceFilterFactory.java
 *
 * Copyright (c) 2.009, denodo technologies, S.L. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * denodo technologies, S.L. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with denodo technologies S.L.
 */
package com.marc.lastweek.business.util.lucene.filters;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.TermQuery;
import org.hibernate.search.annotations.Factory;
import org.hibernate.search.annotations.Key;
import org.hibernate.search.filter.CachingWrapperFilter;
import org.hibernate.search.filter.FilterKey;
import org.hibernate.search.filter.StandardFilterKey;

public class ProvinceFilterFactory extends Filter {
    private static final long serialVersionUID = -5149049706386687972L;
    
    private Long provinceId;

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
    
    @Key
    public FilterKey getKey() {
        StandardFilterKey key = new StandardFilterKey();
        key.addParameter( this.provinceId );
        return key;
    }
    
    @Factory
    public Filter getFilter() {
        Query query = new TermQuery( new Term("province.id", this.provinceId.toString() ) );
        return new CachingWrapperFilter( new QueryWrapperFilter(query) );
    }
}


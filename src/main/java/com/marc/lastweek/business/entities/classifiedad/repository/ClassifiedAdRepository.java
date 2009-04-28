/*
 * ClasifiedAdRepository.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.entities.classifiedad.repository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.views.classifiedad.FilterParameters;


@Repository
public class ClassifiedAdRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private Analyzer analyzer;
	
	private static final Logger logger = Logger.getLogger(ClassifiedAdRepository.class);

	/*
	 * 	NON INDEX BASED QUERIES
	 */
	@SuppressWarnings("unchecked")
	public List<ClassifiedAd> basicSearch(FilterParameters parameters, Calendar from, int start, int count) {
		Criteria criteriaQuery = advancedSearchQueyConstructor(parameters, from);
		criteriaQuery.setFirstResult(start);
		criteriaQuery.setFetchSize(count);
		criteriaQuery.addOrder(Order.desc("publicationDate"));
		return criteriaQuery.list();
	}

	public Integer basicSearchCountResults(FilterParameters parameters, Calendar from) {
		Criteria criteriaQuery = advancedSearchQueyConstructor(parameters, from);

		return new Integer(criteriaQuery.list().size());
	}

	private Criteria advancedSearchQueyConstructor(FilterParameters parameters, Calendar from) {

		Criteria criteriaQuery =
			this.sessionFactory.getCurrentSession().createCriteria(ClassifiedAd.class);

		if (parameters.getCategoryId()!=null) {
			criteriaQuery.createCriteria("category").add(Restrictions.eq("id", parameters.getCategoryId()));
		}
		if (parameters.getProvinceId()!=null) {
			criteriaQuery.createCriteria("province").add(Restrictions.eq("id", parameters.getProvinceId()));
		}
		if (parameters.getSubcategoryId()!=null) {
			criteriaQuery.createCriteria("subcategory").add(Restrictions.eq("id", parameters.getSubcategoryId()));
		}
		criteriaQuery.add(Restrictions.ge("publicationDate", from));
		criteriaQuery.add(Restrictions.eq("state", Integer.valueOf(ClassifiedAd.STATE_ACTIVE)));
		return criteriaQuery;
	}

	/*
	 * HIBERNATE SEARCH BASED METHODS
	 */
	public FullTextQuery getFullTextQuery(FilterParameters parameters, Calendar from){

		try {
			FullTextSession fullTextSession = Search.getFullTextSession(this.sessionFactory.getCurrentSession());

			QueryParser parser = new MultiFieldQueryParser(new String[]{"title", "description"},
					this.analyzer);
			parser.setDefaultOperator(QueryParser.AND_OPERATOR);
			Query query = parser.parse(parameters.getSearchString());

			FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query, ClassifiedAd.class);  

			// Filter by category, subcategory and province
			if (parameters.getCategoryId()!=null) {
				fullTextQuery.enableFullTextFilter("categoryFilter")
				.setParameter("categoryId", parameters.getCategoryId());
			}

			if (parameters.getSubcategoryId()!=null) {
				fullTextQuery.enableFullTextFilter("subcategoryFilter")
				.setParameter("subcategoryId", parameters.getSubcategoryId());
			}

			if (parameters.getProvinceId()!=null) {
				fullTextQuery.enableFullTextFilter("provinceFilter")
				.setParameter("provinceId", parameters.getProvinceId());
			}

			// Include only not expired items
			fullTextQuery.enableFullTextFilter("dateFilter").setParameter("date", from);

			return fullTextQuery;

		} catch (ParseException e) {
			throw new DataRetrievalFailureException("Error performing Lucene Query", e);
		}
	}


	public Integer indexBasedSearchCountResults (FilterParameters parameters, Calendar from) {
		logger.debug("building lucene query");
		FullTextQuery fullTextQuery = this.getFullTextQuery(parameters, from);
		return new Integer(fullTextQuery.list().size());
	}

	@SuppressWarnings("unchecked")
	public List<ClassifiedAd> indexBasedSearch (FilterParameters parameters, Calendar from, int start, int count) {    

		FullTextQuery fullTextQuery =  this.getFullTextQuery(parameters, from);

		SortField[] sortFields = new SortField[2];
		sortFields[0] = new SortField("publicationDate", SortField.STRING, true);
		sortFields[1] = SortField.FIELD_SCORE;
		Sort sort = new Sort(sortFields);
		fullTextQuery.setSort(sort);

		fullTextQuery.setFirstResult(start);
		fullTextQuery.setMaxResults(count);

		try {
			List<ClassifiedAd> result = fullTextQuery.list();
			return result;
		} catch (IllegalArgumentException e) {
			return new ArrayList<ClassifiedAd>();
		}
	}


	/*
	 * Indexes a given instance of Classified add
	 */
	public void index(ClassifiedAd classifiedAd) {
		FullTextSession fullTextSession = org.hibernate.search.Search
		.getFullTextSession(this.sessionFactory.getCurrentSession());

		fullTextSession.index(classifiedAd);
	}

}
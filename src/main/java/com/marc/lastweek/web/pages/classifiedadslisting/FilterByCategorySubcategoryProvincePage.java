/*
 * FilterByCategorySubcategoryProvincePage.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.classifiedadslisting;

import org.apache.wicket.PageParameters;

import com.marc.lastweek.business.views.classifiedad.FilterParameters;
import com.marc.lastweek.web.components.ClassifiedAdsListPanel;
import com.marc.lastweek.web.naming.PageParametersNaming;
import com.marc.lastweek.web.pages.BasePage;

public class FilterByCategorySubcategoryProvincePage extends BasePage {

	public FilterByCategorySubcategoryProvincePage(PageParameters parameters) {
		super(parameters);
		
		final String searchTerm = parameters
			.getString(PageParametersNaming.PARAM_NAME_SEARCH_TERM);
		final Long categoryId = Long.valueOf(parameters.getLong(
				PageParametersNaming.PARAM_NAME_CATEGORY_ID));
		final Long subcategoryId = Long.valueOf(parameters.getLong(
				PageParametersNaming.PARAM_NAME_SUBCATEGORY_ID));
		final Long provinceId = Long.valueOf(parameters.getLong(
				PageParametersNaming.PARAM_NAME_PROVINCE_ID));

		FilterParameters filterParameters = new FilterParameters();
		filterParameters.setSearchString(searchTerm);
		filterParameters.setCategoryId(categoryId);
		filterParameters.setSubcategoryId(subcategoryId);
		filterParameters.setProvinceId(provinceId);
		this.add(new ClassifiedAdsListPanel("classifiedAdsPanel", filterParameters));
		
	}
}

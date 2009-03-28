/*
 * FilterParameters.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.views.classifiedad;

public class FilterParameters {

	private String searchTerm;
	private Long categoryId;
	private Long subcategoryId;
	private Long provinceId;
	
	public String getSearchString() {
		return this.searchTerm;
	}
	
	public void setSearchString(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	public Long getCategoryId() {
		return this.categoryId;
	}
	
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public Long getSubcategoryId() {
		return this.subcategoryId;
	}
	
	public void setSubcategoryId(Long subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	
	public Long getProvinceId() {
		return this.provinceId;
	}
	
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	
	
		
}

/*
 * Filter.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.views.classifiedad;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Filter implements Serializable {

	private static final long serialVersionUID = -5599824484597770562L;
	
	private String filterType;
	private Long id;
	private String filterValue;
	private String filterName;
	public Filter(String filterType, Long id, String filterValue,
			String filterName) {
		super();
		this.filterType = filterType;
		this.id = id;
		this.filterValue = filterValue;
		this.filterName = filterName;
	}
	
	public String getFilterType() {
		return this.filterType;
	}
	
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFilterValue() {
		return this.filterValue;
	}
	
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	
	public String getFilterName() {
		return this.filterName;
	}
	
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}	
	
}

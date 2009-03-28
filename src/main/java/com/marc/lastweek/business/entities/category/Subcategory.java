/*
 * SubCategory.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.entities.category;

import java.util.LinkedHashSet;
import java.util.Set;

import com.marc.lastweek.business.entities.category.Category;
import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;

public class Subcategory {
	
	private Integer id;
	private String name;
	private Category parent;
	private Set<ClassifiedAd> classifiedAds = new LinkedHashSet<ClassifiedAd>(); 
	
	
	
	public Set<ClassifiedAd> getClassifiedAds() {
		return this.classifiedAds;
	}
	public void setClassifiedAds(Set<ClassifiedAd> classifiedAds) {
		this.classifiedAds = classifiedAds;
	}

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getParent() {
		return this.parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	
}

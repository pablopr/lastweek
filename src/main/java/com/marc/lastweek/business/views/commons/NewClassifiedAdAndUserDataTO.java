/*
 * NewClassifiedAdTO.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.views.commons;

import java.io.Serializable;

public class NewClassifiedAdAndUserDataTO implements Serializable {
	
	
	private static final long serialVersionUID = -1393594287491092763L;
	
	private Long categoryId;
	private Long subcategoryId;
	private String email = "";
	private String phone = "";
	private Double price = Double.valueOf("-1");
	private String title = "";
	private String description = "";
	private Long provinceId;
	private Integer source;
	private Integer flag;
	private Integer state;
	private String hashCode = "";
	
	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getHashCode() {
		return this.hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
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
	
	public String getEmail() {
		return this.email;
	}
    
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return this.phone;
	}
    
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Double getPrice() {
		return this.price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getProvinceId() {
		return this.provinceId;
	}
	
	
	
}

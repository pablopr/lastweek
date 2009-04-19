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

import org.apache.wicket.util.file.Folder;

import com.marc.lastweek.web.application.LastweekApplication;

public class NewClassifiedAdAndUserDataTO implements Serializable {
	
	
	private static final long serialVersionUID = -1393594287491092763L;
	
	public NewClassifiedAdAndUserDataTO() {
		super();
		this.temporalFolder = LastweekApplication.get().getImageService().createTemporalFolder();
	}

	private String categoryName;
	private String subcategoryName;
	private String provinceName;
	private Folder temporalFolder;
	private Long categoryId;
	private Long subcategoryId;
	private String email = "";
	private String phone = "";
	private Double price = Double.valueOf("0");
	private String title = "";
	private String description = "";
	private Long provinceId;
	private String hashCode = "";

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

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubcategoryName() {
		return this.subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Folder getTemporalFolder() {
		return this.temporalFolder;
	}

	public void setTemporalFolder(Folder temporalFolder) {
		this.temporalFolder = temporalFolder;
	}

		
}

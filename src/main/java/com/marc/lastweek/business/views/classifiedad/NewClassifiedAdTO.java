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
package com.marc.lastweek.business.views.classifiedad;

import java.io.Serializable;

import loc.marc.commons.business.util.transfer.TransferAsAssociation;
import loc.marc.commons.business.util.transfer.TransferAsAttribute;

import com.marc.lastweek.business.entities.category.Category;
import com.marc.lastweek.business.entities.category.Subcategory;
import com.marc.lastweek.business.entities.userdata.UserData;
import com.marc.lastweek.business.views.commons.NewClassifiedAdAndUserDataTO;

public class NewClassifiedAdTO implements Serializable {

	private static final long serialVersionUID = 6846761775612804953L;
	private Long categoryId;
	private Long subcategoryId;
	private Double price = Double.valueOf("-1");
	private String title = "";
	private String description = "";
	private Long userDataId;
	
	public NewClassifiedAdTO(NewClassifiedAdAndUserDataTO newClassifiedAdAndUserDataTO) {
		this.categoryId = newClassifiedAdAndUserDataTO.getCategoryId();
		this.subcategoryId = newClassifiedAdAndUserDataTO.getSubcategoryId();
		this.price = newClassifiedAdAndUserDataTO.getPrice();
		this.title = newClassifiedAdAndUserDataTO.getTitle();
		this.description = newClassifiedAdAndUserDataTO.getDescription();
	}
	
    @TransferAsAssociation(type=Category.class)
	public Long getCategoryId() {
		return this.categoryId;
	}
    
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
    @TransferAsAssociation(type=Subcategory.class)
	public Long getSubcategoryId() {
		return this.subcategoryId;
	}
    
	public void setSubcategoryId(Long subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	
    @TransferAsAttribute
	public Double getPrice() {
		return this.price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
    @TransferAsAttribute
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
    @TransferAsAttribute
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public void setUserDataId(Long userDataId) {
		this.userDataId = userDataId;
	}
	
	@TransferAsAssociation(type=UserData.class)
	public Long getUserDataId() {
		return this.userDataId;
	}
	
}

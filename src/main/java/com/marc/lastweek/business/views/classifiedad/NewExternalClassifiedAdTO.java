/*
 * NewExternalClassifiedAdTO.java
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
import java.util.Calendar;
import java.util.GregorianCalendar;

import loc.marc.commons.business.util.transfer.TransferAsAssociation;
import loc.marc.commons.business.util.transfer.TransferAsAttribute;

import com.marc.lastweek.business.entities.category.Category;
import com.marc.lastweek.business.entities.category.Subcategory;
import com.marc.lastweek.business.entities.province.Province;

public class NewExternalClassifiedAdTO implements Serializable {

	private static final long serialVersionUID = 7649654974723370513L;
	private Long categoryId;
	private Long subcategoryId;
	private Double price;
	private String title = "";
	private String description = "";
	private Calendar creationDate;
	private Calendar publicationDate;
	private Long provinceId;
	private Integer source;
	private String sourceURL;
	private Integer flag;
	private Integer state;	
	private String hashCode = "EXTERNAL";

	
	
	public NewExternalClassifiedAdTO(Long categoryId, Long subcategoryId,
			Double price, String title, String description,
			Calendar creationDate, Calendar publicationDate, Long provinceId,
			Integer source, String sourceURL, Integer flag, Integer state) {
		super();
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.price = price;
		this.title = title;
		this.description = description;
		if ( creationDate == null ) {
			this.creationDate = new GregorianCalendar();
		} else {
			this.creationDate = creationDate;
		}
		if ( publicationDate == null ) {
			this.publicationDate = new GregorianCalendar();
		} else {
			this.publicationDate = publicationDate;
		}
		
		this.provinceId = provinceId;
		this.source = source;
		this.sourceURL = sourceURL;
		this.flag = flag;
		this.state = state;
	}
	
    @TransferAsAssociation(property="category",type=Category.class)
	public Long getCategoryId() {
		return this.categoryId;
	}
    
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
    @TransferAsAssociation(property="subcategory",type=Subcategory.class)
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

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	@TransferAsAttribute
	public Calendar getCreationDate() {
		return this.creationDate;
	}

	public void setPublicationDate(Calendar publicationDate) {
		this.publicationDate = publicationDate;
	}

	@TransferAsAttribute
	public Calendar getPublicationDate() {
		return this.publicationDate;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	@TransferAsAssociation(property="province",type=Province.class)
	public Long getProvinceId() {
		return this.provinceId;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	@TransferAsAttribute
	public Integer getSource() {
		return this.source;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	@TransferAsAttribute
	public Integer getFlag() {
		return this.flag;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@TransferAsAttribute
	public Integer getState() {
		return this.state;
	}

	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}

	@TransferAsAttribute
	public String getSourceURL() {
		return this.sourceURL;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
	
	@TransferAsAttribute
	public String getHashCode() {
		return this.hashCode;
	}
	
}

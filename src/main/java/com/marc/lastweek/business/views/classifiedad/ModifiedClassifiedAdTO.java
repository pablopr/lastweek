/*
 * ModifiedClassifiedAddTO.java
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

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;

import loc.marc.commons.business.util.transfer.TransferAsAttribute;
import loc.marc.commons.business.util.transfer.TransferEntityIdentifier;


public class ModifiedClassifiedAdTO implements Serializable {

	private static final long serialVersionUID = -4409846329109762353L;
	
	private Long id;
	private String title;
	private String description;
	private Double price;
	private Integer flag;
	private Integer state;
	private String hashCode;
	
	public ModifiedClassifiedAdTO(ClassifiedAd classifiedAd) {
		this.id = classifiedAd.getId();
		this.description = classifiedAd.getDescription();
		this.flag = classifiedAd.getFlag();
		// TODO: recalculate hashcode
		this.hashCode = classifiedAd.getHashCode();
		this.price = classifiedAd.getPrice();
		this.state = classifiedAd.getState();
		this.title = classifiedAd.getTitle();
	}
	
	public ModifiedClassifiedAdTO(Long id, String title, String description,
			Double price, Integer flag, Integer state, String hashCode) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.flag = flag;
		this.state = state;
		this.hashCode = hashCode;
	}

	@TransferEntityIdentifier
	@TransferAsAttribute
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@TransferAsAttribute
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@TransferAsAttribute
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@TransferAsAttribute
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@TransferAsAttribute
	public String getHashCode() {
		return this.hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
}

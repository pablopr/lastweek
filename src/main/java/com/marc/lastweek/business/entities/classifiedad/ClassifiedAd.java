/*
 * ClassifiedAd.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.entities.classifiedad;

public class ClassifiedAd {
	
	private Integer id;
	private String title;
	private String description;
	private Double price;
	private String sourceURL;
	private Integer source;
	private Integer flag;
	private Integer state;
	private String hashCode;
	
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
	public Double getPrice() {
		return this.price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSourceURL() {
		return this.sourceURL;
	}
	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}
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
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

}


/*
 * User.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.entities.userdata;

import java.util.LinkedHashSet;
import java.util.Set;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;

public class UserData {

	private Integer id;
	private String name;
	private String email;
	private String phone;
	private Integer state;
	private Set<ClassifiedAd> classifiedAds = new LinkedHashSet<ClassifiedAd>(); 
	
	public Set<ClassifiedAd> getClassifiedAds() {
		return this.classifiedAds;
	}
	public void setClassifiedAds(Set<ClassifiedAd> classifiedAds) {
		this.classifiedAds = classifiedAds;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}

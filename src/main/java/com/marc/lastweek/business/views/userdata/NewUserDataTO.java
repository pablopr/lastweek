/*
 * NewUserDataTO.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.views.userdata;

import java.io.Serializable;

import loc.marc.commons.business.util.transfer.TransferAsAttribute;

import com.marc.lastweek.business.views.commons.NewClassifiedAdAndUserDataTO;


public class NewUserDataTO implements Serializable {
	
	private static final long serialVersionUID = -6863770140554188284L;
	private String name = "";
	private String email = "";
	private String phone = "";
	private Integer state = Integer.valueOf(0);
	
	public NewUserDataTO(NewClassifiedAdAndUserDataTO newClassifiedAndUserDataTo) {
		this.email = newClassifiedAndUserDataTo.getEmail();
		this.phone = newClassifiedAndUserDataTo.getPhone();
		this.name = newClassifiedAndUserDataTo.getName();
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
    @TransferAsAttribute
	public String getEmail() {
		return this.email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    @TransferAsAttribute
	public String getPhone() {
		return this.phone;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	@TransferAsAttribute
	public Integer getState() {
		return this.state;
	}
	
	@TransferAsAttribute
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}

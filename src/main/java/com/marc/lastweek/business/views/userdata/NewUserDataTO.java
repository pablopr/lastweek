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
	private String email = "";
	private String phone = "";
	
	public NewUserDataTO(NewClassifiedAdAndUserDataTO newClassifiedAndUserDataTo) {
		this.email = newClassifiedAndUserDataTo.getEmail();
		this.phone = newClassifiedAndUserDataTo.getPhone();
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
	
	
	
}

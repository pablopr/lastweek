/*
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.commons.exceptions;


public class BadWordFoundException extends OperationException {

	private static final long serialVersionUID = 4926322623173797575L;
	
	private String badWord;
	

    public BadWordFoundException(String msg, String bardWord) {
        super(msg);
        this.badWord = bardWord;
    }

	public String getBadWord() {
		return this.badWord;
	}

	public void setBadWord(String badWord) {
		this.badWord = badWord;
	}

}

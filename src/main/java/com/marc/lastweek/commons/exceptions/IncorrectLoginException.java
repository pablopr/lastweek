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


public class IncorrectLoginException extends OperationException {

    private static final long serialVersionUID = -1005867516615781313L;
    
    
    public IncorrectLoginException(String login) {
        super("Incorrect login attempt for user: " + login);
    }

}

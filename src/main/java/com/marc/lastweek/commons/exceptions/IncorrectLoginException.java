/*
 * Copyright (c) 2009, denodo technologies, S.L. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * denodo technologies, S.L. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with denodo technologies S.L.
 */
package com.marc.lastweek.commons.exceptions;


public class IncorrectLoginException extends OperationException {

    private static final long serialVersionUID = -1005867516615781313L;
    
    
    public IncorrectLoginException(String login) {
        super("Incorrect login attempt for user: " + login);
    }

}

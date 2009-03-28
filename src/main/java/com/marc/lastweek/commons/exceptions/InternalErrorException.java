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

import org.apache.commons.lang.exception.NestableRuntimeException;

public class InternalErrorException extends NestableRuntimeException {

    private static final long serialVersionUID = -5766573374927113957L;

    public InternalErrorException() {
        super();
    }

    public InternalErrorException(String msg, Throwable nested) {
        super(msg, nested);
    }

    public InternalErrorException(String msg) {
        super(msg);
    }

    public InternalErrorException(Throwable nested) {
        super(nested);
    }
    
}

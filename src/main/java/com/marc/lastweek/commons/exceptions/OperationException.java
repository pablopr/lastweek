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

import org.apache.commons.lang.exception.NestableException;

public class OperationException extends NestableException {


    private static final long serialVersionUID = 6241933231672390425L;

    public OperationException() {
        super();
    }

    public OperationException(String msg, Throwable nested) {
        super(msg, nested);
    }

    public OperationException(String msg) {
        super(msg);
    }

    public OperationException(Throwable nested) {
        super(nested);
    }

}

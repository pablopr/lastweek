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

/*
 * Copyright (c) 2009, denodo technologies, S.L. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * denodo technologies, S.L. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with denodo technologies S.L.
 */
package com.marc.lastweek.business.services.aaa;

import com.marc.lastweek.business.views.aaa.AuthenticatedUserData;
import com.marc.lastweek.commons.exceptions.IncorrectLoginException;

public interface AaaService {

    public AuthenticatedUserData loginUser(String login, String password)
            throws IncorrectLoginException;
    
}

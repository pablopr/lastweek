/*
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.services.aaa.impl;

import org.springframework.stereotype.Service;

import com.marc.lastweek.business.services.aaa.AaaService;
import com.marc.lastweek.business.views.aaa.AuthenticatedUserData;
import com.marc.lastweek.commons.exceptions.IncorrectLoginException;

@Service
public class AaaServiceImpl implements AaaService {

    //@Autowired
   // UserRepository userRepository;
     
    public AaaServiceImpl() {
        super();
    }

    public AuthenticatedUserData loginUser(String login, String password) 
            throws IncorrectLoginException {
        
//        User user = this.userRepository.getUserByLogin(login);
//        
//        if (user!=null) {
//            if (password.equals(user.getPassword())) {
//                return new AuthenticatedUserData(login, user.getChannel(), 
//                        user.getRoles());
//            }
//            throw new IncorrectLoginException(login);
//        } 
//        throw new IncorrectLoginException(login);
    	return new AuthenticatedUserData(login, null);
    }  
}

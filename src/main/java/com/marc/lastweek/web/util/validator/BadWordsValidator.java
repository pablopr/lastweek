/*
 * BadWordsValidator.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.util.validator;

import org.apache.log4j.Logger;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;

import com.marc.lastweek.business.entities.classifiedad.repository.ClassifiedAdRepository;
import com.marc.lastweek.business.util.badwords.BadWordsFilter;
import com.marc.lastweek.commons.exceptions.BadWordFoundException;

public class BadWordsValidator extends AbstractValidator { 
	
	private static final long serialVersionUID = -3954424224791909355L;
	private static final Logger logger = Logger.getLogger(ClassifiedAdRepository.class);


	@Override 
    protected void onValidate(IValidatable validatable) { 
    	try {
			BadWordsFilter.filterBadWords((String)validatable.getValue());
		} catch (BadWordFoundException e) {
			error(validatable, resourceKey());
			logger.info("Bad word found: " + (String)validatable.getValue());
		}
    } 

} 

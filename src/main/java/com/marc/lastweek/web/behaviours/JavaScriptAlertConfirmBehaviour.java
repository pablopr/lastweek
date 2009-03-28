/*
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.behaviours;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.Validate;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

/**
 * This class shows a JavaScript confirm window.
 */
public class JavaScriptAlertConfirmBehaviour extends AttributeModifier {

    private static final long serialVersionUID = 176252163308926780L;
    
    private static final String EVENT_NAME = "onclick";
    
    private final Object[] parameters; 
    private Component boundComponent = null;

    /**
     * Class constructor.
     * 
     * @param key key which contains the message to show in the confirm window.
     * @param parameters value of parameters the message has (if it has any).
     */
    public JavaScriptAlertConfirmBehaviour(final String key, final Object... parameters) {
        super(EVENT_NAME, true, new Model(key));
        Validate.notEmpty(key, "Key cannot be null");
        Validate.notNull(parameters, "Parameters cannot be null");
        this.parameters = ArrayUtils.clone(parameters);
    }
    
    
    @Override
    public void bind(Component component) {
        super.bind(component);
        this.boundComponent = component;
    }

    
    @Override
    protected String newValue(final String currentValue, final String replacementValue) {
        
        String key = replacementValue;
        
        StringResourceModel messageModel = 
            new StringResourceModel(key, this.boundComponent, null, this.parameters);
        
        String message = StringEscapeUtils.escapeJavaScript(messageModel.getString());
        
        if (currentValue != null && currentValue.length() != 0) {
            StringBuilder result = new StringBuilder(256);
            result.append("if( confirm('");
            result.append(message);
            result.append("') ){");
            result.append(currentValue);
            result.append("} else return false;");
            return (result.toString());
        }
        return ("return confirm('" + message + "')");
        
    }

    
}



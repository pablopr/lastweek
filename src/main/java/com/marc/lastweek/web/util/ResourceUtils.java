/*
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.util;

import org.apache.wicket.Component;
import org.apache.wicket.model.StringResourceModel;

/**
 * This class provides an internationalized string model for a component looking for
 * the given key rising from  wicket components hierarchy.
 *
 */
public class ResourceUtils {


    /**
     * Creates a model of a resource.
     * 
     * @param key key to search.
     * @param component initial component the search starts from.
     * @return a {@link StringResourceModel} which represents the model of the resource.
     */
    public static StringResourceModel createResourceModel(String key, Component component) {
        return createResourceModel(key, component, new Object[0]);
    }

    
    /**
     * Creates a model of a resource.
     * 
     * @param key key to search.
     * @param component initial component the search starts from.
     * @param parameters parameters of the message associated with the key.
     * @return a {@link StringResourceModel} which represents the model of the resource.
     */
    public static StringResourceModel createResourceModel(String key, Component component, Object... parameters) {
        return new StringResourceModel(key, component, null, parameters);
    }

    /**
     * Retrieves a <tt>string</tt> representation of a resource.
     * 
     * @param key key to search.
     * @param component initial component the search starts from.
     * @return a <tt>string</tt> representation of the resource.
     */
    public static String getResourceString(String key, Component component) {
        return createResourceModel(key, component).getString();
    }

    /**
     * Retrieves a <tt>string</tt> representation of a resource.
     * 
     * @param key key to search.
     * @param component initial component the search starts from.
     * @param parameters parameters of the message associated with the key.
     * @return a <tt>string</tt> representation of the resource.
     */
    public static String getResourceString(String key, Component component, Object... parameters) {
        return createResourceModel(key, component, parameters).getString();
    }

    
    
    private ResourceUtils() {
        super();
    }
    
}

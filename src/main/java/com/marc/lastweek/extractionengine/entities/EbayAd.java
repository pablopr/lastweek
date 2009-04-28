/*
 * EbayAd.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.extractionengine.entities;

public class EbayAd {
    
    public EbayAd(String title, String description, String place, String date,
            String price, String subcategory, String category, String url,
            String imgUrl) {
        super();
        this.title = title;
        this.description = description;
        this.place = place;
        this.date = date;
        this.price = price;
        this.subcategory = subcategory;
        this.category = category;
        this.url = url;
        this.imgUrl = imgUrl;
    }
    
    String title = null;
    String description = null;
    String place = null;
    String date = null;
    String price = null;
    String subcategory = null;
    String category = null;
    String url = null;
    String imgUrl;
    
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public String getPlace() {
        return this.place;
    }
    public String getDate() {
        return this.date;
    }
    public String getPrice() {
        return this.price;
    }
    public String getSubcategory() {
        return this.subcategory;
    }
    public String getCategory() {
        return this.category;
    }
    public String getUrl() {
        return this.url;
    }
    public String getImgUrl() {
        return this.imgUrl;
    }
   
}

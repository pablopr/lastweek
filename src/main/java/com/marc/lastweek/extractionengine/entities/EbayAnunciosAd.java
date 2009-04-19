/*
 * entities.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.extractionengine.entities;

public class EbayAnunciosAd {
	
	String title = null;
	String description = null;
	String place = null;
	String date = null;
	String price = null;
	String category = null;
	String url = null;
	String imgUrl;

	public EbayAnunciosAd(String title, String description, String place, String date, 
			String price, String category, String url, String imgUrl) {
		super();
		this.title = title;
		this.description = description;
		this.place = place;
		this.date = date;
		this.price = price;
		this.category = category;
		this.url = url;
		this.imgUrl = imgUrl;
	}
	
	public String getImgUrl() {
		return this.imgUrl;
	}

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
	public String getCategory() {
		return this.category;
	}
	public String getUrl() {
		return this.url;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("title = ");
		sb.append(this.title);
		sb.append("\n");
		sb.append("description = ");
		sb.append(this.description);
		sb.append("\n");
		sb.append("place = ");
		sb.append(this.place);
		sb.append("\n");
		sb.append("date = ");
		sb.append(this.date);
		sb.append("\n");
		sb.append("price = ");
		sb.append(this.price);
		sb.append("\n");
		sb.append("category = ");
		sb.append(this.category);
		sb.append("\n");
		sb.append("url = ");
		sb.append(this.url);
		sb.append("\n");
		sb.append("imgUrl = ");
		sb.append(this.imgUrl);
		sb.append("\n");
		return sb.toString();
	}

	
}

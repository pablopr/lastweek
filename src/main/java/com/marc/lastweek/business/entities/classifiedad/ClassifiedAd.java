/*
 * ClassifiedAd.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.entities.classifiedad;

import java.util.Calendar;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.FullTextFilterDef;
import org.hibernate.search.annotations.FullTextFilterDefs;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.marc.lastweek.business.entities.category.Category;
import com.marc.lastweek.business.entities.category.Subcategory;
import com.marc.lastweek.business.entities.province.Province;
import com.marc.lastweek.business.entities.userdata.UserData;
import com.marc.lastweek.business.util.lucene.bridges.CalendarBridge;
import com.marc.lastweek.business.util.lucene.filters.CategoryFilterFactory;
import com.marc.lastweek.business.util.lucene.filters.DateFilterFactory;
import com.marc.lastweek.business.util.lucene.filters.ProvinceFilterFactory;
import com.marc.lastweek.business.util.lucene.filters.StateFilter;
import com.marc.lastweek.business.util.lucene.filters.SubcategoryFilterFactory;

@Indexed
@FullTextFilterDefs( {
	@FullTextFilterDef(name = "stateFilter", impl = StateFilter.class),
	@FullTextFilterDef(name = "dateFilter", impl = DateFilterFactory.class),
    @FullTextFilterDef(name = "categoryFilter", impl = CategoryFilterFactory.class),
    @FullTextFilterDef(name = "subcategoryFilter", impl = SubcategoryFilterFactory.class),
    @FullTextFilterDef(name = "provinceFilter", impl = ProvinceFilterFactory.class)
})
public class ClassifiedAd {
	
	public static final int SOURCE_OUR = 0;
	public static final int SOURCE_EBAY = 1;
	public static final int SOURCE_LOQUO = 2;
	
	public static final int STATE_ACTIVE = 0;
	public static final int STATE_EXPIRED = 1;
	public static final int STATE_BANNED = 2;
	public static final int STATE_INACTIVE = 3;
	public static final int STATE_EXPIRING_TOMORROW = 4;

	@DocumentId
	private Long id;
	
	@Field(index=Index.TOKENIZED)
	private String title;
	
	@Field(index=Index.TOKENIZED)
	private String description;
	
	private Double price;
	
	private String sourceURL;
	
	private Integer source;
	
	private Integer flag;
	
	private Boolean showPhone;
	
	@Field(index=Index.UN_TOKENIZED)
	private Integer state;
	
	private String hashCode;
	
	@IndexedEmbedded
	private Category category;
	
	@IndexedEmbedded
	private Subcategory subcategory;
	
	@IndexedEmbedded
	private Province province;
	
	private UserData userData;
	
	private Calendar creationDate;
	
	@Field(index=Index.UN_TOKENIZED)
	@FieldBridge(impl = CalendarBridge.class)
	private Calendar publicationDate;
	
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return this.price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSourceURL() {
		return this.sourceURL;
	}
	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}
	public Integer getSource() {
		return this.source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getFlag() {
		return this.flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getHashCode() {
		return this.hashCode;
	}
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Category getCategory() {
		return this.category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Province getProvince() {
		return this.province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public UserData getUserData() {
		return this.userData;
	}
	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	public Subcategory getSubcategory() {
		return this.subcategory;
	}
	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}
	public Calendar getCreationDate() {
		return this.creationDate;
	}
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
	public Calendar getPublicationDate() {
		return this.publicationDate;
	}
	public void setPublicationDate(Calendar publicationDate) {
		this.publicationDate = publicationDate;
	}
	public Boolean getShowPhone() {
		return this.showPhone;
	}
	public void setShowPhone(Boolean showPhone) {
		this.showPhone = showPhone;
	}

	public long getTimeToExpireInMillis() {
		Calendar now = Calendar.getInstance();
		Calendar expirationDate = Calendar.getInstance();
		expirationDate.setTimeInMillis(this.publicationDate.getTimeInMillis());
		expirationDate.add(Calendar.WEEK_OF_YEAR, 1);
		return expirationDate.getTimeInMillis() - now.getTimeInMillis();
	}
}


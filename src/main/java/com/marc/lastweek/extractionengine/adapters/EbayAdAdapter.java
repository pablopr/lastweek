/*
 * EbayPisosAdapter.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.extractionengine.adapters;

import org.apache.commons.lang.ArrayUtils;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.views.classifiedad.NewExternalClassifiedAdTO;
import com.marc.lastweek.extractionengine.entities.EbayAd;

public class EbayAdAdapter {
	
	private final static String[] EBAY_PISOS_CATEGORIES = {
		"Alquiler",
		"Compartir",
		"En venta"
	};
	
	private final static long[] EBAY_PISOS_CATEGORIES_TO_LASTWEEK_CATEGORIES = {
		3,4,2
	};
//	
//	private final static String[] EBAY_PISOS_SUBCATEGORIES = {
//		"Casas Pisos\n Piso",
//		"Casas Pisos\n Chalet",
//		"Casas Pisos\n Casa",
//		"Locales",
//		"Oficinas",
//		"Plazas garaje",
//		"Terrenos" 
//	};
	
	
	public final static NewExternalClassifiedAdTO adapt(EbayAd ebayAd){
		long categoryId = EBAY_PISOS_CATEGORIES_TO_LASTWEEK_CATEGORIES[ArrayUtils.indexOf(EBAY_PISOS_CATEGORIES, ebayAd.getCategory().trim())];
		Double price = null;
		try {
				price = Double.valueOf(ebayAd.getPrice().replaceFirst("EUR", ""));
		} catch (Exception e) {
			price = Double.valueOf(0);
		}
		
		Long provinceId = Long.valueOf(1); 
		
		return new NewExternalClassifiedAdTO(
				Long.valueOf(categoryId),
				null,
				price,
				ebayAd.getTitle(),
				ebayAd.getDescription(),
				null,
				null,
				provinceId,
				Integer.valueOf(ClassifiedAd.SOURCE_EBAY),
				ebayAd.getUrl(),
				Integer.valueOf(0),
				Integer.valueOf(ClassifiedAd.STATE_ACTIVE));
	}
}

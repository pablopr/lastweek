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
import com.marc.lastweek.extractionengine.entities.EbayPisosAd;

public class EbayPisosAdapter {
	
	private final static String[] EBAY_PISOS_CATEGORIES = {
		"Alquiler",
		"Compartir",
		"En venta"
	};
	
//	private final static int[] EBAY_PISOS_CATEGORIES_TO_LASTWEEK_CATEGORIES = {
//		3,4,2
//	};
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
	
	
	public final static NewExternalClassifiedAdTO adapt(EbayPisosAd ebayPisosAd){
		Long categoryId = Long.valueOf(ArrayUtils.indexOf(EBAY_PISOS_CATEGORIES, ebayPisosAd.getCategory().trim()));
		Double price = null;
		try {
				price = Double.valueOf(ebayPisosAd.getPrice().replaceFirst("EUR", ""));
		} catch (Exception e) {
			price = Double.valueOf(0);
		}
		
		Long provinceId = Long.valueOf(1); 
		
		return new NewExternalClassifiedAdTO(
				categoryId,
				null,
				price,
				ebayPisosAd.getTitle(),
				ebayPisosAd.getDescription(),
				null,
				null,
				provinceId,
				Integer.valueOf(ClassifiedAd.SOURCE_EBAY),
				ebayPisosAd.getUrl(),
				Integer.valueOf(0),
				Integer.valueOf(ClassifiedAd.STATE_ACTIVE));
	}
}

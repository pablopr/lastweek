/*
 * BadWordsFilter.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.util.badwords;

import com.marc.lastweek.commons.exceptions.BadWordFoundException;

public final class BadWordsFilter {

    /**
     * Array of all the bad words to filter.
     */
    private static final String [] filterList = new String[]{"cabron","puta"};


    /**
     * Filters out bad words.
     * @throws BadWordFoundException 
     */
    public static void filterBadWords(String str) throws BadWordFoundException {
    	 String lower = str.toLowerCase();
         for (int i=0; i < filterList.length; i++) {
             String badWord = filterList[i];
             
             /* Before felix said this is not optimum,
                I've read indexOf uses some reasonably efficient
               algorithm, such as Knuth-Morris-Pratt. */
             if (lower.indexOf(badWord)!=-1){
            	 	throw new BadWordFoundException("BadWord found at current text", badWord);
             }
         }
    }



}

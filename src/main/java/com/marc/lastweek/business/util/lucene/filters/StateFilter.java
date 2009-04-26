/*
 * StateFilter.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.util.lucene.filters;

import java.io.IOException;
import java.util.BitSet;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.Filter;

public class StateFilter extends Filter {
	private static final long serialVersionUID = -821783538889248519L;
	
	@Override
	public BitSet bits(IndexReader indexreader) throws IOException {
		BitSet bitSet = new BitSet(indexreader.maxDoc());
        TermDocs termDocs = indexreader
        	.termDocs(new Term("state", "0"));
        while (termDocs.next()) {
            bitSet.set(termDocs.doc());
        }
        return bitSet;
	}
}

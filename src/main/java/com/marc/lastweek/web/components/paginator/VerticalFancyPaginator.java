/*
 * VerticalFancyPaginator.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components.paginator;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationIncrementLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class VerticalFancyPaginator extends Panel {
	private static final long serialVersionUID = 2668217261151422784L;

	public VerticalFancyPaginator(String id, final IPageable pageableList) {
		super(id);
		
		this.setOutputMarkupId(true);
		
		System.out.println("current: "+pageableList.getCurrentPage());
		
		WebMarkupContainer paginatorBox = new WebMarkupContainer("paginatorBox"){
			private static final long serialVersionUID = -8245214604885147260L;

			@Override
			public boolean isVisible() {
				return pageableList.getPageCount()>1;
			}
		};
		this.add(paginatorBox);
		
		 
		PagingNavigationLink firstPageLink = new PagingNavigationLink("firstPageLink", pageableList, 0);
		paginatorBox.add(firstPageLink);
		PagingNavigationLink lastPageLink = new PagingNavigationLink("lastPageLink", pageableList, 
				pageableList.getPageCount()-1);
		lastPageLink.add(new Label("lastPageLabel", new Model(Integer.valueOf(pageableList.getPageCount()))));
		paginatorBox.add(lastPageLink);
		
		
		// Before current page
		WebMarkupContainer beforeCurrenPageItem = new WebMarkupContainer("beforeCurrentPageItem"){
			private static final long serialVersionUID = -5876391261093967497L;
			@Override
			public boolean isVisible() {
				return pageableList.getCurrentPage()>=2;
			}
		};
		beforeCurrenPageItem.setOutputMarkupPlaceholderTag(true);
		PagingNavigationIncrementLink beforeCurrentPageLink = new PagingNavigationIncrementLink("beforeCurrentPageLink", 
				pageableList, -1);
		beforeCurrentPageLink.add(new Label("beforeCurrentPageLabel", new LoadableDetachableModel(){
			private static final long serialVersionUID = -3157979806031118522L;
			@Override
			protected Object load() {
				return Integer.valueOf(pageableList.getCurrentPage());
			}
		}));
		beforeCurrenPageItem.add(beforeCurrentPageLink);
		paginatorBox.add(beforeCurrenPageItem);
		
		
		// Current page
		WebMarkupContainer currenPageItem = new WebMarkupContainer("currentPageItem"){
			private static final long serialVersionUID = -9186596865047783422L;
			@Override
			public boolean isVisible() {
				return pageableList.getCurrentPage()>=1 && 
					pageableList.getCurrentPage()!=pageableList.getPageCount()-1;
			}
		};
		currenPageItem.setOutputMarkupPlaceholderTag(true);
		currenPageItem.add(new Label("currentPageLabel", new LoadableDetachableModel(){
			private static final long serialVersionUID = 393829273382282502L;
			@Override
			protected Object load() {
				return Integer.valueOf( pageableList.getCurrentPage() + 1 );
			}
		}));
		paginatorBox.add(currenPageItem);
		
		
		// After CurrentPage
		WebMarkupContainer afterCurrenPageItem = new WebMarkupContainer("afterCurrentPageItem"){
			private static final long serialVersionUID = 7079913736005869990L;
			@Override
			public boolean isVisible() {
				return  pageableList.getCurrentPage()<(pageableList.getPageCount()-2);
			}
		};
		afterCurrenPageItem.setOutputMarkupPlaceholderTag(true);
		PagingNavigationIncrementLink afterCurrentPageLink = new PagingNavigationIncrementLink("afterCurrentPageLink", 
				pageableList, 1);
		afterCurrentPageLink.add(new Label("afterCurrentPageLabel", new LoadableDetachableModel(){
			private static final long serialVersionUID = 8932718471553075655L;

			@Override
			protected Object load() {
				return Integer.valueOf( pageableList.getCurrentPage() + 2 );
			}
		}));
		afterCurrenPageItem.add(afterCurrentPageLink);
		paginatorBox.add(afterCurrenPageItem);

		

		// Two After CurrentPage
		WebMarkupContainer twoAfterCurrenPageItem = new WebMarkupContainer("twoAfterCurrentPageItem"){
            private static final long serialVersionUID = 572789655450253585L;

            @Override
			public boolean isVisible() {
				return  pageableList.getCurrentPage()<(pageableList.getPageCount()-3);
			}
		};
		twoAfterCurrenPageItem.setOutputMarkupPlaceholderTag(true);
		PagingNavigationIncrementLink twoAfterCurrentPageLink = new PagingNavigationIncrementLink("twoAfterCurrentPageLink", 
				pageableList, 2);
		twoAfterCurrentPageLink.add(new Label("twoAfterCurrentPageLabel", new LoadableDetachableModel(){
			private static final long serialVersionUID = 8932718471553075655L;

			@Override
			protected Object load() {
				return Integer.valueOf( pageableList.getCurrentPage() + 3 );
			}
		}));
		twoAfterCurrenPageItem.add(twoAfterCurrentPageLink);
		paginatorBox.add(twoAfterCurrenPageItem);
		
		// Previous and next links
		WebMarkupContainer previousPageItem = new WebMarkupContainer("previousPageItem"){
			private static final long serialVersionUID = -5827228260699397983L;

			@Override
			public boolean isVisible() {
				return pageableList.getCurrentPage()>0;
			}
		};
		previousPageItem.add( new PagingNavigationIncrementLink("previousPageLink", 
				pageableList, -1));
		previousPageItem.setOutputMarkupPlaceholderTag(true);
		paginatorBox.add(previousPageItem);
		
		WebMarkupContainer nextPageItem = new WebMarkupContainer("nextPageItem"){
			private static final long serialVersionUID = -5827228260699397983L;

			@Override
			public boolean isVisible() {
				return pageableList.getCurrentPage()<(pageableList.getPageCount()-1);
			}
		};
		nextPageItem.add( new PagingNavigationIncrementLink("nextPageLink", 
				pageableList, 1));
		nextPageItem.setOutputMarkupPlaceholderTag(true);
		paginatorBox.add(nextPageItem);
	}

}

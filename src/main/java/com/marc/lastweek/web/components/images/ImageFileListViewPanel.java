/*
 * ImageFileListView.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components.images;

import java.io.File;
import java.util.List;

import org.apache.wicket.extensions.markup.html.image.resource.ThumbnailImageResource;
import org.apache.wicket.markup.html.WebResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;

import com.marc.lastweek.web.application.LastweekApplication;


public class ImageFileListViewPanel extends Panel{

	private static final long serialVersionUID = -6023857527416347119L;
	private static final int IMAGE_THUMBNAIL_SIZE = 100;
	protected final Folder dirPath;
	
	public ImageFileListViewPanel(String id, Folder dirPath) {
		super(id);
		this.dirPath = dirPath;
		this.setOutputMarkupId(true);
		
		ImageFileListView fileListView = new ImageFileListView("fileList", new LoadableDetachableModel(){
			private static final long serialVersionUID = 4896378814518090123L;

			@Override
			protected List<File> load(){
				return 	LastweekApplication.get().getImageService().getAllTemporalFiles(ImageFileListViewPanel.this.dirPath);
			}
		});
		
		this.add(fileListView);
	}

	private class ImageFileListView extends ListView{
		private static final long serialVersionUID = 3201538754791639716L;

		public ImageFileListView(String name, final IModel files){
			super(name, files);
			this.setOutputMarkupId(true);
		}

		@Override
		protected void populateItem(ListItem listItem) {
			final File file = (File)listItem.getModelObject();
			
			listItem.add(new NonCachingImage("image",
                    new AbstractReadOnlyModel() {
						private static final long serialVersionUID = -3793771756226385126L;

							@Override
                            public Object getObject() {
                                    return new ThumbnailImageResource(new ImageFileResource(file), IMAGE_THUMBNAIL_SIZE);
                            }
                    })
			);
			
			listItem.add(new Label("file", file.getName()));
			listItem.add(new Link("delete"){
				private static final long serialVersionUID = -346244936373700794L;
				@Override
				public void onClick(){
					Files.remove(file);
					info("Has borrado " + file.getName());
				}
			});
		}
		
		private class ImageFileResource extends WebResource{
			private static final long serialVersionUID = -4393593327489597112L;
			private File file;
			
			public ImageFileResource(File file) {
				super();
				this.file = file;
			}

			@Override
			public IResourceStream getResourceStream() {
				return new FileResourceStream(this.file);
			}
			
			public void setFile(File file){
				this.file = file;
			}
			
		}
	}
}

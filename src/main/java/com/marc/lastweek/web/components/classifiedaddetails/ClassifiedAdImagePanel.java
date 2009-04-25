/*
 * ClassifiedAdImagePanel.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components.classifiedaddetails;

import org.apache.wicket.extensions.markup.html.image.resource.ThumbnailImageResource;
import org.apache.wicket.markup.html.WebResource;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import java.io.File;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;

public class ClassifiedAdImagePanel extends Panel {

	private static final long serialVersionUID = -7951715575454708405L;
	private static final int IMAGE_THUMBNAIL_SIZE = 200;
	
	public ClassifiedAdImagePanel(String id, final File file) {
		super(id);
		this.add(new NonCachingImage("image", new AbstractReadOnlyModel() {
			
			private static final long serialVersionUID = 2231398467162415338L;

			@Override
			public Object getObject() {
				return new ThumbnailImageResource(new ImageFileResource(file),
						IMAGE_THUMBNAIL_SIZE);
			}
		}));
	}
	
	private class ImageFileResource extends WebResource{
		
		private static final long serialVersionUID = 6296035402530112497L;
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

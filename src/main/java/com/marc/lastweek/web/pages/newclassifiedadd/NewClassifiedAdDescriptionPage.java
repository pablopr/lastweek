/*
 * NewClassifiedAdDescriptionPage.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.newclassifiedadd;

import java.io.File;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.validation.validator.StringValidator;

import com.marc.lastweek.business.views.commons.NewClassifiedAdAndUserDataTO;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.components.jquerytexteditor.JQueryTextEditor;
import com.marc.lastweek.web.components.upload.UploadPanel;
import com.marc.lastweek.web.util.ResourceUtils;

public class NewClassifiedAdDescriptionPage extends NewClassifiedAdPage{
	
	protected FileListDiv fileListDiv;
	
	public NewClassifiedAdDescriptionPage(NewClassifiedAdAndUserDataTO newClassifiedAdTO) {
		super(newClassifiedAdTO);
		this.newClassifiedAdTO = newClassifiedAdTO;
		
		WebMarkupContainer descriptionDiv = new WebMarkupContainer("descriptionDiv");
		
		UploadPanel uploadPanel = new UploadPanel("uploadImageAjax"){ 
			private static final long serialVersionUID = 1L;

			@Override 
			public String onFileUploaded(FileUpload upload) { 

				//TODO move to service and use imageUtils
				if (upload != null){
					LastweekApplication.get().getImageService().saveTemporalImage(upload, NewClassifiedAdDescriptionPage.this.newClassifiedAdTO.getImageRandomDir());
				}
				return ""; 
			} 

			@Override 
			public void onUploadFinished(AjaxRequestTarget target, String filename, String newFileUrl) { 
				//when upload is finished, will be called 
				target.addComponent(NewClassifiedAdDescriptionPage.this.fileListDiv); 
				
			} 
		};
		
		descriptionDiv.add(uploadPanel);
		
//		List<File> fileList = LastweekApplication.get().getImageService().getAllTemporalFiles(NewClassifiedAdDescriptionPage.this.newClassifiedAdTO.getImageRandomDir());
		
//		ImageFileListViewPanel fileListViewPanel = new ImageFileListViewPanel("fileListViewPanel", fileList);
		
//		this.fileListDiv = new FileListDiv("fileListDiv");
//		this.fileListDiv.add(fileListViewPanel);
//		descriptionDiv.add(this.fileListDiv);

		DescriptionForm descriptionForm =  new DescriptionForm("descriptionForm", new LoadableDetachableModel(){
			private static final long serialVersionUID = 4896378814518090123L;
			@Override
			protected List<File> load(){
				return LastweekApplication.get().getImageService().getAllTemporalFiles(NewClassifiedAdDescriptionPage.this.newClassifiedAdTO.getImageRandomDir());
			}
		});
		
		descriptionDiv.add(descriptionForm);
		
		
		this.add(descriptionDiv);
	}
	

	private class FileListDiv extends WebMarkupContainer{
		private static final long serialVersionUID = 7058344388159207398L;

		public FileListDiv(String id) {
			super(id);
			this.setOutputMarkupId(true);
			this.setOutputMarkupPlaceholderTag(true);
		}
	}

	
	private class DescriptionForm extends Form {
		private static final long serialVersionUID = 9053897905303403343L;
		protected final TextField price;
		protected final RequiredTextField title;
		protected final JQueryTextEditor description;


		public DescriptionForm(String id, IModel model) {
			super(id, model);
			setMultiPart(true);

			this.price = new TextField("price", new Model(NewClassifiedAdDescriptionPage.this.newClassifiedAdTO.getPrice()), Double.class);
			this.price.setLabel(ResourceUtils.createResourceModel("descriptionpanel.form.price", NewClassifiedAdDescriptionPage.this)); 

			this.title = new RequiredTextField("title", new Model(NewClassifiedAdDescriptionPage.this.newClassifiedAdTO.getTitle()));
			this.description = new JQueryTextEditor("description", new Model(NewClassifiedAdDescriptionPage.this.newClassifiedAdTO.getDescription()));
			this.description.add(StringValidator.lengthBetween(5, 510));
			this.description.setLabel(ResourceUtils.createResourceModel("descriptionpanel.form.description", NewClassifiedAdDescriptionPage.this));
			this.description.setRequired(true);
			add(this.price);
			add(this.title);
			add(this.description);
			

			setMaxSize(Bytes.kilobytes(100));
		}


		@Override
		protected void onSubmit() {
			LastweekApplication.get().getImageService().saveAllImages(NewClassifiedAdDescriptionPage.this.newClassifiedAdTO.getImageRandomDir());
			
			NewClassifiedAdDescriptionPage.this.newClassifiedAdTO.setPrice(Double.valueOf(DescriptionForm.this.price.getModelObjectAsString()));
			NewClassifiedAdDescriptionPage.this.newClassifiedAdTO.setTitle(DescriptionForm.this.title.getModelObjectAsString());
			NewClassifiedAdDescriptionPage.this.newClassifiedAdTO.setDescription(DescriptionForm.this.description.getModelObjectAsString());
			
			this.setResponsePage(new NewClassifiedAdUserDataPage(NewClassifiedAdDescriptionPage.this.newClassifiedAdTO));

			super.onSubmit();
		}

	}


}

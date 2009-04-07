/*
 * AjaxFileUploadForm.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.lang.Bytes;

import com.marc.lastweek.web.application.LastweekApplication;

//TODO we are not using this class. 
@Deprecated
public class AjaxFileUploadFormPanel  extends Panel{
	
	private static final long serialVersionUID = 8654242582786704246L;


	public AjaxFileUploadFormPanel(String id) {
		super(id);
		
		/* Java code */ 
		this.setOutputMarkupId(true); 
		
		final UploadTemporalFilesForm ajaxUploadForm = new UploadTemporalFilesForm("fileUploadForm");
//        ajaxUploadForm.add(new UploadProgressBar("progress", ajaxUploadForm));
        this.add(ajaxUploadForm);
			
		this.add(new Label("dir", AjaxFileUploadFormPanel.this.getUploadFolder().getAbsolutePath()));
		FileListView fileListView = new FileListView("fileList", new LoadableDetachableModel(){
		private static final long serialVersionUID = 4896378814518090123L;
		@Override
            protected List<File> load(){
                return Arrays.asList(getTemporalUploadFolder().listFiles());
            }
        });
        this.add(fileListView);
		
	}
	
	private class UploadTemporalFilesForm extends Form {
		
		private static final long serialVersionUID = 2713401468137390764L;
		
		protected final FileUploadField fileUploadField =  new FileUploadField("fileInput");
		
	    public UploadTemporalFilesForm(String id) {
	        super(id);
	        setMultiPart(true);
	        
	        
	        add(this.fileUploadField);
	        setMaxSize(Bytes.kilobytes(100));
	        
	        add(new AjaxSubmitLink("save", UploadTemporalFilesForm.this ) { 

	        	private static final long serialVersionUID = 1L;

				@Override 
	            protected void onSubmit(AjaxRequestTarget target, Form form) { 
	            	
	                target.addComponent(AjaxFileUploadFormPanel.this); 
	            } 
	        }); 
	    }


		@Override
		protected void onDetach() {
			// TODO Auto-generated method stub
			super.onDetach();
		}

		@Override
		protected void onSubmit() {
			final FileUpload upload = this.fileUploadField.getFileUpload();
        	//TODO move to service and use imageUtils
            if (upload != null){
            	File newFile = new File(getTemporalUploadFolder(), upload.getClientFileName());
        		
                // Check new file, delete if it allready existed
            	AjaxFileUploadFormPanel.this.checkFileExists(newFile);
                try{
                    // Save to new file
                    newFile.createNewFile();
                    upload.writeTo(newFile);

                    AjaxFileUploadFormPanel.this.info("saved file: " + upload.getClientFileName());
                }
                catch (Exception e) {
                    throw new IllegalStateException("Unable to write file");
                }
            }
		}
	    
	    
	}
	
	
	private class FileListView extends ListView{
		private static final long serialVersionUID = 3201538754791639716L;

        public FileListView(String name, final IModel files){
            super(name, files);
        }

        @Override
        protected void populateItem(ListItem listItem) {
            final File file = (File)listItem.getModelObject();
            listItem.add(new Label("file", file.getName()));
            listItem.add(new Link("delete"){
				private static final long serialVersionUID = -346244936373700794L;
				@Override
                public void onClick(){
                    Files.remove(file);
                    info("Deleted " + file);
                }
            });
        }
    }
	
	protected Folder getTemporalUploadFolder(){
        return ((LastweekApplication)Application.get()).getTemporalUploadFolder() ;
    }
	

	protected Folder getUploadFolder(){
        return ((LastweekApplication)Application.get()).getUploadFolder();
    }
	
	protected void checkFileExists(File newFile){
        if (newFile.exists()){
            // Try to delete the file
            if (!Files.remove(newFile)){
                throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());
            }
        }
    }

}

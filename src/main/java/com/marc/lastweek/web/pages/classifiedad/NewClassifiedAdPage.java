/*
 * NewClassifiedAd.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.classifiedad;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.marc.lastweek.business.entities.category.Category;
import com.marc.lastweek.business.entities.category.Subcategory;
import com.marc.lastweek.business.entities.province.Province;
import com.marc.lastweek.business.views.commons.NewClassifiedAdAndUserDataTO;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.models.LoadableCategoriesListModel;
import com.marc.lastweek.web.models.LoadableProvincesListModel;
import com.marc.lastweek.web.pages.BasePage;
import com.marc.lastweek.web.util.ResourceUtils;

public class NewClassifiedAdPage extends BasePage {


	private static final long serialVersionUID = -7976014359463033532L;


	protected NewClassifiedAdAndUserDataTO newClassifiedAdTO = new NewClassifiedAdAndUserDataTO();
	protected String categoryName;
	protected String subcategoryName;
	protected String provinceName;
	protected ProvincePanel provincePanel ;
	protected CategoryPanel categoryPanel;
	protected SubcategoryPanel subcategoryPanel;
	protected DescriptionPanel descriptionPanel;
	protected UserDataPanel userDataPanel;
	protected CheckCreatedAdPanel checkCreatedAdPanel;
	protected FeedbackPanel feedbackPanel;
	protected List<String> temporalFiles ;
	
	
	
	private class ProvincePanel extends Panel {

		private static final long serialVersionUID = -7936905418698062099L;

		public ProvincePanel(String id) {
			super(id);
			this.add(new ListView("provinceList", 
					new LoadableProvincesListModel()) {

				private static final long serialVersionUID = -5843308083402561880L;

				@Override
				protected void populateItem(ListItem listItem) {
					Province province = (Province)listItem.getModelObject();
					final Long provinceId = province.getId();
					final String provinceName = province.getName();
					
					Link provinceLink = new Link("provinceLink") {

						private static final long serialVersionUID = 1L;

						@Override
						public void onClick() {
							NewClassifiedAdPage.this.newClassifiedAdTO.setProvinceId(provinceId);
							NewClassifiedAdPage.this.provinceName = provinceName;
							ProvincePanel.this.setVisible(false);
							
							NewClassifiedAdPage.this.categoryPanel.setVisible(true);
						}
					};

					provinceLink.add(new Label("provinceLabel",provinceName));
					listItem.add(provinceLink);
		        }
			});
		}

	}


	
	private class CategoryPanel extends Panel {

		private static final long serialVersionUID = -1266334687818119105L;

		public CategoryPanel(String id) {
			super(id);
			CategoryPanel.this.setVisible(false);
			this.add(new ListView("category", new LoadableCategoriesListModel()) {
				
				private static final long serialVersionUID = 6730094093871495627L;
 
				@Override
				protected void populateItem(ListItem item) {
					Category  category = (Category)item.getModelObject();
					final Long categoryId = category.getId();
					final String categoryName = category.getName();
					final int countSubcategories = category.getSubcategories().size();
					
					Link categoryLink = new Link("categoryLink") {

						private static final long serialVersionUID = -1462588672710233585L;


						@Override
						public void onClick() {
							NewClassifiedAdPage.this.newClassifiedAdTO.setCategoryId(categoryId);
							NewClassifiedAdPage.this.categoryName = categoryName;
							CategoryPanel.this.setVisible(false);
							
							if (countSubcategories == 0){
								NewClassifiedAdPage.this.descriptionPanel.setVisible(true);
							}
							else{
								NewClassifiedAdPage.this.subcategoryPanel.setCategoryId(categoryId);
								NewClassifiedAdPage.this.subcategoryPanel.setVisible(true);
							}
						}

					};

					categoryLink.add(new Label("categoryLabel",categoryName));
					item.add(categoryLink);
				}

			});
		}

	}


	private class SubcategoryPanel extends Panel {

		private static final long serialVersionUID = 7478601607373577524L;
		protected Long categoryId ;

		public  SubcategoryPanel(final String id) {
			super(id);
			SubcategoryPanel.this.setVisible(false);
			add(new ListView("subcategory",new LoadableDetachableModel() {

				private static final long serialVersionUID = 5737843643543228915L;

				@Override
				protected Object load() {
					Map<String,Object> parameters = new HashMap<String, Object>();
			        parameters.put("categoryId", SubcategoryPanel.this.categoryId);

					return LastweekApplication.get().getGeneralService().queryForList(Subcategory.class, "findSubcategoriesByCategoryId", parameters);
				}
				
			}){

				private static final long serialVersionUID = -5397807417164068536L;

				@Override
				protected void populateItem(final ListItem item) {
					Subcategory subcategory = (Subcategory)item.getModelObject();
					final Long subcategoryId = subcategory.getId();
					final String subcategoryName = subcategory.getName();
					Link categoryLink = new Link("subcategoryLink") {

						private static final long serialVersionUID = -1462588672710233585L;

						@Override
						public void onClick() {
							NewClassifiedAdPage.this.newClassifiedAdTO.setSubcategoryId(subcategoryId);
							NewClassifiedAdPage.this.subcategoryName = subcategoryName;
							SubcategoryPanel.this.setVisible(false);
							NewClassifiedAdPage.this.descriptionPanel.setVisible(true);

						}

					};				
					categoryLink.add(new Label("subcategoryLabel",subcategoryName));
					item.add(categoryLink);

				}

			});

		}
		public void setCategoryId(Long categoryId){
			this.categoryId = categoryId;
		}
	}
	
	
	
	private class DescriptionPanel extends Panel {

		private static final long serialVersionUID = 3616303310835436664L;

		public  DescriptionPanel(final String id) {
			super(id);
			DescriptionPanel.this.setVisible(false);
			DescriptionForm descriptionForm =  new DescriptionForm("descriptionForm", new LoadableDetachableModel(){
				private static final long serialVersionUID = 4896378814518090123L;
				@Override
			     protected List<String> load(){
			         return NewClassifiedAdPage.this.temporalFiles;
			     }
 	        });
			
 			this.add(descriptionForm);
 			
 			final UploadTemporalFilesForm ajaxUploadForm = new UploadTemporalFilesForm("fileUploadForm");
	        ajaxUploadForm.add(new UploadProgressBar("progress", ajaxUploadForm));
	        this.add(ajaxUploadForm);
 			
 			this.add(new Label("dir", NewClassifiedAdPage.this.getUploadFolder().getAbsolutePath()));
 			FileListView fileListView = new FileListView("fileList", new LoadableDetachableModel(){
				private static final long serialVersionUID = 4896378814518090123L;
				@Override
 	            protected List<File> load(){
 	                return Arrays.asList(getUploadFolder().listFiles());
 	            }
 	        });
 	        add(fileListView);
		}
	
	}
	

	
	private class UserDataPanel extends Panel {

		private static final long serialVersionUID = -7322178890866291550L;

		public  UserDataPanel(final String id) {
			super(id);
			UserDataPanel.this.setVisible(false);							
 			add(new UserDataForm("userDataForm"));
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
	
	private class DescriptionForm extends Form {
		private static final long serialVersionUID = 9053897905303403343L;
		protected final TextField price;
		protected final RequiredTextField title;
		protected final TextArea description;
	    

	    public DescriptionForm(String id, IModel model) {
	        super(id, model);
	        setMultiPart(true);
	        
	        this.price = new TextField("price", new Model(NewClassifiedAdPage.this.newClassifiedAdTO.getPrice()), Double.class);
	        this.price.setLabel(ResourceUtils.createResourceModel("newclassifiedad.form.price", NewClassifiedAdPage.this)); 

	        this.title = new RequiredTextField("title", new Model(NewClassifiedAdPage.this.newClassifiedAdTO.getTitle()));
	        this.description = new TextArea("description", new Model(NewClassifiedAdPage.this.newClassifiedAdTO.getDescription()));
	        this.description.setRequired(true);
	        add(this.price);
	        add(this.title);
	        add(this.description);
	        
	        add(new SubmitLink("submitLink"){
	        	
				private static final long serialVersionUID = 1377358372101952950L;

				@Override
				public void onSubmit() {
	        		
	        		List<String> files = NewClassifiedAdPage.this.temporalFiles;
	        		
					if (files != null) {
		            	
						for (String fileName : files){
							// Create a new file
			                File newFile = new File(getUploadFolder(), fileName);
			                File tempFile = new File(getTemporalUploadFolder(), fileName);
			                // Check new file, delete if it allready existed
			                NewClassifiedAdPage.this.checkFileExists(newFile);
			                try{
			                	
			                    //TODO check if creation succed
			                    tempFile.renameTo(tempFile);
		
			                    NewClassifiedAdPage.this.info("saved file: " + fileName);
			                }
			                catch (Exception e) {
			                    throw new IllegalStateException("Unable to write file");
			                }
						}
		                
					}
					NewClassifiedAdPage.this.newClassifiedAdTO.setPrice(Double.valueOf(DescriptionForm.this.price.getModelObjectAsString()));
			    	NewClassifiedAdPage.this.newClassifiedAdTO.setTitle(DescriptionForm.this.title.getModelObjectAsString());
			    	NewClassifiedAdPage.this.newClassifiedAdTO.setDescription(DescriptionForm.this.description.getModelObjectAsString());
			    	NewClassifiedAdPage.this.descriptionPanel.setVisible(false);
					NewClassifiedAdPage.this.userDataPanel.setVisible(true);
	        	}
	        });
	        
	        setMaxSize(Bytes.kilobytes(100));
	    }
	    
	}
	
	private class UploadTemporalFilesForm extends Form {
		
		private static final long serialVersionUID = 2713401468137390764L;
		protected  FileUploadField fileUploadField;
		
	    public UploadTemporalFilesForm(String id) {
	        super(id);
	        setMultiPart(true);
	        
	        add(this.fileUploadField = new FileUploadField("fileInput"));
	        setMaxSize(Bytes.kilobytes(100));
	    }
	    /**
         * @see org.apache.wicket.markup.html.form.Form#onSubmit()
         */
        @Override
        protected void onSubmit(){
        	
        	final FileUpload upload = UploadTemporalFilesForm.this.fileUploadField.getFileUpload();
        	
            if (upload != null){
            	File newFile = new File(getTemporalUploadFolder(), upload.getClientFileName());
        		
                // Check new file, delete if it allready existed
                NewClassifiedAdPage.this.checkFileExists(newFile);
                try{
                    // Save to new file
                    newFile.createNewFile();
                    upload.writeTo(newFile);

                    NewClassifiedAdPage.this.info("saved file: " + upload.getClientFileName());
                }
                catch (Exception e) {
                    throw new IllegalStateException("Unable to write file");
                }
                if (NewClassifiedAdPage.this.temporalFiles == null){
                	NewClassifiedAdPage.this.temporalFiles = new  ArrayList<String>();
                }
                
                NewClassifiedAdPage.this.temporalFiles.add(upload.getClientFileName());
            }
        }
	}
	
	

	
	private class UserDataForm extends Form {
		private static final long serialVersionUID = 9053897905303403343L;
		protected final RequiredTextField email;
		protected final TextField phone;
	    

	    public UserDataForm(String id) {
	        super(id);
	        this.phone = new TextField("phone", new Model(NewClassifiedAdPage.this.newClassifiedAdTO.getPhone()), String.class);

	        this.email = new RequiredTextField("email", new Model(NewClassifiedAdPage.this.newClassifiedAdTO.getEmail()));
	        this.email.add(EmailAddressValidator.getInstance());
	        this.email.setRequired(true);
	        
	        add(this.phone);
	        add(this.email);
	        add(new SubmitLink("submitUserDataLink"){

				private static final long serialVersionUID = 8056648125766325902L;

				@Override
				public void onSubmit() {
					NewClassifiedAdPage.this.newClassifiedAdTO.setPhone(UserDataForm.this.phone.getModelObjectAsString());
			    	NewClassifiedAdPage.this.newClassifiedAdTO.setEmail(UserDataForm.this.email.getModelObjectAsString());
			    	NewClassifiedAdPage.this.userDataPanel.setVisible(false);
			    	NewClassifiedAdPage.this.checkCreatedAdPanel.setVisible(true);
				}
	        	
	        });
	    }
	}

	
	private class CheckCreatedAdPanel extends Panel {

		private static final long serialVersionUID = -7322178890866291550L;

		public  CheckCreatedAdPanel(final String id) {
			super(id);
			CheckCreatedAdPanel.this.setVisible(false);	
			
			this.add(new Label("classifiedAdCategory", new LoadableDetachableModel(){
				private static final long serialVersionUID = 6214064755552736602L;

				@Override
				protected Object load() {
					return NewClassifiedAdPage.this.categoryName;
				}
			}));
			this.add(new Label("classifiedAdSubcategory", new LoadableDetachableModel(){
				private static final long serialVersionUID = 970648523535816386L;

				@Override
				protected Object load() {
					return NewClassifiedAdPage.this.subcategoryName;
				}
			}));
			
			this.add(new Label("classifiedAdTitle", new LoadableDetachableModel(){
				private static final long serialVersionUID = 6266642250638714559L;
				@Override
				protected Object load() {
					return NewClassifiedAdPage.this.newClassifiedAdTO.getTitle();
				}
			}));
			this.add(new Label("classifiedAdDescription", new LoadableDetachableModel(){
				private static final long serialVersionUID = 12279343931559149L;

				@Override
				protected Object load() {
					return NewClassifiedAdPage.this.newClassifiedAdTO.getDescription();
				}
			}));
			this.add(new Label("classifiedAdPrice", new LoadableDetachableModel(){
				private static final long serialVersionUID = -4934863231126478229L;

				@Override
				protected Object load() {
					return NewClassifiedAdPage.this.newClassifiedAdTO.getPrice();
				}
			}));
			
			this.add(new Label("userDataEmail", new LoadableDetachableModel(){
				private static final long serialVersionUID = 5750430827354561280L;

				@Override
				protected Object load() {
					return NewClassifiedAdPage.this.newClassifiedAdTO.getEmail();
				}
			}));
			this.add(new Label("userDataPhone", new LoadableDetachableModel(){
				private static final long serialVersionUID = -3049466790761590794L;

				@Override
				protected Object load() {
					return NewClassifiedAdPage.this.newClassifiedAdTO.getPhone();
				}
			}));
			
			Link confirmationLink = new Link("confirmationLink") {
				@Override
				public void onClick() {
					
					LastweekApplication.get().getClassifiedService().createClassifiedAd(NewClassifiedAdPage.this.newClassifiedAdTO);
					
					NewClassifiedAdPage.this.info("se ha creado correctamente");
				}

			};

			confirmationLink.add(new Label("confirmationLabel", "publicar mi anuncio"));
			this.add(confirmationLink);
			
		}
	
	}

	public NewClassifiedAdPage() {
		super();
		this.provincePanel = new ProvincePanel("provincePanel");
		add(this.provincePanel);
		this.categoryPanel = new CategoryPanel("categoryPanel");
		add(this.categoryPanel);
		this.subcategoryPanel = new SubcategoryPanel("subcategoryPanel");
		add(this.subcategoryPanel);
		this.descriptionPanel = new DescriptionPanel("descriptionPanel");
		add(this.descriptionPanel);
		this.userDataPanel = new UserDataPanel("userDataPanel");
		add(this.userDataPanel);
		this.checkCreatedAdPanel = new CheckCreatedAdPanel("checkCreatedAdPanel");
		add(this.checkCreatedAdPanel);
		
		
		
		this.feedbackPanel  = new FeedbackPanel("feedbackPanel");
		this.feedbackPanel.setOutputMarkupId(true);
		add(this.feedbackPanel);
	}




	public NewClassifiedAdPage(PageParameters pageParameters) {
		super(pageParameters);
		// TODO Auto-generated constructor stub
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

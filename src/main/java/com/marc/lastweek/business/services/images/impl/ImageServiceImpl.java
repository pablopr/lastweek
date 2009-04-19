/*
 * ImageServiceImpl.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.services.images.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.springframework.stereotype.Service;

import com.marc.lastweek.business.services.images.ImageService;
import com.marc.lastweek.business.views.images.ImageEntry;
import com.marc.lastweek.web.application.LastweekApplication;
 	
@Service
public class ImageServiceImpl implements ImageService{
   
	// CONSTANTS
    private static final Log logger = LogFactory.getLog(ImageServiceImpl.class);
    
    /**
     * Creates a new instance.
     */
    public ImageServiceImpl(){
        super();
        this.imagesProperties = this.getImagesProperties();
    }

    private static final String IMAGES_FILE_PROPERTIES = "images.properties";
    
    private static final String UPLOAD_FOLDER_KEY = "images.upload.basepath";
    private static final String UPLOAD_TEMPORAL_FOLDER_KEY = "images.upload.temporal.basepath";
   
    private Properties imagesProperties;

	
	
	
	public List<File> getAllTemporalFiles(Folder temporalFolder) {
		temporalFolder.mkdir();
		return Arrays.asList(temporalFolder.listFiles());
	}
    
	public void saveTemporalImage(FileUpload fileUpload, Folder temporalFolder) {
		try{
			File newFile =  createImageFile(temporalFolder, fileUpload.getClientFileName());
			// Save to new file
			fileUpload.writeTo(newFile);

		}
		catch (Exception e) {
			throw new IllegalStateException("Unable to write file");
		}
		
	}
	
	public void saveAllImages(Folder temporalFolder) {
		Folder folder = createSaveFolder(temporalFolder);
		
		List<File> files = Arrays.asList(temporalFolder.listFiles());

		if (files != null) {
			
			for (File file : files){
				// Create a new file
				File newFile = new File(folder, file.getName());
				
				
				// Check new file, delete if it allready existed
				checkFileExists(newFile);
				try{
					
					//TODO check if creation succed
					Files.writeTo(newFile, new FileInputStream(file));
					Files.remove(file);
	
				}
				catch (Exception e) {
					throw new IllegalStateException("Unable to write file");
				}
			}
			temporalFolder.delete();
		}
		
	}
	
	protected void checkFileExists(File newFile){
		if (newFile.exists()){
			// Try to delete the file
			if (!Files.remove(newFile)){
				throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());
			}
		}
	}
    
	private String getFileTypeSuffix(String contentType){
		 String suffix = null;
	       if ("image/gif".equalsIgnoreCase(contentType)) {
	           suffix = ".gif";
	       } else if ("image/jpeg".equalsIgnoreCase(contentType)) {
	           suffix = ".jpeg";
	       } else if ("image/png".equalsIgnoreCase(contentType)) {
	           suffix = ".png";
	       }
	      return suffix;
	}

	
	private File createImageFile(Folder dir, String fileName){
        File file = new File(dir, fileName);
        if (logger.isDebugEnabled()) {
            logger.debug("File " + file.getAbsolutePath() + " created.");
        }
        return file;
    }
	
	
	public Folder createTemporalFolder(){
		return this.createRandomDir(this.imagesProperties.getProperty(UPLOAD_TEMPORAL_FOLDER_KEY));
	}
	
	public Folder createSaveFolder(Folder temporalFolder){
		Folder folder = new Folder(this.imagesProperties.getProperty(UPLOAD_FOLDER_KEY), temporalFolder.getName());
		folder.mkdir();
		return folder;
	}
	
	public Folder findFolderFromName(String folderName){
		return new Folder(this.imagesProperties.getProperty(UPLOAD_FOLDER_KEY), folderName);
	}
	
	public Folder createRandomDir(String basePath){
		UUID uuid = UUID.randomUUID();
		Folder folder = new Folder(basePath, uuid.toString());
		folder.mkdir();
		return folder;
		
	}
	
    
    public Date getLastModifyTime(ImageEntry imageEntry){
        File f = new File(imageEntry.getFileName());
        return new Date(f.lastModified());
    }
 
    public boolean isImageAvailable(ImageEntry imageEntry){
        return (new File(imageEntry.getFileName()).exists());
    }
    
	private Properties getImagesProperties(){
		return getPropertiesFromFile(IMAGES_FILE_PROPERTIES);
	}
	
	private Properties getPropertiesFromFile(String fileName){
	      /* Read properties file */
        ClassLoader loader = LastweekApplication.class.getClassLoader();  
        URL url = loader.getResource(fileName);  
        InputStream in;
        Properties properties = new Properties(); 
		try {
			in = url.openStream();
			properties.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return properties;
	}
   


   
   
}
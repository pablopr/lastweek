/*
 * ImageService.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.services.images;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.Folder;
import org.springframework.transaction.annotation.Transactional;

import com.marc.lastweek.business.views.images.ImageEntry;

public interface ImageService{
	
    
    public boolean isImageAvailable(ImageEntry imageEntry);
    
    public Date getLastModifyTime(ImageEntry imageEntry);
    
    public byte[] getThumbnail(ImageEntry imageEntry)
       throws IOException;

    
    @Transactional
    public void saveTemporalImage(FileUpload fileUpload, Folder temporalDir);
    
    @Transactional
    public void saveAllImages(Folder temporalDir);
    
    public  List<File> getAllTemporalFiles(Folder temporalDir);
    
	public Folder createTemporalFolder();
	
	public Folder createSaveFolder(Folder temporalFolder);
	
	public Folder createRandomDir(String basePath);
	
	public Folder findFolderFromName(String folderName);
    
}
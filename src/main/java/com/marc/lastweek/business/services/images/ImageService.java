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
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.springframework.transaction.annotation.Transactional;

import com.marc.lastweek.business.views.images.ImageEntry;

public interface ImageService{
	
    public byte[] getImage(ImageEntry imageEntry)
       throws IOException;
    
    public boolean isImageAvailable(ImageEntry imageEntry);
    
    public Date getLastModifyTime(ImageEntry imageEntry);
    
    public byte[] getThumbnail(ImageEntry imageEntry)
       throws IOException;

    @Transactional
    public void save(ImageEntry imageEntry, InputStream imageStream)
       throws IOException;
    
    @Transactional
    public void saveTemporalImage(FileUpload fileUpload, String temporalDir);
    
    @Transactional
    public void saveAllImages(String temporalDir);
    
    public  List<File> getAllTemporalFiles(String temporalDir);
    	
    
}
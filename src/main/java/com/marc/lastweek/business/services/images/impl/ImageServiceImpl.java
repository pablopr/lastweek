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
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.io.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import com.marc.lastweek.business.services.images.ImageService;
import com.marc.lastweek.business.views.images.ImageEntry;

@Service
public class ImageServiceImpl implements ImageService{
   
	// CONSTANTS
    private static final Log logger = LogFactory.getLog(ImageServiceImpl.class);
    
    /**
     * Creates a new instance.
     */
    public ImageServiceImpl(){
        super();
    }

    /* Spring Injected */
    private File imageDir;
    private int thumbnailSize;
    private static final String UPLOAD_FOLDER = "/var/tmp/lastweek/wicket-uploads";
    
	private static final String TEMPORAL_UPLOAD_FOLDER = "/var/tmp/lastweek/wicket-uploads-temp";
	
	
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
		return this.createRandomDir(TEMPORAL_UPLOAD_FOLDER);
	}
	
	public Folder createSaveFolder(Folder temporalFolder){
		Folder folder = new Folder(UPLOAD_FOLDER, temporalFolder.getName());
		folder.mkdir();
		return folder;
	}
	
	public Folder findFolderFromName(String folderName){
		return new Folder(UPLOAD_FOLDER, folderName);
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
    
    public byte[] getThumbnail(ImageEntry imageEntry)
        throws IOException {
    	
        if (isImageAvailable(imageEntry)) {
            // Open the file, then read it in.
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            InputStream inStream =
                new FileInputStream(new File(imageEntry.getThumbName()));
            copy(inStream, outStream);
            inStream.close();
            outStream.close();
            return outStream.toByteArray();
        }
		byte[] imageData = createNotAvailableImage(imageEntry.getContentType());
		return scaleImage(imageData, getThumbnailSize());
    }

 
    /**
     * Copies data from src into dst.
     */
    private void copy(InputStream source, OutputStream destination)
        throws IOException{
        try {
            // Transfer bytes from source to destination
            byte[] buf = new byte[1024];
            int len;
            while ((len = source.read(buf)) > 0) {
                destination.write(buf, 0, len);
            }
            source.close();
            destination.close();
            if (logger.isDebugEnabled()) {
                logger.debug("Copying image...");
            }
        } catch (IOException ioe) {
            logger.error(ioe);
            throw ioe;
        }
    }
    
    private byte[] createNotAvailableImage(String contentType)
        throws IOException{
        // Load the "Image Not Available"
        // image from jar, then write it out.
        StringBuffer name = new StringBuffer("com/marc/lastweek/resources/ImageNotAvailable.");
        if ("image/jpeg".equalsIgnoreCase(contentType)) {
            name.append("jpg");
        } else if ("image/png".equalsIgnoreCase(contentType)) {
            name.append("png");
        } else {
            name.append("gif");
        }
        URL url = getClass().getClassLoader().getResource(
                name.toString()
            );
        InputStream in = url.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(in, out);
        in.close();
        out.close();
        return out.toByteArray();
    }
 
   
   private byte[] scaleImage(byte[] imageData, int maxSize) {
//       if (logger.isDebugEnabled()) {
//           logger.debug("Scaling image...");
//       }
//       // Get the image from a file.
//       Image inImage = new ImageIcon(imageData).getImage();
//
//       // Determine the scale.
//       double scale = (double) maxSize / (double) inImage.getHeight(null);
//       if (inImage.getWidth(null) > inImage.getHeight(null)) {
//           scale = (double) maxSize / (double) inImage.getWidth(null);
//       }
//
//       // Determine size of new image.
//       // One of the dimensions should equal maxSize.
//       int scaledW = (int) (scale * inImage.getWidth(null));
//       int scaledH = (int) (scale * inImage.getHeight(null));
//
//       // Create an image buffer in which to paint on.
//       BufferedImage outImage = new BufferedImage(
//               scaledW, scaledH, BufferedImage.TYPE_INT_RGB
//           );
//
//       // Set the scale.
//       AffineTransform tx = new AffineTransform();
//
//       // If the image is smaller than the desired image size,
//       // don't bother scaling.
//       if (scale < 1.0d) {
//           tx.scale(scale, scale);
//       }
//
//       // Paint image.
//       Graphics2D g2d = outImage.createGraphics();
//       g2d.setRenderingHint(
//               RenderingHints.KEY_ANTIALIASING,
//               RenderingHints.VALUE_ANTIALIAS_ON
//           );
//       g2d.drawImage(inImage, tx, null);
//       g2d.dispose();
//
//       // JPEG-encode the image
//       // and write to file.
//       ByteArrayOutputStream os = new ByteArrayOutputStream();
//       JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
//       encoder.encode(outImage);
//       os.close();
//       if (logger.isDebugEnabled()) {
//          logger.debug("Scaling done.");
//       }
//       return os.toByteArray();
	   return null;
   }

	public File getImageDir() {
		return this.imageDir;
	}
	
	public void setImageDir(File imageDir) {
		this.imageDir = imageDir;
	}
	
	public int getThumbnailSize() {
		return this.thumbnailSize;
	}
	
	public void setThumbnailSize(int thumbnailSize) {
		this.thumbnailSize = thumbnailSize;
	}

   
   
}
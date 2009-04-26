/*
 * @(#)JQueryImagegallery.java
 *
 * Copyright (c) 2.009, denodo technologies, S.L. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * denodo technologies, S.L. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with denodo technologies S.L.
 */
package com.marc.lastweek.web.components.imagegallery;

import java.io.File;
import java.util.List;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebResource;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;

public class JQueryImagegallery extends Panel{
    private static final long serialVersionUID = -708309110111519195L;

    private static final String GALLERY_JS = "js/jquery.galleria.js";
    private static final String GALLERY_INIT_JS = "js/gallery.init.js";
    private static final String GALLERY_CSS = "css/galleria.css";
    
    public JQueryImagegallery (String id, List<File> imagesList) {
        super(id);
        
        this.add(HeaderContributor.forCss(new ResourceReference(JQueryImagegallery.class,
                GALLERY_CSS)));

        this.add(HeaderContributor.forJavaScript(new ResourceReference(JQueryImagegallery.class,
                GALLERY_JS)));
        this.add(HeaderContributor.forJavaScript(new ResourceReference(JQueryImagegallery.class,
              GALLERY_INIT_JS)));
        
        this.add(new ListView("imageGalleryList", imagesList){
            private static final long serialVersionUID = 2408115049886403982L;

            @Override
            protected void populateItem(ListItem item) {
                final File file = (File)item.getModelObject();
                
                item.add(new Image("imageItem", new ImageFileResource(file)));
                
                if (item.getIndex()==0) {
                    item.add(new SimpleAttributeModifier("class", "active"));
                }
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


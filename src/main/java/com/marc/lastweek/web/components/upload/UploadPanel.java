/*
 * UploadPanel.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.components.upload;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.InlineFrame;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class UploadPanel extends Panel { 
 
	private static final long serialVersionUID = -8249394384855408798L;
	private InlineFrame uploadIFrame = null; 
    public UploadPanel(String id) { 
        super(id); 
        addOnUploadedCallback(); 
	    
	    this.setOutputMarkupId(true);
		this.setOutputMarkupPlaceholderTag(true);
    } 
    /**
     * Called when the upload load is uploaded and ready to be used
     * Return the url of the new uploaded resource
     * @param upload {@link FileUpload}
     */ 
    public abstract String onFileUploaded(FileUpload upload); 
    /**
     * Called once the upload is finished and the traitment of the
     * {@link FileUpload} has been done in {@link UploadPanel#onFileUploaded}
     * @param target an {@link AjaxRequestTarget}
     * @param fileName name of the file on the client side
     * @param newFileUrl Url of the uploaded file
     */ 
    public abstract void onUploadFinished(AjaxRequestTarget target, String filename, String newFileUrl); 
 
    @Override 
    protected void onBeforeRender() { 
        super.onBeforeRender(); 
        if (this.uploadIFrame == null) { 
            // the iframe should be attached to a page to be able to get its pagemap, 
            // that's why i'm adding it in onBeforRender 
            addUploadIFrame(); 
        } 
    } 
 
    /**
     * Create the iframe containing the upload widget
     *
     */ 
    private void addUploadIFrame() { 
        IPageLink iFrameLink = new IPageLink() { 

			private static final long serialVersionUID = -982896757427200332L;

			public Page getPage() { 
                return new UploadIFrame() { 
                    @Override 
                    protected String getOnUploadedCallback() { 
                        return "onUpload_" + UploadPanel.this.getMarkupId(); 
                    } 
 
                    @Override 
                    protected String manageInputSream(FileUpload upload) { 
                        return UploadPanel.this.onFileUploaded(upload); 
                    } 
                }; 
            } 

        	public Class<UploadIFrame> getPageIdentity() { 
                return UploadIFrame.class; 
            } 
        }; 
        UploadPanel.this.uploadIFrame = new InlineFrame("upload", getPage().getPageMap(), iFrameLink); 
        add(UploadPanel.this.uploadIFrame); 
    } 
    /**
     * Hackie method allowing to add a javascript in the page defining the
     * callback called by the innerIframe
     *
     */ 
    @SuppressWarnings("synthetic-access")
	private void addOnUploadedCallback() { 
        final OnUploadedBehavior onUploadBehavior = new OnUploadedBehavior(); 
        add(onUploadBehavior); 
        add(new WebComponent("onUploaded") { 
			private static final long serialVersionUID = 6222268354159011845L;

			@Override 
            protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) { 
                // calling it through setTimeout we ensure that the callback is called 
                // in the proper execution context, that is the parent frame 
                replaceComponentTagBody(markupStream, openTag, 
                        "function onUpload_" + UploadPanel.this.getMarkupId() + 
                        "(clientFileName, newFileUrl) {window.setTimeout(function() { " + 
                        onUploadBehavior.getCallback() + " }, 0 )}"); 
            } 
        }); 
    } 
 
    private class OnUploadedBehavior extends AbstractDefaultAjaxBehavior { 
		private static final long serialVersionUID = 4368699167246211519L;
		public String getCallback() { 
            return generateCallbackScript( 
                    "wicketAjaxGet('" + getCallbackUrl(false) + 
                    "&amp;amp;newFileUrl=' + encodeURIComponent(newFileUrl)" + 
                    " + '&amp;amp;clientFileName=' + encodeURIComponent(clientFileName)").toString(); 
        } 
        @Override 
        protected void respond(AjaxRequestTarget target) { 
            UploadPanel.this.onUploadFinished(target, getRequest().getParameter("clientFileName"), getRequest().getParameter("newFileUrl")); 
        } 
    }
    
}  
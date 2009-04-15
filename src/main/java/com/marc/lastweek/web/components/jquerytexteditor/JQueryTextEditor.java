/*
 * @(#)JQueryTextEditor.java
 *
 * Copyright (c) 2.009, denodo technologies, S.L. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * denodo technologies, S.L. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with denodo technologies S.L.
 */
package loc.denodo.mashupmanager.web.components.jquerytexteditor;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.collections.MiniMap;
import org.apache.wicket.util.template.TextTemplateHeaderContributor;

public class JQueryTextEditor extends TextArea {
    private static final long serialVersionUID = 9033131475535398504L;

    private static final String EDITOR_JS = "js/jquery.rte.js";
    private static final String EDITOR_CSS = "css/style.rte.css";
    private static final String JQUERY_URL = "js/jquery-1.3.2.min.js";
    
    private boolean addJQuery = false;
    
    public JQueryTextEditor(String id) {
       this(id, new Model());
    }

    
    public JQueryTextEditor(String id, IModel model) {
        super(id, model);
        
        this.add(new AttributeAppender("class", true, new Model("rte-zone"), " "));
        this.setEscapeModelStrings(false);
    }
    
    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        
        if(addJQuery) {
            this.add(HeaderContributor.forJavaScript(new ResourceReference(JQueryTextEditor.class,
                    JQUERY_URL)));
        }
        
        this.add(TextTemplateHeaderContributor.forJavaScript(JQueryTextEditor.class,
                EDITOR_JS,new Model(getToolbarImageMap())));
        
        this.add(HeaderContributor.forCss(new ResourceReference(JQueryTextEditor.class,
                EDITOR_CSS)));
    }
    
    public void addJQueryLibrary(boolean value) {
        this.addJQuery = value;
    }

    private MiniMap getToolbarImageMap() {
         MiniMap images = new MiniMap(4);
         images.put("BOLD_IMG", getRequestCycle().urlFor(
                 new ResourceReference(JQueryTextEditor.class, "images/bold.gif")));
         images.put("ITALIC_IMG", getRequestCycle().urlFor(
                 new ResourceReference(JQueryTextEditor.class, "images/italic.gif")));
         images.put("LINK_IMG", getRequestCycle().urlFor(
                 new ResourceReference(JQueryTextEditor.class, "images/link.png")));
         images.put("UNORDERED_IMG", getRequestCycle().urlFor(
                 new ResourceReference(JQueryTextEditor.class, "images/unordered.gif")));
        return images;
   }

}


/*
 * MailServerImpl.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.business.services.mail.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.MathTool;
import org.apache.velocity.tools.generic.ResourceTool;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.marc.lastweek.business.services.mail.MailService;

public class MailServiceImpl implements MailService {
	
	private final static String TEMPLATE_FAVORITES = "favorites";
	
	private final static String FIELD_SUBJECT = ".subject";
	
    private static final String CHARSET = "ISO-8859-1";
    private static final Locale SPANISH_LOCALE = new Locale("es");  
	private final static String MAIL_MESSAGES_FILE = "mail-templates/mail-messages.properties";

	protected JavaMailSender javaMailSender = null;

	protected VelocityEngine velocityEngine = null;

	protected Map<String, String> velocityTemplates = new HashMap<String, String>();    

	protected String from;        
	

	public void sendFavoritesMail(String address, List<Long> favoritesList) {
		// TODO get classified ad inforamtion
		Map templateData = new HashMap();
		
		this.sendMail(SPANISH_LOCALE, TEMPLATE_FAVORITES, templateData, address);
	}

	@Required
	public void setFrom(String from) {
		this.from = from;
	}    

	@Required
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Required
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	@Required
	public void setVelocityTemplates(Map<String, String> velocityTemplates) {
		this.velocityTemplates = velocityTemplates;
	}

	private void sendMail(Locale locale, String templateName, Map<String, Object> templateData, String mailTo) {
        this.javaMailSender.send(getMimeMessagePreparator(locale, templateName, templateData, mailTo));
    }
	
    private MimeMessagePreparator getMimeMessagePreparator(final Locale locale, final String templateName, 
    		final Map<String, Object> templateData, final String mailTo) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);

                // Set message attributes
                message.setTo(mailTo);
                message.setFrom(MailServiceImpl.this.from);
                message.setSubject(getMailMessageEntry(locale, templateName + FIELD_SUBJECT));
                
                // Add parameters
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("locale", locale);
                model.put("dateTool", new DateTool());
                model.put("mathTool", new MathTool());
                model.put("resourceTool", new ResourceTool());

                // Insert data in the template
                for (String name : templateData.keySet()) {
                    model.put(name, templateData.get(name));
                }

                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        MailServiceImpl.this.velocityEngine, MailServiceImpl.this.velocityTemplates.get(templateName), 
                        CHARSET, model);
                message.setText(text, true);

                // Insert stylesheet
                //ClassPathResource stylesheet = new ClassPathResource("templates/email.css", MailServiceImpl.class);
//                ClassPathResource stylesheet = new ClassPathResource(MAIL_STYLESHEET, );
//                message.addInline("email.css", stylesheet, "text/css");								
            }
        };
        return preparator;
    }
    
    public static String getMailMessageEntry(Locale locale, String key, Object...arguments) {				
		return MessageFormat.format(ResourceBundle.getBundle(MAIL_MESSAGES_FILE, locale).
				getString(key), arguments);
	}	


}

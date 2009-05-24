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

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import loc.marc.commons.business.services.general.GeneralService;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.ResourceTool;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.services.mail.MailService;
import com.marc.lastweek.web.application.LastweekApplication;


public class MailServiceImpl implements MailService {
	
	private final static String TEMPLATE_FAVORITES = "favorites";
	private final static String TEMPLATE_ACTIVATION = "activation";
	private final static String TEMPLATE_CONTACT = "contact";
	private final static String TEMPLATE_REFRESH = "refresh";
	private final static String TEMPLATE_EXPIRED = "expired";
	
	private final static String FIELD_SUBJECT = ".subject";
	
    private static final String CHARSET = "ISO-8859-1";
    private static final Locale SPANISH_LOCALE = new Locale("es");  
	private final static String MAIL_MESSAGES_FILE = "mail-templates/mail-messages";

	protected JavaMailSender javaMailSender = null;

	protected VelocityEngine velocityEngine = null;

	protected Map<String, String> velocityTemplates = new HashMap<String, String>();    

	protected String from;        
	

	public void sendFavoritesMail(String address, List<Long> favoritesList) {
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("favorites", favoritesList);
		List<ClassifiedAd> adsList = LastweekApplication.get().getGeneralService().queryForList(ClassifiedAd.class,
				"getClassifiedAdsInList", parameters);
		
		Map<String,Object> templateData = new HashMap<String,Object>();
		templateData.put("baseurl", "http://localhost:8080/lastweek/details/clid");
		templateData.put("adsList", adsList);
		
		this.sendMail(SPANISH_LOCALE, TEMPLATE_FAVORITES, templateData, address);
	}
	
	public void sendActivationMail(ClassifiedAd classifiedAd) {
		
		Map<String,Object> templateData = new HashMap<String,Object>();
		//TODO localhost:8080? change!
		templateData.put("baseurl", "http://localhost:8080/lastweek/details/clid");
		templateData.put("ad", classifiedAd);
		this.sendMail(SPANISH_LOCALE, TEMPLATE_ACTIVATION, templateData, classifiedAd.getUserData().getEmail());
	}
    
	public void sendContactMail(String senderName, String senderEmail, String text,
			Long classifiedAdId) {
		Map<String,Object> templateData = new HashMap<String,Object>();
		ClassifiedAd classifiedAd = LastweekApplication.get().getGeneralService().get(ClassifiedAd.class, classifiedAdId);
		//TODO localhost:8080? change!
		templateData.put("baseurl", "http://localhost:8080/lastweek/details/clid");
		templateData.put("ad", classifiedAd);
		templateData.put("senderName", senderName);
		templateData.put("senderEmail", senderEmail);
		templateData.put("text", text);
		this.sendMail(SPANISH_LOCALE, TEMPLATE_CONTACT, templateData, classifiedAd.getUserData().getEmail());
	}
	
	@Transactional
	public void sendRefreshMail(Long classifiedAdId, GeneralService generalService) {
		Map<String,Object> templateData = new HashMap<String,Object>();
		ClassifiedAd classifiedAd = generalService.find(ClassifiedAd.class, classifiedAdId);
		//TODO localhost:8080? change!
		templateData.put("baseurl", "http://localhost:8080/lastweek/details/clid");
		templateData.put("ad", classifiedAd);
		this.sendMail(SPANISH_LOCALE, TEMPLATE_REFRESH, templateData, classifiedAd.getUserData().getEmail());		
	}
	
	@Transactional
	public void sendExpiredMail(Long classifiedAdId, GeneralService generalService) {
		Map<String,Object> templateData = new HashMap<String,Object>();
		ClassifiedAd classifiedAd = generalService.find(ClassifiedAd.class, classifiedAdId);
		//TODO localhost:8080? change!
		templateData.put("baseurl", "http://localhost:8080/lastweek/details/clid");
		templateData.put("ad", classifiedAd);
		this.sendMail(SPANISH_LOCALE, TEMPLATE_EXPIRED, templateData, classifiedAd.getUserData().getEmail());		
	}

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
            public void prepare(MimeMessage mimeMessage) throws MessagingException {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);

                // Set message attributes
                message.setTo(mailTo);
                message.setFrom(MailServiceImpl.this.from);
                message.setSubject(getMailMessageEntry(locale, templateName + FIELD_SUBJECT));
                
                // Add parameters
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("locale", locale);
                model.put("dateTool", new DateTool());
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

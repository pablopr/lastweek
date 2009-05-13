/*
 * RecommendedClassifiedAdsListPanel.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.classifiedadslisting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.time.DateUtils;
import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.joda.time.DateTime;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.web.application.LastweekApplication;
import com.marc.lastweek.web.naming.PageParametersNaming;
import com.marc.lastweek.web.pages.classifiedad.ClassifiedAdDetailPage;
import com.marc.lastweek.web.session.LastweekSession;
import com.marc.lastweek.web.util.ViewUtils;


/* TODO Add a different style
 * TODO Internationalization of the html page.
 */
public class RecommendedClassifiedAdsListPanel extends Panel {

    private static int RECOMMENDED_ADS_NUMBER = 2;
    private static final long serialVersionUID = 4553714306517667806L;
    
    private Calendar date;
    private ListView recommendedClassifiedAdsList;
    private List<ClassifiedAd> recommendedClassifiedAds;
    
    public RecommendedClassifiedAdsListPanel(String id) {
        super(id);
        this.initializeFilterDate();
        this.initializeRecommendedClassifiedAdsList();        
    }

    private void initializeFilterDate() {
        DateTime now = new DateTime(Calendar.getInstance());
        this.date = DateUtils.truncate(now.minusWeeks(1).toCalendar(getLocale()), Calendar.DAY_OF_MONTH);
    }
    
    public void initializeRecommendedClassifiedAdsList() {
        this.recommendedClassifiedAds = new ArrayList<ClassifiedAd>();
        List<ClassifiedAd> allRecommendedClassifiedAds = this.getAllRecommendedClassifiedAds();
        Random rand = new Random();
        if ( allRecommendedClassifiedAds.size() > 0 ) {
            for (int i = 0; i < RECOMMENDED_ADS_NUMBER; i++) {
                int recommendedClassifiedAdIndex = rand.nextInt( allRecommendedClassifiedAds.size() - 1 );
                this.recommendedClassifiedAds.add(allRecommendedClassifiedAds.get(recommendedClassifiedAdIndex));
            }
        }
        this.recommendedClassifiedAdsList = new ListView("recommendedClassifiedAdsList",this.recommendedClassifiedAds) {

            private static final long serialVersionUID = 1L;
        
            @Override
            protected void populateItem(ListItem item) {
                ClassifiedAd classifiedAd = (ClassifiedAd)item.getModelObject();

                PageParameters linkParameters = new PageParameters();
                linkParameters.put(PageParametersNaming.PARAM_NAME_CLASSIFIED_AD_ID, 
                        classifiedAd.getId());
                Link classifiedAdLink = (new BookmarkablePageLink("classifiedAdLink", 
                        ClassifiedAdDetailPage.class, linkParameters));
                classifiedAdLink.add(new Label("classifiedAdTitle",ViewUtils.labelizer(classifiedAd.getTitle())));
                item.add(classifiedAdLink);
                
                item.add(new Label("classifiedAdPublicationDate",ViewUtils.labelizer(classifiedAd.getPublicationDate())));
                
                Label description = new Label("classifiedAdDescription",ViewUtils.getDigest(classifiedAd.getDescription()));
                description.setEscapeModelStrings(false);
                item.add(description);
                item.add(new Label("classifiedAdPrice",ViewUtils.labelizer(classifiedAd.getPrice())));

                
                if (item.getIndex()%2 == 1) {
                    item.add(new SimpleAttributeModifier("class", "classified-ad-summary-alt"));
                }
            }
        };
        this.add(this.recommendedClassifiedAdsList);
    }
       
    
    private List<ClassifiedAd> getAllRecommendedClassifiedAds() {
        LastweekSession lastweekSession = (LastweekSession) getSession();
        return LastweekApplication.get().getClassifiedAdsService().
            findClassifiedAdsByFilterParameters(lastweekSession.getRamdomFilterParametersFromHistory(), 0, 15, this.date);
    }
    
    @Override
    public boolean isVisible() {
        return !this.recommendedClassifiedAds.isEmpty();
    }
   

}

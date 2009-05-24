package com.marc.lastweek.business.jobs;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import loc.marc.commons.business.services.general.GeneralService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.services.classifiedads.ClassifiedAdsService;
import com.marc.lastweek.business.services.mail.MailService;

public class ExpiringAdsAdvisorJob {

private static final long timeToAdviceBeforeExpiring = 86400000;
	
	@Autowired
	private GeneralService generalService;
	@Autowired
	private ClassifiedAdsService classifiedAdsService;
	@Autowired
	private MailService mailService;
	
	protected static final Logger log = Logger.getLogger(ExpiringAdsAdvisorJob.class);
	private static final String GET_EXPIRING_TODAY_OR_TOMORROW_ADS_QUERY = "getExpiringTodayOrTomorrowAds";
	private static final String PARAMETER_NAME_NOW = "now";
	
	protected void checkExpiredAds() {
		long timeToExpire;

		Map<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put(PARAMETER_NAME_NOW, Calendar.getInstance());
		
		for (ClassifiedAd classifiedAd : this.generalService.queryForList(ClassifiedAd.class, 
				GET_EXPIRING_TODAY_OR_TOMORROW_ADS_QUERY, queryParameters)) {
		
			timeToExpire = classifiedAd.getTimeToExpireInMillis();
			System.out.println();
			System.out.println("------ Time to expire : " + timeToExpire);
			System.out.println();
			if (classifiedAd.getState().intValue() == ClassifiedAd.STATE_EXPIRING_TOMORROW) {
				log.info("Sending expired for ad" + classifiedAd.getTitle() + "!!!!!");
				this.classifiedAdsService.expireClassifiedAd(classifiedAd.getId());
				this.mailService.sendExpiredMail(classifiedAd.getId(), this.generalService);
			}
			else {
				log.info("Sending refresh for ad" + classifiedAd.getTitle() + "!!!!!");
				this.mailService.sendRefreshMail(classifiedAd.getId(), this.generalService);
			}
		}

	}
}
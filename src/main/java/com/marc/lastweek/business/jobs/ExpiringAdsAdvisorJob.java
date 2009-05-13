package com.marc.lastweek.business.jobs;

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
	
	protected void checkExpiredAds() {
		long timeToExpire;

		for (ClassifiedAd classifiedAd : this.generalService.findAll(ClassifiedAd.class)) {
		
			timeToExpire = this.classifiedAdsService.getTimeToExpire(classifiedAd.getId());
			System.out.println("Time to expire : " + timeToExpire);
			if (timeToExpire < 0) {
				log.info("Sending expired for ad" + classifiedAd.getTitle() + "!!!!!");
				this.classifiedAdsService.expireClassifiedAd(classifiedAd.getId());
				this.mailService.sendExpiredMail(classifiedAd.getId(), this.generalService);
			}
			else if (timeToExpire - timeToAdviceBeforeExpiring <= 0) {
				log.info("Sending refresh for ad" + classifiedAd.getTitle() + "!!!!!");
				this.mailService.sendRefreshMail(classifiedAd.getId(), this.generalService);
			}
		}

	}
}
package com.marc.lastweek.business.jobs;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import loc.marc.commons.business.services.general.GeneralService;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.marc.lastweek.business.entities.classifiedad.ClassifiedAd;
import com.marc.lastweek.business.services.classifiedads.ClassifiedAdsService;
import com.marc.lastweek.business.services.mail.MailService;

public class ExpiringAdsNotifierJob extends ApplicationContextAwareQuartzJobBean {

	private static final String GET_EXPIRING_TODAY_OR_TOMORROW_ADS_QUERY = "getExpiringTodayOrTomorrowAds";
	private static final String PARAMETER_NAME_NOW = "now";

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		ApplicationContext applicationContext = this.getApplicationContext(context);
		ClassifiedAdsService classifiedAdsService = (ClassifiedAdsService) applicationContext.getBean("classifiedAdsServiceImpl");
		MailService mailService = (MailService) applicationContext.getBean("mailServiceTarget");
		GeneralService generalService = (GeneralService) applicationContext.getBean("generalServiceImpl");

		Calendar now = Calendar.getInstance();
		// TODO : remove this hack (query does not get all ads when passing Calendar.getInstance() directly as query parameter)
		now.clear();
		Calendar now2 = Calendar.getInstance();
		now.set(Calendar.MONTH, now2.get(Calendar.MONTH));
		now.set(Calendar.DAY_OF_MONTH, now2.get(Calendar.DAY_OF_MONTH));
		now.set(Calendar.YEAR, now2.get(Calendar.YEAR));
		
		Map<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put(PARAMETER_NAME_NOW, now);
		
		for (ClassifiedAd classifiedAd : generalService.queryForList(ClassifiedAd.class, 
				GET_EXPIRING_TODAY_OR_TOMORROW_ADS_QUERY, queryParameters)) {
		
			if (classifiedAd.getState().intValue() == ClassifiedAd.STATE_EXPIRING_TOMORROW) {
				log.info("Sending expired to ad " + classifiedAd.getId() + ".");
				classifiedAdsService.expireClassifiedAd(classifiedAd.getId());
				mailService.sendExpiredMail(classifiedAd.getId(), generalService);
			}
			else {
				log.info("Sending refresh to ad " + classifiedAd.getId() + ".");
				mailService.sendRefreshMail(classifiedAd.getId(), generalService);
				classifiedAdsService.markClassifiedAdAsExpiringTomorrow(classifiedAd.getId());
			}
		}
		
	}
}
package com.marc.lastweek.business.jobs;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class OpenSessionInJobFactoryBean extends
		MethodInvokingJobDetailFactoryBean {

	@Autowired
	private SessionFactory sessionFactory;

	private final static Log logger = LogFactory
			.getLog(OpenSessionInJobFactoryBean.class);

	@Override
	public Object invoke() {

		Object result = null;
		Session session = null;
		try {

			logger
					.debug("Opening single Hibernate session in OpenSessionInJobFactoryBean");
			session = SessionFactoryUtils.getSession(this.sessionFactory, true);
			TransactionSynchronizationManager.bindResource(this.sessionFactory,
					new SessionHolder(session));
			result = super.invoke();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			TransactionSynchronizationManager.unbindResource(this.sessionFactory);
			logger
					.debug("Closing single Hibernate session in OpenSessionInJobFactoryBean");

			SessionFactoryUtils.closeSession(session);
		}

		return result;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
/*
 * NewClassifiedAd.java
 * Copyright (c) 2009, Monte Alto Research Center, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Monte Alto Research Center ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Monte Alto Research Center
 */
package com.marc.lastweek.web.pages.kk;

import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.extensions.wizard.WizardModel;
import org.apache.wicket.extensions.wizard.WizardStep;

import com.marc.lastweek.web.pages.main.MainPage;

public class NewClassifiedAdPage extends Wizard {


	private static final long serialVersionUID = -7976014359463033532L;




	private final class CategoryStep extends WizardStep {

		private static final long serialVersionUID = 1L;

		public CategoryStep(){
			System.out.println();
		}
	}

	
	public NewClassifiedAdPage(String id){
		super(id);
		WizardModel model = new WizardModel();
		model.add(new CategoryStep());
		init(model);
	}
	
	@Override
	public void onCancel(){
		setResponsePage(MainPage.class);
	}
	
	
	@Override
	public void onFinish(){
		setResponsePage(MainPage.class);
	}

}

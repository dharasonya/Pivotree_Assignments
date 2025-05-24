package com.pivotree.auto.training;

import org.testng.Assert;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class TC_LogSoftAssertFailures extends BasePageSetup {

	
	@Test(priority=1)
	public void validate_UIElements() throws Exception
	{
			
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl();
		corestep.verify_PersonalDetailsLabelNames();
		
		
	}
}

package com.pivotree.auto.training;

import org.testng.Assert;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class TC_ProductSelectionAutomation extends BasePageSetup {

	
	@Test(priority=1,enabled=true)
	public void validate_MatchedProductSelection() throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl();
		corestep.selectMatchedProduct("smart");
	}
}



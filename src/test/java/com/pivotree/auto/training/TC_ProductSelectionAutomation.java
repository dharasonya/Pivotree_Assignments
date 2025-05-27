package com.pivotree.auto.training;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class TC_ProductSelectionAutomation extends BasePageSetup {

	
	@Test(priority=1,enabled=true)
	@Parameters("Url")
	public void validate_MatchedProductSelection(String url) throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl(url);
		corestep.selectMatchedProduct("smart");
	}
}



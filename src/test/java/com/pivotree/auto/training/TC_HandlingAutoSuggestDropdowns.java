package com.pivotree.auto.training;

import org.testng.Assert;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class TC_HandlingAutoSuggestDropdowns extends BasePageSetup {

	@Test(priority=1,enabled=false)
	public void Validate_SuggestCount() throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl();
		corestep.handleAutoSuggestDropdowns("dress",5);
	}
	
	@Test(priority=2,enabled=true)
	public void selectLongestAutoSuggestion() throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl();
		corestep.selectLongestAutoSuggestion("pivotree");
	}
}



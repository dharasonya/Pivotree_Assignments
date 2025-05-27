package com.pivotree.auto.training;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class TC_HandlingAutoSuggestDropdowns extends BasePageSetup {

	@Test(priority=1,enabled=true)
	@Parameters("Url")
	public void Validate_SuggestCount(String url) throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl(url);
		corestep.handleAutoSuggestDropdowns("dress",5);
	}
	
	@Test(priority=2,enabled=true)
	@Parameters("Url")
	public void selectLongestAutoSuggestion(String url) throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl(url);
		corestep.selectLongestAutoSuggestion("pivotree");
	}
}



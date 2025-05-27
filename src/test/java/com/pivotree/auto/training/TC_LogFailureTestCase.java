package com.pivotree.auto.training;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

@Test(enabled=false) /// Setting all the Test Cases of this class to skip for Test Execution
public class TC_LogFailureTestCase extends BasePageSetup {

	@Parameters("Url")
	@Test(priority=1,enabled=false)
	public void Validate_Enter_OnlyStartDate(String url) throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl(url);
		corestep.validateFailDateErrors("10-05-2016","");
	}
	
	@Test(priority=2,enabled=false)
	@Parameters("Url")
	public void Validate_Enter_OnlyEndDate(String url) throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl(url);
		corestep.validateFailDateErrors("","10-05-2016");
	}
	
	@Test(priority=3,enabled=false)
	@Parameters("Url")
	public void Validate_No_Value_Enter_StartEndDate(String url) throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl(url);
		corestep.validateFailDateErrors("","");
	}
	
	@Test(priority=3,enabled=false)
	@Parameters("Url")
	public void Validate_StartDate_GreaterEndDate(String url) throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl(url);
		corestep.validateFailDateErrors("10-05-2023","10-05-2019");
	}
	
	
}



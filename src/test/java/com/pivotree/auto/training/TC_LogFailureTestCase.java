package com.pivotree.auto.training;

import org.testng.Assert;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class TC_LogFailureTestCase extends BasePageSetup {

	@Test(priority=1)
	public void Validate_Enter_OnlyStartDate() throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl();
		corestep.validateFailDateErrors("10-05-2016","");
	}
	
	@Test(priority=2)
	public void Validate_Enter_OnlyEndDate() throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl();
		corestep.validateFailDateErrors("","10-05-2016");
	}
	
	@Test(priority=3)
	public void Validate_No_Value_Enter_StartEndDate() throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl();
		corestep.validateFailDateErrors("","");
	}
	
	@Test(priority=3)
	public void Validate_StartDate_GreaterEndDate() throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl();
		corestep.validateFailDateErrors("10-05-2023","10-05-2019");
	}
	
	
}



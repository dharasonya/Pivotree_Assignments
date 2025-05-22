package com.pivotree.auto.training;

import org.testng.Assert;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class Case_ValidatePageTitle extends BasePageSetup {

	@Test(priority=1,enabled=false)
	public void ValidatePageTitle()
	{
		getUrl();
		String expectedTitle=getDriver().getTitle();
		Assert.assertEquals(expectedTitle, "Automation Testing Practice","Verification of Title");
	}
	

	@Test(priority=2)
	public void ValidateMultiplePageLocators() throws Exception
	{
			
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl();
		corestep.addPersonalDetails("Sana", "sana@gmail.com", "9167111278", "Riverview Apt, Thane East", "male", "Thursday",
				"05/22/2025", "19/05/2025", "Test");
		
		
	}
}

package com.pivotree.auto.training;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class TC_FillPersonalDetails extends BasePageSetup {

	@Test(priority=1,enabled=true)
	@Parameters("Url")
	public void ValidatePageTitle(String url)
	{
		getUrl(url);
		String expectedTitle=getDriver().getTitle();
		Assert.assertEquals(expectedTitle, "Automation Testing Practice","Verification of Title");
	}
	

	@Test(priority=2)
	@Parameters("Url")
	public void ValidateMultiplePageLocators(String url) throws Exception
	{
			
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl(url);
		corestep.addPersonalDetails("Sana", "sana@gmail.com", "9167111278", "Riverview Apt, Thane East", "male", "Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday",
				"05/22/2025", "22/06/2023", "01/05/2024","20/05/2024");
		
		
	}
}

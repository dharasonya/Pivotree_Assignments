package com.pivotree.auto.training;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class TC_IdentifyMax_MinBookDetails extends BasePageSetup {

	Method_BlogSpot corestep;

	@BeforeMethod
	public void setup() throws InterruptedException {
		try {
			corestep = Get_BlogSpot_Obj();  // Attempt to initialize the object

			if (corestep == null) {
				throw new NullPointerException("corestep is null! Failed to initialize Get_BlogSpot_Obj().");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Setup failed due to exception: " + e.getMessage());
		}
	}	
	
	@Test(priority=1,enabled=true)
	public void Validate_Highest_PriceWiseBookDetails() throws Exception
	{
		getUrl();
		corestep.getMaxBookDetails();	
	}

	@Test(priority=2,enabled=true)
	public void Validate_Lowest_PriceWiseBookDetails() throws Exception
	{
		getUrl();
		corestep.getMinBookDetails();
	}

	@Test(priority=3,enabled=true)
	public void Validate_Average_PriceWiseBookDetails() throws Exception
	{
		getUrl();
		corestep.getAveragePriceBookDetails();
	}
}

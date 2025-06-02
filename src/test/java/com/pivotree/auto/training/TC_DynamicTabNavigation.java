package com.pivotree.auto.training;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class TC_DynamicTabNavigation extends BasePageSetup {

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
	@Parameters("Url")
	public void Validate_TabNamesCorrectness(String url) throws Exception
	{
		getUrl(url);
		List<String> expectedTabs = Arrays.asList("Home", "Udemy Courses", "Online Trainings", "Blog","PlaywrightPractice");
		corestep.verifyTabName(expectedTabs);	
	}
	
	@Test(priority=2,enabled=true)
	@Parameters("Url")
	public void Validate_TabNamesNavigatedCorrectly(String url) throws Exception
	{
		getUrl(url);
		List<String> expectedTabs = Arrays.asList("Home", "Udemy Courses", "Online Trainings", "Blog","PlaywrightPractice");
		corestep.verifyTabNavigation(expectedTabs);	
	}

}

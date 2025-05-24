package com.pivotree.auto.training;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;
import ePay_CRM.Test_PageObjects.Repo_BlogSpot;

public class TC_ScrollToElementAndCapture extends BasePageSetup {

	Method_BlogSpot corestep;
	Repo_BlogSpot obj;

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
			obj=new Repo_BlogSpot(getDriver());
		}
	}	
	
	@Test(priority=1,enabled=true)
	public void Validate_scrollToTopAndCapture() throws Exception
	{
		getUrl();
		corestep.scrollToTopAndCapture();	
	}

	@Test(priority=2,enabled=true)
	public void Validate_scrollToBottomAndCapture() throws Exception
	{
		getUrl();
		corestep.scrollToBottomAndCapture();
	}

	@Test(priority=3,enabled=true)
	public void Validate_scrollToElementAndCapture() throws Exception
	{
		getUrl();
		corestep.scrollToElementAndCapture();
	}
	@Test(priority=4,enabled=true)
	public void Validate_captureFullPageScreen() throws Exception
	{
		getUrl();
		corestep.captureFullScreen();
	}
}

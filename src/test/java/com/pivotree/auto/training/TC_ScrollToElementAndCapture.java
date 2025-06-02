package com.pivotree.auto.training;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
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
	@Parameters("Url")
	public void Validate_scrollToTopAndCapture(String url) throws Exception
	{
		getUrl(url);
		corestep.scrollToTopAndCapture();	
	}

	@Test(priority=2,enabled=true)
	@Parameters("Url")
	public void Validate_scrollToBottomAndCapture(String url) throws Exception
	{
		getUrl(url);
		corestep.scrollToBottomAndCapture();
	}

	@Test(priority=3,enabled=true)
	@Parameters("Url")
	public void Validate_scrollToElementAndCapture(String url) throws Exception
	{
		getUrl(url);
		corestep.scrollToElementAndCapture();
	}
	@Test(priority=4,enabled=true)
	@Parameters("Url")
	public void Validate_captureFullPageScreen(String url) throws Exception
	{
		getUrl(url);
		corestep.captureFullScreen();
	}
}

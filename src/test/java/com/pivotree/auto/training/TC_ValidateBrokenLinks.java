package com.pivotree.auto.training;

import org.testng.Assert;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class TC_ValidateBrokenLinks extends BasePageSetup {

	@Test(priority=1,enabled=true)
	public void Validate_Label_BrokenLinks() throws Exception
	{
			
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl();
		corestep.verifyBrokenLinks();
		
		
	}
	
	@Test(priority=2,enabled=true)
	public void Validate_Label_FooterLink() throws Exception
	{
			
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl();
		corestep.verifyFooterLinks();
		
		
	}
}

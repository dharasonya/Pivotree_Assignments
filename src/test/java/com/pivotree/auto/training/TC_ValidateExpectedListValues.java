package com.pivotree.auto.training;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Reusable_Utils.ExcelColumnReader;

import ePay_CRM.Reusable_Utils.TestReader_ExcelData;
import ePay_CRM.Test_ActionMethods.Method_Aldo;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class TC_ValidateExpectedListValues extends BasePageSetup {


	@Test(priority=1,enabled=false)
	@Parameters("Url")
	public void validateListAgainstExpectedValues_blogspot(String url) throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl(url);
		corestep.validateListExpectedValues("Category_Animal_List","List_1","ListValue_Animals");
	}
	
	
	@Test(priority=2,enabled=true)
	@Parameters("Url")
	//(priority=1,dataProvider="ExcelDataProvider",dataProviderClass=ExcelDataExtractor.class,enabled=true)///Working	
	public void validateListAgainstExpectedValues_aldo(String url) throws Exception
	{		
		System.out.println("---- URL---:"+url);
		Method_Aldo corestep=Get_Aldo_Obj();
		getUrl(url);
		corestep.validateListExpectedValues_Handbags("MenuList_aldo","Handbags","Womens","Handbags","Women,Collections");
	}
}



package com.pivotree.auto.training;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Reusable_Utils.ExcelColumnReader;

import ePay_CRM.Reusable_Utils.TestReader_ExcelData;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class TC_ValidateExpectedListValues extends BasePageSetup {

	
	@Test
	//(priority=1,dataProvider="ExcelDataProvider",dataProviderClass=ExcelDataExtractor.class,enabled=true)///Working	
	public void validateListAgainstExpectedValues() throws Exception
	{		
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl();
		corestep.validateListExpectedValues("Category_Animal_List","List_1","ListValue_Animals");
	}
	
	
	
	

}



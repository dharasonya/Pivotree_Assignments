package com.pivotree.auto.training;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Reusable_Utils.TestReader_ExcelData;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;

public class TC_FillPersonalDetails extends BasePageSetup {

	@Test(priority=1,enabled=false)
	@Parameters("Url")
	public void ValidatePageTitle(String url)
	{
		getUrl(url);
		String expectedTitle=getDriver().getTitle();
		Assert.assertEquals(expectedTitle, "Automation Testing Practice","Verification of Title");
	}
	

	@Test(priority=2,enabled=false)
	@Parameters("Url")
	public void ValidateMultiplePageLocators(String url) throws Exception
	{	
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		getUrl(url);
		//corestep.addPersonalDetails("Sana", "sana@gmail.com", "9167111278", "Riverview Apt, Thane East", "male", "Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday",
				//"05/22/2025", "22/06/2023", "01/05/2024","20/05/2024");
	}
	
	@Test(priority=3,dataProvider="ExcelDataProvider",dataProviderClass=TestReader_ExcelData.class,enabled=true)///Working
	@Parameters("Url")
	public void FillPersonalDetails(String... testData) throws Exception
	{
		Method_BlogSpot corestep=Get_BlogSpot_Obj();
		Map<String, String> testFields = mapTestData(testData);
		
		getUrl();
		corestep.addPersonalDetails(testFields.get("Name"), testFields.get("Email"), testFields.get("Phone"), testFields.get("Address"), 
				testFields.get("Gender"), testFields.get("Days"), testFields.get("Country"),
				testFields.get("Colors"), testFields.get("SelectAnimal"), testFields.get("DatePicker1_(mm/dd/yyyy)"),
				testFields.get("DatePicker2_(dd/mm/yyyy)"),testFields.get("DatePicker3_From_(dd/mm/yyyy)"),testFields.get("DatePicker3_To_(dd/mm/yyyy)"));
	}
	
	public static Map<String, String> mapTestData(String[] testData) {
        // Mapping column values dynamically for easier access
        Map<String, String> testMap = new HashMap<>();
        String[] columnNames = {"Name","Email","Phone","Address","Gender","Days","Country","Colors","SelectAnimal","DatePicker1_(mm/dd/yyyy)","DatePicker2_(dd/mm/yyyy)","DatePicker3_From_(dd/mm/yyyy)","DatePicker3_To_(dd/mm/yyyy)"};
        
        for (int i = 0; i < columnNames.length && i < testData.length; i++) {
            testMap.put(columnNames[i], testData[i]);
        }
        return testMap;
    }	
}

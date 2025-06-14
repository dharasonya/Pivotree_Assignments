package ePay_CRM.Test_ActionMethods;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Reusable_Utils.CallListeners;
import ePay_CRM.Reusable_Utils.ScrollHandler;
import ePay_CRM.Reusable_Utils.WaitUtils;
import ePay_CRM.Test_PageObjects.Repo_Aldo;
import ePay_CRM.Test_PageObjects.Repo_BlogSpot;
import ePay_CRM.WrapMethods.WrapperMethods;
import ePay_CRM.Reusable_Utils.CommonLogger;
import ePay_CRM.Reusable_Utils.ExcelColumnReader;

public class Method_Aldo extends BasePageSetup{

	CallListeners event=new CallListeners();
	Repo_Aldo obj;
	WebDriver driver;
	WaitUtils wait;
	WrapperMethods action;
	ScrollHandler scroll;

	public Method_Aldo(WebDriver driver) throws Exception {
		//	super(driver);
		this.driver=driver;
		obj =new Repo_Aldo(driver);	
		wait=new WaitUtils(driver);
		action=new WrapperMethods(driver);
		scroll=new ScrollHandler(driver);
	}  


	public void validateListExpectedValues_Handbags(String fileName, String sheetName,String ColumnName,String Category,
			String SubCategory) throws Exception {
		
		List<String> getTotalHandBagItemList_Women=new ArrayList<String>();
		List<String> getTotalHandBagItemList_Collection=new ArrayList<String>();

		int index=0;
		for(WebElement menuValues:wait.waitForListOfAllElementsToBeVisible(obj.getMenuList,5))
		{
			String menuName=menuValues.getText();

			index++;
			if(menuName.equalsIgnoreCase(Category))
			{
				// Create Actions object
				Actions actions = new Actions(driver);
				// Perform hover action
				actions.moveToElement(obj.getMenuName(index)).perform();
				
				switch(SubCategory)
					{
						case "Women":
						{
							Assert.assertEquals(SubCategory, "Women");
							for(WebElement getItemCategory_Women:wait.waitForListOfAllElementsToBeVisible(obj.getHangListBagsItem_Women, 5))
							{
								getTotalHandBagItemList_Women.add(getItemCategory_Women.getText());
							}
							this.ExcelReader(fileName, sheetName, ColumnName, getTotalHandBagItemList_Women);
							break;
						}
						case "Collections":
						{
							Assert.assertEquals(SubCategory, "Collections");
							for(WebElement getItemCategory_Women:wait.waitForListOfAllElementsToBeVisible(obj.getHangListBagsItem_Collections, 5))
							{
								getTotalHandBagItemList_Collection.add(getItemCategory_Women.getText());
							}
							this.ExcelReader(fileName, sheetName, ColumnName, getTotalHandBagItemList_Collection);
							break;
						}		
					}	
			}
		}
	}
	
	public void ExcelReader(String fileName,String sheetName,String ColumnName,List<String> listOfCatValues)
	{
		boolean areListsEqual=false;
		ExcelColumnReader reader = new ExcelColumnReader();
		//Map<String, List<String>> extractedData = reader.readColumnWiseData(fileName, sheetName);
		Map<String, List<String>> extractedData = reader.readColumnWiseData(fileName, sheetName);

		//System.out.println("--FILENAME : "+fileName + " _SheetName : "+sheetName);
		List<String> expectedValues = extractedData.get(ColumnName); // Get expected values from Excel  

		if (expectedValues == null || expectedValues.isEmpty()) {
			System.out.println("No data available for column: " + ColumnName);
			//continue; // Skip to next iteration
		}
		
		// Print column-wise data
		/*System.out.println("Column: " + ColumnName);
		for (int j = 0; j < expectedValues.size(); j++) {  
			System.out.println("  - " + expectedValues.get(j)); // Using simple loop instead of foreach
		}*/
		
		// Convert lists to Sets for comparison
		Set<String> webValuesSet = new HashSet<>(listOfCatValues);
		Set<String> expectedValuesSet = new HashSet<>(expectedValues);

		// Find missing & extra values
		Set<String> missingFromWeb = new HashSet<>(expectedValuesSet);
		missingFromWeb.removeAll(webValuesSet);

		Set<String> extraInWeb = new HashSet<>(webValuesSet);
		extraInWeb.removeAll(expectedValuesSet);
		
		
		
		if (webValuesSet.equals(expectedValuesSet)) {
			CommonLogger.log("Both lists contain the same elements (order ignored)!");
			areListsEqual=true;
		} 
		else {
			CommonLogger.log("---Lists have different elements!---");
			CommonLogger.log("Missing in Web dropdown (Expected but not found): " + missingFromWeb);
			CommonLogger.log("Extra in Web dropdown (Found but not expected): " + extraInWeb);
			areListsEqual=false;
		}
		
		CommonLogger.log("-----------------------------------------------------"); // Separator for readability
		if(areListsEqual==true)
		{
			CommonLogger.log("Both lists contain the same elements (order ignored)!");
			Assert.assertTrue(true, "Verification of Values in list");
		}
		else
		{
			CommonLogger.log("Lists have different elements!");
			Assert.assertTrue(false, "Verification of Values in list");
		}
	}
}
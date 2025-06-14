package ePay_CRM.Test_ActionMethods;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import ePay_CRM.Test_PageObjects.Repo_BlogSpot;
import ePay_CRM.WrapMethods.WrapperMethods;
import ePay_CRM.Reusable_Utils.CommonLogger;
import ePay_CRM.Reusable_Utils.ExcelColumnReader;

public class Method_BlogSpot extends BasePageSetup{

	CallListeners event=new CallListeners();
	Repo_BlogSpot obj;
	WebDriver driver;
	WaitUtils wait;
	WrapperMethods action;
	ScrollHandler scroll;

	public Method_BlogSpot(WebDriver driver) throws Exception {
		//	super(driver);
		this.driver=driver;
		obj =new Repo_BlogSpot(driver);	
		wait=new WaitUtils(driver);
		action=new WrapperMethods(driver);
		scroll=new ScrollHandler(driver);
	}  

	public void addPersonalDetails(String Name, String Email, String Phone, String Address,String Gender, String Days, String Country,
			String Colors,String SelectAnimal,String Date1, String Date2, String StartDate, String endDate) {


		CommonLogger.log("Initiated the process of filling in details on Section-PersonalDetails-GUI Elements");

		action.enterAndVerify(obj.EnterName, Name, "Name");
		event.printSnap("Name Entered");
		
		action.enterAndVerify(obj.EnterEmail, Email, "Email");
		event.printSnap("Email Entered");
		
		action.enterAndVerify(obj.EnterPhone, Phone, "Phone");
		event.printSnap("Phone No. Entered");
		
		action.enterAndVerify(obj.EnterAddress, Address, "Address");
		event.printSnap("Address Entered");
		
		action.selectRadioButton(obj.selectGender, obj.getGenderElement(Gender), Gender, "Gender");
		event.printSnap("Gender Selected");
		
		//Convert comma-separated Days into a List
		List<String> daysList = Arrays.asList(Days.split(",")); // Splitting by ',' 

		for(int i=0;i<daysList.size();i++) 
		{ 
			action.selectCheckBoxes(obj.chooseMultipleDays, daysList.get(i),obj.getValueElement(daysList.get(i).toLowerCase()), "Days"); 
		}
		action.selectValueAndVerify(obj.selectCountry, Country, "Country Selection");
		event.printSnap("Country Selected");
		
		this.selectColors(Colors);
		event.printSnap("Colors Selected");
		
		action.selectValueAndVerify(obj.getAnimalsList, SelectAnimal, "Select Sorted List Animals");
	
		action.enterAndVerify(obj.enterDatePicker1, Date1, "Date 1");
		this.selectDate(Date2);
		action.enterAndVerify(obj.SelectStartDatePicker3, StartDate, "StartDate");
		action.enterAndVerify(obj.SelectEndDatePicker3, endDate, "EndDate");
		event.printSnap("Dates Entered/Selected");

		CommonLogger.log("The process of adding Personal details has been completed.");
	}

	public void selectDateFromPicker(String Date2) {
		CommonLogger.log("Initiated the process of Date Section-PersonalDetails-GUI Elements");
		this.selectDate(Date2);
		scroll.scrollToElement(obj.selectDatePicker2);
		event.printSnap("Date Selection");
		CommonLogger.log("The process of Date-Selection from Picker has been completed.");
	}

	public void selectDate(String dateToSelect) {
		try {
			Actions actions = new Actions(driver);
			actions.moveByOffset(10, 10).click().perform(); // Click on an empty area

			obj.selectDatePicker2.click();

			// Extract year, month, and day from the input date
			String[] dateParts = dateToSelect.split("/");
			String year = dateParts[2];  // Example: "2024"

			String month = getMonth(dateParts[1]); // Example: "Jun"
			String day = dateParts[0];   // Example: "22"

			// Select Year
			Select selectYear = new Select(obj.selectYear);
			List<WebElement> yearOptions = selectYear.getOptions();
			boolean yearFound = yearOptions.stream().anyMatch(e -> e.getText().equals(year));

			if (yearFound) {
				selectYear.selectByVisibleText(year);
				// Assert that the selected year is correct
				Assert.assertEquals(selectYear.getFirstSelectedOption().getText(), year, "Verify Selected Year");
			} else {
				Assert.fail("Year " + year + " not found in dropdown!");
			}

			// Select Month
			Select selectMonth = new Select(obj.selectMonth);
			List<WebElement> monthOptions = selectMonth.getOptions();
			boolean monthFound = monthOptions.stream().anyMatch(e -> e.getText().equals(month));

			if (monthFound) {
				selectMonth.selectByVisibleText(month);
				// Assert that the selected month is correct
				Assert.assertEquals(selectMonth.getFirstSelectedOption().getText(), month, "Verify Selected Month");

			} else {
				Assert.fail("Month " + month + " not found in dropdown!");
			}

			// Select Day
			boolean dayFound = false;
			for (WebElement dayElement : obj.selectDay) {
				if (dayElement.getText().equals(day)) {
					dayElement.click();
					Assert.assertEquals(dayElement.getText(), day,"Verify Selected Day");
					dayFound = true;
					break;
				}
			}

			if (!dayFound) {
				Assert.fail("Day " + day + " not found in the date picker!");
			}

			CommonLogger.log("Date selected successfully: " + dateToSelect);

		} catch (Exception e) {
			CommonLogger.log("RunTime Exception Occurred while selecting date: " + dateToSelect + " - " + e);
			CommonLogger.errorLog(e);
			Assert.fail("Exception occurred while selecting date: " + e.getMessage());
		}
	}

	private String getMonth(String monthNum) {
		// TODO Auto-generated method stub

		switch (monthNum)
		{
		case "01":
		{
			return "Jan";
		}

		case "02":
		{
			return "Feb";
		}
		case "03":
		{
			return "Mar";
		}
		case "04":
		{
			return "April";
		}
		case "05":
		{
			return "May";
		}
		case "06":
		{
			return "Jun";
		}
		case "07":
		{
			return "Jul";
		}
		case "08":
		{
			return "Aug";
		}
		case "09":
		{
			return "Sep";
		}
		case "10":
		{
			return "Oct";
		}
		case "11":
		{
			return "Nov";
		}
		case "12":
		{
			return "Dec";
		}

		}
		return null;
	}

	public void select_deselect_Checkboxes(String Days) {
		CommonLogger.log("Initiated the process of Dynamic checkbox selection/De-selection -Section-PersonalDetails-GUI Elements");
		//Convert comma-separated Days into a List

		scroll.scrollToElement(obj.DaysLabel);

		List<String> daysList = Arrays.asList(Days.split(",")); // Splitting by ',' 
		for(int i=0;i<daysList.size();i++) 
		{ 
			action.selectCheckBoxes(obj.chooseMultipleDays, daysList.get(i),obj.getValueElement(daysList.get(i).toLowerCase()), "Days"); 
		}
		event.printSnap("Checkbox selected");
		CommonLogger.log("The process of Dynamic Checkbox selection/deselection has been completed.");
	}

	public void getMaxBookDetails() {

		CommonLogger.log("Initiated the process of Finding HighestPrice Book details on Section-StaticWebTable-GUI Elements");

		int maxPrice = Integer.parseInt(obj.ListPrice.get(0).getText()); // Initialize with first price

		// Loop to find max price and its index
		for (int i = 0; i < obj.ListPrice.size(); i++) {
			int currValue = Integer.parseInt(obj.ListPrice.get(i).getText());

			if (currValue > maxPrice) {
				maxPrice = currValue;
			}
		}
		int expectedMinPrice=3000;
		Assert.assertEquals(expectedMinPrice, maxPrice,"Verify Max Price of Book");
		CommonLogger.log("----Highest Price Book Details ----: "+maxPrice);
		CommonLogger.log("Highest Price Book BookName : "+getBookName(maxPrice));
		CommonLogger.log("Highest Price AuthorName : "+this.getAuthorName(maxPrice));
		CommonLogger.log("Highest Price Subject : "+this.getSubjectName(maxPrice));

		scroll.scrollToElement(obj.LabelStaticWebTable);
		event.printSnap("Static Web Table");
		CommonLogger.log("The process of Finding HighestBookDetails has been completed.");
	}

	public void getMinBookDetails() {

		CommonLogger.log("Initiated the process of Finding LowestPrice Book details on Section-StaticWebTable-GUI Elements");

		int minPrice = Integer.parseInt(obj.ListPrice.get(0).getText()); // Initialize with first price

		// Loop to find max price and its index
		for (int i = 0; i < obj.ListPrice.size(); i++) {
			int currValue = Integer.parseInt(obj.ListPrice.get(i).getText());

			if (currValue < minPrice) {
				minPrice = currValue;
			}
		}
		int expectedMinPrice=300;
		Assert.assertEquals(expectedMinPrice, minPrice,"Verify Min Price of Book");
		CommonLogger.log("----Lowest Price Book Details ----: "+minPrice);
		CommonLogger.log("Lowest Price Book BookName : "+getBookName(minPrice));
		CommonLogger.log("Lowest Price AuthorName : "+this.getAuthorName(minPrice));
		CommonLogger.log("Loweser Price Subject : "+this.getSubjectName(minPrice));
		scroll.scrollToElement(obj.LabelStaticWebTable);
		event.printSnap("Static Web Table");
		CommonLogger.log("The process of Finding LowestBookDetails has been completed.");
	}

	private List<String> getBookName(int Price) {
		// TODO Auto-generated method stub

		List<String> bookList=new ArrayList<String>();
		for(int i=0;i<obj.ListBookName.size();i++)
		{
			if(Integer.parseInt(obj.ListPrice.get(i).getText())==Price)
			{
				bookList.add(obj.getBookName(i+2));
			}
		}
		return bookList;
	}

	private List<String> getAuthorName(int Price) {
		// TODO Auto-generated method stub

		List<String> bookList=new ArrayList<String>();
		for(int i=0;i<obj.ListAuthor.size();i++)
		{
			if(Integer.parseInt(obj.ListPrice.get(i).getText())==Price)
			{
				bookList.add(obj.getAuthorName(i+2));
			}
		}

		return bookList;
	}

	private List<String> getSubjectName(int Price) {
		// TODO Auto-generated method stub

		List<String> bookList=new ArrayList<String>();
		for(int i=0;i<obj.ListSubject.size();i++)
		{
			if(Integer.parseInt(obj.ListPrice.get(i).getText())==Price)
			{
				bookList.add(obj.getSubjectName(i+2));
			}
		}

		return bookList;
	}

	public void getAveragePriceBookDetails() {
		int totalElements = 0;
		float sum = 0;
		float ExpectedAvg = 1183.33f;

		for (int i = 0; i < obj.ListPrice.size(); i++) {
			totalElements++;
			sum += Integer.parseInt(obj.ListPrice.get(i).getText());
		}

		float averagePrice = sum / totalElements; // Calculate average
		String roundedAverage = String.format("%.2f", averagePrice);
		Assert.assertEquals(ExpectedAvg, Float.parseFloat(roundedAverage),"Verify Average Book Price");
		CommonLogger.log("--- Average Price of Book Details ---: " + roundedAverage);
	}

	public void verifyTabName(List<String> expectedTabNames) {
		// TODO Auto-generated method stub
		CommonLogger.log("Initiated the process of Comparing the actual tabs with expected tab names list");

		List<WebElement> onScreenTabList=obj.getAllTabNames;

		List<String> actualTabNames=new ArrayList<String>();

		for(WebElement onScreenTabListValues:onScreenTabList)
		{
			actualTabNames.add(onScreenTabListValues.getText());
		}
		Assert.assertEquals(expectedTabNames, actualTabNames,"Verify Actual Tab Names visibility");

		event.printSnap("Tab Names Correctness");
		CommonLogger.log("The process of Comparing Tab Names has been completed.");
	}

	public void verifyTabNavigation(List<String> expectedTabs) throws InterruptedException {
		// TODO Auto-generated method stub
		CommonLogger.log("Initiated the process of Navigating tabs with expected tab names list");

		List<WebElement> onScreenTabList=wait.waitForListOfAllElementsToBeVisible(obj.getAllTabNames, 6);

		List<String> actualTabNames=new ArrayList<String>();

		for(WebElement onScreenTabListValues:onScreenTabList)
		{
			actualTabNames.add(onScreenTabListValues.getText());
		}
		Assert.assertEquals(expectedTabs, actualTabNames,"Verify Actual Tab Names visibility");


		// Navigation to every tab and re-direct back to home
		for(int i=0;i<obj.getAllTabNames.size();i++)
		{
			String tabName=obj.getAllTabNames.get(i).getText();

			if(actualTabNames.contains(tabName))
			{
				action.clickOnButtonAndVerify(obj.getTabName(tabName), "tab");
				event.printSnap("Tab Names Navigated Correctly on "+tabName);
				driver.navigate().back();
				wait.waitForListOfAllElementsToBeVisible(obj.getAllTabNames, 20);
				event.printSnap("Navigated back to Tab on "+tabName);
				Assert.assertTrue(obj.getTabName(tabName).isDisplayed(), "Verify tab is visible: " + obj.getTabName(tabName).getText());
				CommonLogger.log("On Tab :"+tabName+" - Get CurrentUrl :"+driver.getCurrentUrl());
			}
		}


		CommonLogger.log("The process of Navigating of Expected Tabs has been completed.");


	}

	public void switchTabCaptureScreenShot(List<String> expectedTabs) throws InterruptedException {
		CommonLogger.log("Initiated the process of navigating tabs with expected tab names list.");

		List<String> switchedTabsList = new ArrayList<>();
		List<WebElement> onScreenTabList = wait.waitForListOfAllElementsToBeVisible(obj.getAllTabNames, 6);
		List<String> actualTabNames = new ArrayList<>();

		// Fetch all tab names dynamically
		for (WebElement tab : onScreenTabList) {
			actualTabNames.add(tab.getText());
		}

		// Validate expected vs actual tab names
		Assert.assertEquals(actualTabNames, expectedTabs, "Verify actual tab names visibility");

		// Navigate each tab & capture screenshot
		for (String tabName : actualTabNames) {
			if (!switchedTabsList.contains(tabName)) {

				if(tabName.equals("Home"))
				{
					event.printSnap("Tab navigated correctly " + tabName);
					CommonLogger.log("On tab: " + tabName + " | Current URL: " + driver.getCurrentUrl());
				}
				else
				{
					action.clickOnButtonAndVerify(obj.getTabName(tabName), "tab");
					event.printSnap("Tab navigated correctly " + tabName);

					// Ensure navigation back to home when "Home" tab is available
					if (!tabName.equals("Home")) {
						driver.navigate().back();
						event.printSnap("Tab navigated back to " + obj.getTabName("Home").getText());
					}

					// Re-fetch elements before waiting
					onScreenTabList = wait.waitForListOfAllElementsToBeVisible(obj.getAllTabNames, 8);

					// Fix timeout issue by increasing wait time
					WebElement refreshedTab = wait.waitForElementToBeVisible(obj.getTabName(tabName), 10);
					Assert.assertTrue(refreshedTab.isDisplayed(), "Verify tab is visible: " + tabName);

					CommonLogger.log("On tab: " + tabName + " | Current URL: " + driver.getCurrentUrl());

					switchedTabsList.add(tabName);
				}

			}
		}

		CommonLogger.log("Completed navigation of expected tabs.");
	}

	public void validateDateErrors(String startDate, String endDate) {
		// TODO Auto-generated method stub
		CommonLogger.log("Initiated the process of validating Date Valdidation errors");

		if(!startDate.isEmpty() && endDate.isEmpty())
		{
			action.enterAndVerify(obj.SelectStartDatePicker3, startDate, "StartDate");
			action.clickOnButtonAndVerify(obj.onSubmitOfDates, "Submit");
			String expectedError="Please select both start and end dates.";
			Assert.assertEquals(expectedError,obj.Error_SelectBothStartEndDate.getText(),"Verify On-Submit Error");				
			event.printSnap("Validation Error");
		}
		else if(startDate.isEmpty() && !endDate.isEmpty())
		{
			action.enterAndVerify(obj.SelectEndDatePicker3, endDate, "EndDate");
			action.clickOnButtonAndVerify(obj.onSubmitOfDates, "Submit");
			String expectedError="Please select both start and end dates.";
			Assert.assertEquals(expectedError,obj.Error_SelectBothStartEndDate.getText(),"Verify On-Submit Error");				
			event.printSnap("Validation Error");
		}
		else if(startDate.isEmpty() && endDate.isEmpty())
		{
			action.clickOnButtonAndVerify(obj.onSubmitOfDates, "Submit");
			String expectedError="Please select both start and end dates.";
			Assert.assertEquals(expectedError,obj.Error_SelectBothStartEndDate.getText(),"Verify On-Submit Error");				
			event.printSnap("Validation Error");
		}
		else if(!startDate.isEmpty() && !endDate.isEmpty())
		{
			action.enterAndVerify(obj.SelectStartDatePicker3, startDate, "StartDate");
			action.enterAndVerify(obj.SelectEndDatePicker3, endDate, "EndDate");
			action.clickOnButtonAndVerify(obj.onSubmitOfDates, "Submit");

			String expectedError="End date must be after start date.";
			Assert.assertEquals(expectedError,obj.Error_EndDateGreaterThanStartDate.getText(),"Verify On-Submit Error");				
			event.printSnap("Validation Error");	
			CommonLogger.log("Completed the process of validating Date Valdidation errors");
		}
	}

	public void validateFailDateErrors(String startDate, String endDate) {
		CommonLogger.log("Initiated the process of validating Date Validation errors");

		SoftAssert softAssert = new SoftAssert(); // Initialize Soft Assertion

		if (!startDate.isEmpty() && endDate.isEmpty()) {
			action.enterAndVerify(obj.SelectStartDatePicker3, startDate, "StartDate");
			action.clickOnButtonAndVerify(obj.onSubmitOfDates, "Submit");
			String expectedError = "Dummy-Please select both start and end dates.";
			softAssert.assertEquals(obj.Error_SelectBothStartEndDate.getText(), expectedError, "Verify On-Submit Error");
			event.printSnap("Validation Error");
		} 

		else if (startDate.isEmpty() && !endDate.isEmpty()) {
			action.enterAndVerify(obj.SelectEndDatePicker3, endDate, "EndDate");
			action.clickOnButtonAndVerify(obj.onSubmitOfDates, "Submit");
			String expectedError = "Dummy-Please select both start and end dates.";
			softAssert.assertEquals(obj.Error_SelectBothStartEndDate.getText(), expectedError, "Verify On-Submit Error");
			event.printSnap("Validation Error");
		} 

		else if (startDate.isEmpty() && endDate.isEmpty()) {
			action.clickOnButtonAndVerify(obj.onSubmitOfDates, "Submit");
			String expectedError = "Dummy-Please select both start and end dates.";
			softAssert.assertEquals(obj.Error_SelectBothStartEndDate.getText(), expectedError, "Verify On-Submit Error");
			event.printSnap("Validation Error");
		} 

		else if (!startDate.isEmpty() && !endDate.isEmpty()) {
			action.enterAndVerify(obj.SelectStartDatePicker3, startDate, "StartDate");
			action.enterAndVerify(obj.SelectEndDatePicker3, endDate, "EndDate");
			action.clickOnButtonAndVerify(obj.onSubmitOfDates, "Submit");

			String expectedError = "Dummy-End date must be after start date.";
			softAssert.assertEquals(obj.Error_EndDateGreaterThanStartDate.getText(), expectedError, "Verify On-Submit Error");
			event.printSnap("Validation Error");
			CommonLogger.log("Completed the process of validating Date Validation errors");
		}

		softAssert.assertAll(); // ✅ Ensures all errors are displayed at the end
	}

	public void handleAutoSuggestDropdowns(String searchValue, int expectSuggestCount) {
		// TODO Auto-generated method stub
		CommonLogger.log("Initiated the process of validating auto-suggest count");
		List<String> matchedResults=new ArrayList<String>();

		boolean matchFound=false;
		action.enterAndVerify(obj.enterSearchInput, searchValue, "Search Value");
		action.clickOnButtonAndVerify(obj.searchbutton, "Search Button");
		int actualSuggestCount=wait.waitForListOfAllElementsToBeVisible(obj.getSearchResults, 5).size();
		Assert.assertEquals(expectSuggestCount, actualSuggestCount,"Verify Suggest Count");
		CommonLogger.log("Total Count of Search Results :"+actualSuggestCount);


		for(WebElement suggestValues:obj.getSearchResults)
		{
			if(suggestValues.getText().toLowerCase().contains(searchValue))
			{
				//System.out.println("\n Value : "+suggestValues.getText());
				matchFound=true;
				matchedResults.add(suggestValues.getText());
			}
			else
			{
				matchFound=false;
			}
		}
		CommonLogger.log("\n List of Suggested Results : "+matchedResults);
		if(matchFound==true)
		{

			Assert.assertTrue(true, "Verify Search Result Match Found");
		}
		else
		{
			Assert.fail();
		}


		event.printSnap("Auto-SuggestCount");	
		CommonLogger.log("Completed the process of validating auto-suggest count");

	}

	public void selectLongestAutoSuggestion(String searchValue) {
		// TODO Auto-generated method stub
		CommonLogger.log("Initiated the process of select longest auto-suggest value");

		int maxlenSearchIndex=0;
		int maxLenValue=0;
		String actualSearchValue="";

		action.enterAndVerify(obj.enterSearchInput, searchValue, "Search Value");
		action.clickOnButtonAndVerify(obj.searchbutton, "Search Button");
		for(WebElement suggestValues:wait.waitForListOfAllElementsToBeVisible(obj.getSearchResults, 5))
		{
			actualSearchValue=suggestValues.getText().toLowerCase();

			if(actualSearchValue.toLowerCase().contains(searchValue))
			{
				int lenValue=actualSearchValue.length();
				if(lenValue>maxLenValue)
				{
					maxLenValue=lenValue;
				}
			}
		}
		event.printSnap("Auto-SelectLongestSuggestValues");	
		maxlenSearchIndex=longSearchValueName(maxLenValue);
		action.clickOnButtonAndVerify(obj.getSuggestSearchResult(maxlenSearchIndex), "Search");
		action.handleWindowSwitch(driver);
		Assert.assertEquals(driver.getTitle(), "Automation Testing Practice","Verify Land to Home");

		CommonLogger.log("Completed the process of validating auto-suggest count");

	}

	public int longSearchValueName(int maxLength) {
		int indexSearchName = 1; // Use -1 to indicate no match found
		WebElement searchValueName = null;

		// Check if there are search results
		if (obj.getSearchResults.size() == 1) {  
			// Directly check the only available record
			if (obj.getSearchResults.get(0).getText().length() == maxLength) {
				indexSearchName = 0;
				searchValueName = obj.getSuggestSearchResult(indexSearchName);
				CommonLogger.log("Longest Suggest Value : " + searchValueName.getText());
			}
		} else {  
			// Iterate over multiple search results
			for (int i = 0; i < obj.getSearchResults.size(); i++) {
				if (obj.getSearchResults.get(i).getText().length() == maxLength) {
					indexSearchName = i;
					indexSearchName++;
					searchValueName = obj.getSuggestSearchResult(indexSearchName);
					CommonLogger.log("Longest Suggest Value : " + searchValueName.getText());
				}
			}
		}

		return indexSearchName; // Return valid index or -1 if no match found
	}

	public void scrollToTopAndCapture() {
		// TODO Auto-generated method stub
		CommonLogger.log("Started scrollToTopAndCapture process");
		scroll.scrollToTop();
		event.printSnap("scrollToTopAndCapture");
		CommonLogger.log("Completed scrollToTopAndCapture process");
	}

	public void scrollToBottomAndCapture() {
		// TODO Auto-generated method stub
		CommonLogger.log("Started scrollToBottomAndCapture process");
		scroll.scrollToBottom();
		event.printSnap("scrollToBottomAndCapture");
		CommonLogger.log("Completed scrollToBottomAndCapture process");
	}

	public void scrollToElementAndCapture() {
		// TODO Auto-generated method stub
		CommonLogger.log("Started scrollToElementAndCapture process");
		scroll.scrollToElement(obj.LabelStaticWebTable);
		event.printSnap("scrollToElementAndCapture");
		CommonLogger.log("Completed scrollToElementAndCapture process");
	}

	public void captureFullScreen() {
		// TODO Auto-generated method stub
		CommonLogger.log("Started captureFullScreen process");
		event.fullprintSnap("captureFullScreen");
		CommonLogger.log("Completed captureFullScreen process");
	}

	public void verify_PersonalDetailsLabelNames() {
		// TODO Auto-generated method stub
		CommonLogger.log("Started SoftAssertion Validation process");
		SoftAssert softAssert=new SoftAssert();

		softAssert.assertEquals(obj.getNameLabel.getText(), "Name:","Verify Label Name");
		softAssert.assertEquals(obj.getEmailLabel.getText(), "Email:","Verify Email Name");
		softAssert.assertEquals(obj.getPhoneLabel.getText(), "Phone:","Verify Phone Name");
		softAssert.assertEquals(obj.getAddressLabel.getText(), "Address:","Verify Address Name");
		softAssert.assertEquals(obj.getGenderLabel.getText(), "Gender:","Verify Gender Name");

		event.fullprintSnap("verify_PersonalDetailsLabelNames_UI");
		CommonLogger.log("Completed SoftAssertion Validation process");
		softAssert.assertAll();

	}

	public void selectMatchedProduct(String productName) throws InterruptedException {
		// TODO Auto-generated method stub

		CommonLogger.log("Product Verification process started");
		scroll.scrollToElement(obj.getPaginationWebTableName);
		int tempIndex=0;
		int prodNameIndex=0;
		for(int i=0;i<obj.getPaginationList.size();i++)
		{
			tempIndex++;
			obj.getPageNumber(tempIndex).click();

			for(WebElement prodValues:obj.getProductName)
			{
				prodNameIndex++;
				String actualProdName=prodValues.getText();

				if(actualProdName.toLowerCase().contains(productName.toLowerCase()))
				{
					WebElement chkBox=obj.getProductSelectCheckBox(prodNameIndex);
					chkBox.click();
					Assert.assertTrue(chkBox.isSelected(), "Match Product Checkbox selected");

				}

			}
			event.printSnap("Pagination "+tempIndex);
			prodNameIndex=0;
		}

		CommonLogger.log("Product Verification process completed");
	}

	public void verifyBrokenLinks() throws InterruptedException {
		CommonLogger.log("Starting verification of broken links");

		// Scroll to the section containing broken links
		scroll.scrollToElement(obj.getBrokenLinkLabelName);

		List<String> brokenLinkList = action.checkBrokenLinks(obj.getBrokenLinksUrl);
		int totalBrokenLinks = brokenLinkList.size();
		CommonLogger.log("Found " + totalBrokenLinks + " broken links to verify.");

		for (int i = 0; i < totalBrokenLinks; i++) {
			WebElement linkName = obj.getLinkName(i + 1); // Ensure correct indexing
			String linkNameValue = linkName.getText();

			action.clickOnButtonAndVerify(linkName, "Broken Link :"+linkNameValue);

			// Wait for the new page to load completely
			wait.waitForPageToLoad(10);
			// Capture screenshot **before navigating back**
			event.printSnap("Captured Screenshot for Broken Link " + linkNameValue);

			// Navigate back to the main page
			driver.navigate().back();
			wait.waitForListOfAllElementsToBeVisible(obj.getFooterLinksUrl,10);
			//.
		}
		CommonLogger.log("✅ Completed verification of all broken links.");
	}

	public void verifyFooterLinks() {
		// TODO Auto-generated method stub

		CommonLogger.log("Starting verification of Footer links");

		// Scroll to the section containing broken links
		scroll.scrollToElement(obj.getFooterLinkLabelName);

		List<String> brokenLinkList = action.checkBrokenLinks(obj.getFooterLinksUrl);
		int totalBrokenLinks = brokenLinkList.size();
		CommonLogger.log("Found " + totalBrokenLinks + " broken links to verify.");

		if(totalBrokenLinks>0)
		{
			for (int i = 0; i < totalBrokenLinks; i++) {
				WebElement linkName = obj.getLinkName(i + 1); // Ensure correct indexing
				String linkNameValue = linkName.getText();

				action.clickOnButtonAndVerify(linkName, "Broken Link :"+linkNameValue);

				wait.waitForPageToLoad(10);
				// Capture screenshot **before navigating back**
				event.printSnap("Captured Screenshot for Broken Link " + linkNameValue);

				// Navigate back to the main page
				driver.navigate().back();
				wait.waitForListOfAllElementsToBeVisible(obj.getFooterLinksUrl,5);
			}
		}
		else
		{
			for (int i = 0; i < obj.getFooterLinksUrl.size(); i++) {
				WebElement linkName = obj.getFooterLinkName(i + 1); // Ensure correct indexing
				String linkNameValue = linkName.getText();

				action.clickOnButtonAndVerify(linkName, "Valid Link :"+linkNameValue);

				// Wait for the new page to load completely
				wait.waitForPageToLoad(10);

				// Capture screenshot **before navigating back**
				event.printSnap("Captured Screenshot for Valid Link " + linkNameValue);

				// Navigate back to the main page
				driver.navigate().back();
				wait.waitForListOfAllElementsToBeVisible(obj.getFooterLinksUrl,5);
			}
		}
		CommonLogger.log("Completed verification of all Broken/Valid links.");
	}

	public void selectColors(String Colors) {
		// TODO Auto-generated method stub
		CommonLogger.log("Initiated the process of Selecting Color Values");
		scroll.scrollToElement(obj.getColorsLabelName);
		event.printSnap("Color List");
		Select select=new Select(obj.getColorsList);
		List<WebElement> getColorsList=select.getOptions();

		for(int i=0;i<getColorsList.size();i++)
		{
				String tempColor=getColorsList.get(i).getText();	
				if(tempColor.equals(Colors))
				{
					select.selectByVisibleText(tempColor);
				}
		
		}
		scroll.scrollToDropdownOption(obj.getColorsList);
		CommonLogger.log("Completed the process of Color Selection");
	}
	
	public void identifyDuplicateItems() {
		// TODO Auto-generated method stub
		CommonLogger.log("Initiated the process of Idenitfying Duplicate Values");
		scroll.scrollToElement(obj.getColorsLabelName);
		int duplicateCounterchk=0;
		List<String> dupValues=new ArrayList<String>();
		List<String> uniqueValues=new ArrayList<String>();

		event.printSnap("Color List");
		Select select=new Select(obj.getColorsList);
		List<WebElement> getColorsList=select.getOptions();

		for(int i=0;i<getColorsList.size();i++)
		{
			String tempColor1=getColorsList.get(i).getText();
			for(int j=0;j<getColorsList.size();j++)
			{
				String tempColor2=getColorsList.get(j).getText();	
				if(tempColor1.equals(tempColor2) && !dupValues.contains(tempColor2))
				{
					duplicateCounterchk++;
					//select.selectByVisibleText(tempColor1);
				}
			}

			if(duplicateCounterchk>1)
			{
				dupValues.add(tempColor1);
			}
			else if(duplicateCounterchk==1)
			{
				uniqueValues.add(tempColor1);
			}
			duplicateCounterchk=0;
		}

		// Code to select only Duplicate Items
		for(String colorValues:dupValues)
		{
			select.selectByVisibleText(colorValues);	
			Assert.assertTrue(select.getFirstSelectedOption().isSelected(), "Verify Option is selected");// to check
		}

		scroll.scrollToDropdownOption(obj.getColorsList);
		CommonLogger.log("\n List of Duplicate values : "+dupValues);
		CommonLogger.log("\n List of unique values : "+uniqueValues);
		CommonLogger.log("Completed the process of Idenitfying Duplicate Values");
	}

	public void validateListExpectedValues(String fileName, String sheetName,String ColumnNames) {
		// TODO Auto-generated method stub

		scroll.scrollToElement(obj.getSortListLabelName);
		ExcelColumnReader reader = new ExcelColumnReader();
		Map<String, List<String>> extractedData = reader.readColumnWiseData(fileName, sheetName);
		List<String> webList=new ArrayList<String>();

		/*
		// Print extracted column-wise data
		extractedData.forEach((columnName, values) -> {
		if(columnName.equalsIgnoreCase("ListValue_Animals"))
		{
			System.out.println("✅ ---EXTRACT--" + columnName + ": " + values);
		}
		});
		 */

		// Get expected values from Excel
		List<String> expectedValues = extractedData.get(ColumnNames); // Column Name from Excel
		System.out.println("Expected Values from Excel: " + expectedValues);

		// Get values from web dropdown
		Select select=new Select(obj.getAnimalsList);
		List<WebElement> webDerivedList=select.getOptions();

		for(WebElement webDerviedListValues:webDerivedList)
		{
			webList.add(webDerviedListValues.getText());
		}
		System.out.println("Web Extracted Values: " + webList);

		// Assertion: Check if both lists are equal
		Assert.assertEquals(webList, expectedValues, "//div[@id='aldo_handbags']/div[2]/div/div[3]/aMismatch between Web dropdown values and Excel data!");

		System.out.println("Assertion passed: Web values match expected values.");
	}
}
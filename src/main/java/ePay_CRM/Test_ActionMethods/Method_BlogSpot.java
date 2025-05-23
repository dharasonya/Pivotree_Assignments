package ePay_CRM.Test_ActionMethods;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Reusable_Utils.CallListeners;
import ePay_CRM.Reusable_Utils.ScrollHandler;
import ePay_CRM.Reusable_Utils.WaitUtils;
import ePay_CRM.Test_PageObjects.Repo_BlogSpot;
import ePay_CRM.WrapMethods.WrapperMethods;
import ePay_CRM.Reusable_Utils.CommonLogger;

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

	public void addPersonalDetails(String Name, String Email, String Phone, String Address,
			String Gender, String Days, String Date1, String Date2, String StartDate, String endDate) {
		CommonLogger.log("Initiated the process of filling in details on Section-PersonalDetails-GUI Elements");

		action.enterAndVerify(obj.EnterName, Name, "Name");
		action.enterAndVerify(obj.EnterEmail, Email, "Email");
		action.enterAndVerify(obj.EnterPhone, Phone, "Phone");
		action.enterAndVerify(obj.EnterAddress, Address, "Address");
		action.selectRadioButton(obj.selectGender, obj.getGenderElement(Gender), Gender, "Gender");

		//Convert comma-separated Days into a List
		List<String> daysList = Arrays.asList(Days.split(",")); // Splitting by ',' 

		for(int i=0;i<daysList.size();i++) 
		{ 
			action.selectCheckBoxes(obj.chooseMultipleDays, daysList.get(i),obj.getValueElement(daysList.get(i).toLowerCase()), "Days"); 
		}

		action.enterAndVerify(obj.enterDatePicker1, "05/03/2025", "Date 1");
		this.selectDate(Date2);
		action.enterAndVerify(obj.SelectStartDatePicker3, StartDate, "StartDate");
		action.enterAndVerify(obj.SelectEndDatePicker3, endDate, "EndDate");

		event.printSnap("Personal Details Added");
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
}
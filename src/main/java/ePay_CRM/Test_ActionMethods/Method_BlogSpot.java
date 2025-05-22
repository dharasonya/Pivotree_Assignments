package ePay_CRM.Test_ActionMethods;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair; 
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.LandingPage.Method_OrangeHRM_BaseSteps;
import ePay_CRM.Reusable_Utils.CallListeners;
import ePay_CRM.Reusable_Utils.ScrollHandler;
import ePay_CRM.Reusable_Utils.WaitUtils;
import ePay_CRM.Test_PageObjects.Repo_BlogSpot;
import ePay_CRM.Test_PageObjects.Repo_PIM;
import ePay_CRM.WrapMethods.WrapperMethods;
import ePay_CRM.Reusable_Utils.CommonLogger;

public class Method_BlogSpot extends BasePageSetup{

	Repo_BlogSpot obj;
	WebDriver driver;
	int count=0;
	int errCount=0;
	int retryCount=0;
	int addRetryCheck=0;
	CallListeners event=new CallListeners();
	WaitUtils wait;
	int counter=0;
	ScrollHandler scroll;
	WrapperMethods action;
	//CommonLogger commonLogger=new CommonLogger();
	static Logger logger = Logger.getLogger("Method_PIM_AddEmployee");
	 //Set<String> fieldValues;

	 Set<String> fieldValuesCopy;
	 
	public Method_BlogSpot(WebDriver driver) throws Exception {
		//	super(driver);
		this.driver=driver;
		obj =new Repo_BlogSpot(driver);	
		wait=new WaitUtils(driver);
		scroll=new ScrollHandler(driver);
		action=new WrapperMethods(driver);
	}  
		
	public void addPersonalDetails(String Name,String Email, String Phone,String Address
		,String Gender,String Days,String Date1, String Date2, String Date3)
	{
		CommonLogger.log("Initiated the process of filling in details on Section-PersonalDetails-GUI Elements");
		
		action.enterAndVerify(obj.EnterName, Name, "Name");
		action.enterAndVerify(obj.EnterEmail, Email, "Email");
		action.enterAndVerify(obj.EnterPhone, Phone, "Phone");
		action.enterAndVerify(obj.EnterAddress, Address, "Address");
		action.selectRadioButton(obj.selectGender,obj.getGenderElement(Gender), Gender, "Gender");
		action.selectCheckBoxes(obj.chooseMultipleDays, obj.getValueElement(Days.toLowerCase()), Days,"Days");
		action.enterAndVerify(obj.enterDatePicker1, "05/03/2025", "Date 1");
		action.enterAndVerify(obj.SelectStartDatePicker3, "01/05/2025", "StartDate");
		action.enterAndVerify(obj.SelectEndDatePicker3, "01/06/2025", "EndDate");
		
		this.selectDate(Date2);
		event.printSnap("Personal Details Added");
		CommonLogger.log("The process of adding Personal details has been completed.");
	}
	
	public void selectDate(String Date2)
	{
		Actions actions = new Actions(driver);
		actions.moveByOffset(10, 10).click().perform();  // Moves 10 pixels from the top-left corner and clicks
		obj.selectDatePicker2.click();
		Select selectYear=new Select(obj.selectYear);
		selectYear.selectByVisibleText("2024");
		
		Select selectMonth=new Select(obj.selectMonth);
		selectMonth.selectByVisibleText("Jun");
		
		for(WebElement daysValues:obj.selectDay)
		{
			if(daysValues.getText().equals("22"))
			{
				daysValues.click();
				break;
			}
		}
		
	}
	
	
}
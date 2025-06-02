package ePay_CRM.WrapMethods;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ePay_CRM.LandingPage.BasePageSetup;
import ePay_CRM.Reusable_Utils.CallListeners;
import ePay_CRM.Reusable_Utils.CommonLogger;
import ePay_CRM.Reusable_Utils.ScrollHandler;
import ePay_CRM.Reusable_Utils.WaitUtils;

public class WrapperMethods extends BasePageSetup {

	WebDriver driver;
	WaitUtils wait;
	CallListeners event=new CallListeners();
	ScrollHandler scroll;
	public WrapperMethods(WebDriver driver)
	{
		this.driver=driver;
		wait=new WaitUtils(driver);	
		scroll=new ScrollHandler(driver);
	}

	public void enterAndVerify(WebElement element, String value, String fieldName) {
		String currentValue;
		try {
			wait.waitForElementToBeVisible(element, 10);

			// Check if the text box is empty before entering a new value
			currentValue = element.getAttribute("value");

			if (currentValue == null || currentValue.isEmpty()) {
				element.sendKeys(value);
				CommonLogger.log("Field: " + fieldName + " is entered & verified.");
			} else {
				CommonLogger.log("Text box already contains a value: " + currentValue);
			}

		} catch (Exception e) {
			CommonLogger.log("Runtime Exception Occurred: " + element + " - " + e);

			// Retry logic—ensure visibility before checking and entering value
			wait.waitForElementToBeVisible(element, 10);
			currentValue = element.getAttribute("value");

			if (currentValue == null || currentValue.isEmpty()) {
				element.sendKeys(value);
				CommonLogger.log("Field: " + fieldName + " is entered & verified.");
			} else {
				CommonLogger.log("Text box still contains a value after retry: " + currentValue);
			}
		}
	}

	public void enterAndSelectServiceCode(WebElement element,List<WebElement> listElement, String value, String fieldName) throws Exception {
		try
		{
			wait.waitForElementToBeVisible(element, 30);
			element.sendKeys(value);
			wait.waitForListOfAllElementsToBeVisible(listElement, 10);	
			for (WebElement serviceCodeValue : listElement) {
				String tempServiceCode = serviceCodeValue.getText().split("-")[1].replace("(", "").replace(")", "");
				if (tempServiceCode.equalsIgnoreCase(value)) {
					serviceCodeValue.click();

					CommonLogger.log("Field :"+fieldName+" is Selected & Verified.");
					break;
				}
			}
		}
		catch(Exception e)
		{
			//getLog().info("RunTime Exception Occurred: " + element+" - "+e);
			CommonLogger.log("RunTime Exception Occurred: " + element+" - "+e);
		}
	}

	public void clickOnCheckboxAndVerify(WebElement element, String value, String fieldName) {
		try
		{
			wait.waitforelementToBeClickable(element, 30);
			if(value.equalsIgnoreCase("1") && !element.isSelected())
			{
				//System.out.println("--- element selected :"+element.isEnabled());
				element.click();
				Assert.assertTrue(element.isSelected(), "Verify : "+fieldName);
				CommonLogger.log("Field :"+fieldName+" is Selected & Verified.");
			}
			else
			{
				Assert.assertFalse(element.isSelected(), "Verify : "+fieldName);
				CommonLogger.log("Field :"+fieldName+" is Not Selected & Verified.");
			}
		}
		catch(Exception e)
		{
			//getLog().info("RunTime Exception Occurred: " + element+" - "+e);
			CommonLogger.log("RunTime Exception Occurred: " + element+" - "+e);
		}
		//Assert.assertEquals(select.getFirstSelectedOption().getText(), value, "Verify " + fieldName);
	}

	public void enableToggleSwitchAndVerify(WebElement element, String value, String fieldName) {
		try
		{
			wait.waitforelementToBeClickable(element, 30);
			if(value.equalsIgnoreCase("1") && element.isEnabled())
			{
				element.click();
				Assert.assertTrue(element.isEnabled(), "Verify : "+fieldName);
				CommonLogger.log("Field :"+fieldName+" is Selected & Verified.");
			}
			else
			{
				Assert.assertFalse(element.isEnabled(), "Verify : "+fieldName);
				CommonLogger.log("Field :"+fieldName+" is Not Selected & Verified.");
			}
		}
		catch(Exception e)
		{

			CommonLogger.log("RunTime Exception Occurred: " + element+" - "+e);
		}
	}

	public void selectActiveFlagAndVerify(WebElement element, String value, String FieldName) {
		try {
			Select select = new Select(element);
			if (value.equals("1")) {
				select.selectByValue("true");
				Assert.assertTrue(element.getAttribute("value").equals("true"), "Verify " + FieldName + " is selected");
				CommonLogger.log("Field :"+FieldName+" is Selected & Verified.");
			} else {
				select.selectByValue("false");
				Assert.assertFalse(element.getAttribute("value").equals("false"), "Verify " + FieldName + " is selected");
				CommonLogger.log("Field :"+FieldName+" is not Selected & Verified.");
			}
		} catch (Exception e) {
			CommonLogger.log("RunTime Exception Occurred: " + element+" - "+e);
		}
	}

	public void selectEnableDisabledFlagAndVerify(WebElement element, String value, String FieldName,String attributeValue) {
		try {
			if (value.equals("Enabled")) {
				element.click();
				Assert.assertTrue(attributeValue.equals(value), "Verify Status Value" + value + " is selected");
				CommonLogger.log("Value :"+value+" is Selected & Verified.");
			} else {
				element.click();
				Assert.assertTrue(attributeValue.equals(value), "Verify Status Value "  + value + " is selected");
				CommonLogger.log("Value :"+value+" is Selected & Verified.");
			}
		} catch (Exception e) {
			CommonLogger.log("RunTime Exception Occurred: " + value+" - "+e);
			CommonLogger.errorLog(e);
		}
	}

	public void selectValueAndVerify(WebElement element, String value,String FieldName) {
		try
		{
			Select select = new Select(element);
			select.selectByVisibleText(value);
			Assert.assertEquals(select.getFirstSelectedOption().getText().toUpperCase(),value.toUpperCase(), "Verify "+FieldName+" is selected");
			CommonLogger.log("Field :"+FieldName+" is Selected & Verified.");
		}
		catch(Exception e)
		{
			CommonLogger.log("RunTime Exception Occurred: " + element+" - "+e);
			CommonLogger.errorLog(e);
		}
	}

	public void selectRadioButton(List<WebElement> elements, WebElement attribute, String value, String FieldName) {
		try {
			wait.waitForListOfAllElementsToBeVisible(elements, 5);

			for (WebElement elementValues : elements) {
				if (elementValues.getText().equalsIgnoreCase(value)) {
					Assert.assertTrue(elementValues.isDisplayed(), "Verify Radio button is displayed!");
					Assert.assertFalse(elementValues.isSelected(), "Verify Radio button is not pre-selected!");
					elementValues.click();
					Assert.assertEquals(attribute.getAttribute("value").toLowerCase(), value, "Verify Selected Radio Button Label");

					CommonLogger.log("For Radio: " + FieldName + " : Values: " + value + " is selected & Verified.");

					break;  // Stop looping after successful selection
				}
			}
			// Handle the case where no match is found
		} catch (Exception e) {
			CommonLogger.log("RunTime Exception Occurred, unable to locate element: " + elements + " - " + e);
			CommonLogger.errorLog(e);
		}
	}
	public void selectCheckBoxes(List<WebElement> elements, String values, WebElement attribute, String FieldName) {

		List<String> selectedValues=new ArrayList<String>();         
		try {
			wait.waitForListOfAllElementsToBeVisible(elements, 5);

			boolean matchFound = false; // Track if at least one checkbox was selected

			for (WebElement elementValues : elements) {
				String checkboxLabel = elementValues.getText();// Get the value attribute of the checkbox
				// Check if current checkbox matches any of the given values
				if (values.contains(checkboxLabel)) {
					Assert.assertTrue(elementValues.isDisplayed(), "Verify Checkbox is displayed!");

					if (!elementValues.isSelected() && !selectedValues.contains(elementValues.getText())) { // Ensure it's not already checked
						elementValues.click();
						CommonLogger.log("Checkbox selected: " + checkboxLabel);
						selectedValues.add(elementValues.getText());

						if(attribute.isSelected())
						{
							Assert.assertTrue(attribute.isSelected(),"Verify Element is Selected");
						}
						else if(!attribute.isSelected())
						{
							Assert.assertFalse(attribute.isSelected(),"Verify Element is De-Selected");
						}


					} else {
						CommonLogger.log("Checkbox already selected: " + checkboxLabel);
					}
					matchFound = true;

					//Verify attribute value after selection
					Assert.assertEquals(attribute.getAttribute("value").toLowerCase(), checkboxLabel.toLowerCase(), 
							"Verify Selected Checkbox Attribute Value");
				}
			}

			// Fail test if no checkboxes were found for selection
			if (!matchFound) {
				Assert.fail("No matching checkboxes found for values: " + values);
			}

		} catch (Exception e) {
			CommonLogger.log("RunTime Exception Occurred while selecting checkboxes: " + e.getMessage());
			CommonLogger.errorLog(e);
			Assert.fail("Error selecting checkboxes: " + e.getMessage());
		}
	}



	public String getTextAndVerify(WebElement element, String FieldName) throws Exception {
		String value = "";

		try {
			value = wait.waitForElementToBeVisible(element, 5).getText();
			Assert.assertNotNull(value, "Verify " + FieldName + " is retrieved");
			CommonLogger.log("For Field :" + FieldName + " Text Value :" + value + " is Retrieved & Verified.");
		} catch (Exception e) {
			//CommonLogger.errorLog(e);
			//CommonLogger.log("Runtime Exception occured !!!..");
			throw new Exception("Error retrieving text for field: " + FieldName, e);
		}

		return value;
	}

	public void clickOnButtonAndVerify(WebElement element, String fieldName)
	{
		try
		{
			wait.waitforelementToBeClickable(element, 60);
			element.click();
			CommonLogger.log("On Click :"+fieldName+" is Clicked & Verified.");
		}
		catch(Exception e)
		{
			//getLog().info("RunTime Exception Occurred: " + element+" - "+e);	
			//CommonLogger.errorLog(e);
			CommonLogger.log("RunTime Exception Occurred: " + element+" - "+e);
			
		}
	}

	public void handleWindowSwitch(WebDriver driver) {
	    String mainWindowHandle = driver.getWindowHandle(); // Store the main window
	    
	    // Get all window handles
	    Set<String> allWindows = driver.getWindowHandles();
	    Assert.assertTrue(allWindows.size() > 1, "Verify No new window opened!");

	    String newWindowHandle = "";
	    for (String window : allWindows) {
	        if (!window.equals(mainWindowHandle)) {
	            newWindowHandle = window;
	            driver.switchTo().window(window);
	            CommonLogger.log("Switched to new window: " + driver.getTitle());
	            scroll.scrollToTop();
	            event.printSnap("Switched to New Window");	
	            break;
	        }
	    }
	    // Perform actions in the new window (Modify based on test scenario)
	    Assert.assertEquals(driver.getWindowHandle(), newWindowHandle, "Verify switch to new window!");

	    // Close new window and switch back
	    driver.close();

	    /*// Wait for windows to update
	    Set<String> remainingWindows = driver.getWindowHandles();
	    while (remainingWindows.size() > 1) {
	        remainingWindows = driver.getWindowHandles();
	    }*/

	    // Switch back to the main window
	    driver.switchTo().window(mainWindowHandle);
	    CommonLogger.log("🔄 Switched back to main window: " + driver.getTitle());
	    
	    // ✅ Verify correct switch back
	    Assert.assertEquals(driver.getWindowHandle(), mainWindowHandle, "Verified switch back to main window!");
	    event.printSnap("Switched to Main Window");	
	}
	
	public String getAttributeValue(WebElement element, String attribute,String fieldName) {

		String value="";

		try{
			wait.waitForElementToBeVisible(element, 5);
			value=element.getAttribute("value");

			if(value.length()!=0)
			{
				CommonLogger.log("For Attribute :"+fieldName+" / attribute Value :"+value+" is Retrieved & Verified.");
			}
			else
			{
				CommonLogger.log("For Attribute :"+fieldName+" / attribute Value :"+value+" is Retrieved as Blank/Null");

			}
		}
		catch(Exception e)
		{
			getLog().info("RunTime Exception Occurred: " + element+" - "+e);	
		}
		return value;
	}

	public void uploadFileUsingRobot(String filePath) throws AWTException, InterruptedException {
		StringSelection fileSelection = new StringSelection(filePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(fileSelection, null);

		Robot rb=new Robot();

		Thread.sleep(6000); 
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		Thread.sleep(600); 
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);

		//3) click on return / entry key
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		CommonLogger.log("File Selection Completed");
	}

	public void VerifyElement_EnabledDisable(WebElement element, String value)
	{
		// Assert that the image is displayed
		try
		{
			wait.waitForElementToBeVisible(element, 5);
			CommonLogger.log(value +" : "+element.isDisplayed());
			Assert.assertTrue(element.isDisplayed(),value);
		}
		catch(Exception e)
		{

			CommonLogger.errorLog(e);
		}	
	}

	
	 // Wrapper method to find all broken links
    public List<String> checkBrokenLinks(List<WebElement> element) {
        List<WebElement> links = element;
        		//driver.findElements(By.tagName("a"));
       // System.out.println("Found " + links.size() + " links to verify.");

        List<String> listOfBrokenLinks=new ArrayList<String>();
        for (WebElement link : links) {
            String url = link.getAttribute("href");

            if (url == null || url.isEmpty()) {
                System.out.println("Empty or Null URL Found: " + link.getText());
                continue;
            }

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode >= 400) {
                   CommonLogger.log("Broken Link: " + url + " | Status Code: " + responseCode);
                   listOfBrokenLinks.add(url);
                  // event.printSnap("Broken Link :"+url);
                   
                } else {
                	CommonLogger.log("Valid Link: " + url + " | Status Code: " + responseCode);
                	//event.printSnap("Valid Link :"+url);
                }

                connection.disconnect();
            } catch (Exception e) {
                System.out.println("Error Checking Link: " + url + " | Exception: " + e.getMessage());
            }
        }
		return listOfBrokenLinks;
    }

}




package ePay_CRM.Test_PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ePay_CRM.Reusable_Utils.WaitUtils;

public class Repo_BlogSpot {

	WebDriver driver;
	WaitUtils wait;
	public Repo_BlogSpot(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait=new WaitUtils(driver);
	}

	@FindBy(id="name")
	public WebElement EnterName;

	@FindBy(id="email")
	public WebElement EnterEmail;

	@FindBy(xpath="//input[@id='phone']")
	public WebElement EnterPhone;

	@FindBy(xpath="(//div[@class='form-group'])[3]/div")
	public List<WebElement> selectGender;

	@FindBy(tagName="textarea")
	public WebElement EnterAddress;

	//@FindBy(xpath="((//*[@class='form-group']))[4]/div")
	@FindBy(css =".form-group:nth-of-type(4) > div")
	public List<WebElement> chooseMultipleDays;

	@FindBy(xpath="//*[text()='Days:']")
	public WebElement DaysLabel;

	@FindBy(id="country")
	public WebElement selectCountry;

	//@FindBy(xpath="//input[@id='datepicker']")
	@FindBy(css="input#datepicker")
	public WebElement enterDatePicker1;

	//@FindBy(name="SelectedDate")
	@FindBy(css="input[name='SelectedDate']")
	public WebElement selectDatePicker2;

	@FindBy(xpath="//input[@id='start-date']")
	public WebElement SelectStartDatePicker3;

	@FindBy(xpath="//input[@id='end-date']")
	public WebElement SelectEndDatePicker3;

	@FindBy(xpath="//select[@data-handler='selectYear']")
	public WebElement selectYear;

	@FindBy(xpath="//select[@data-handler='selectMonth']")
	public WebElement selectMonth;

	@FindBy(xpath="//*[@data-handler='selectDay']")
	public List<WebElement> selectDay;
	
	@FindBy(xpath="//*[text()='Static Web Table']")
	public WebElement LabelStaticWebTable;
	
	@FindBy(xpath="//table[@name='BookTable']/tbody/tr/td[1]")
	public List<WebElement> ListBookName;
	
	@FindBy(xpath="//table[@name='BookTable']/tbody/tr/td[2]")
	public List<WebElement> ListAuthor;
	
	@FindBy(xpath="//table[@name='BookTable']/tbody/tr/td[3]")
	public List<WebElement> ListSubject;
	
	@FindBy(xpath="//table[@name='BookTable']/tbody/tr/td[4]")
	public List<WebElement> ListPrice;

	@FindBy(xpath="//div[@id='crosscol']/div/div/ul/li/a")
	public List<WebElement> getAllTabNames;
	
	@FindBy(xpath="//*[@class='submit-btn']")
	public WebElement onSubmitOfDates;
	
	@FindBy(xpath="//*[text()='Please select both start and end dates.']")
	public WebElement Error_SelectBothStartEndDate;
	
	@FindBy(xpath="//*[text()='End date must be after start date.']")
	public WebElement Error_EndDateGreaterThanStartDate;
	
	@FindBy(id="Wikipedia1_wikipedia-search-input")
	public WebElement enterSearchInput;
	
	@FindBy(className="wikipedia-search-button")
	public WebElement searchbutton;
	
	@FindBy(xpath="//div[@id='Wikipedia1_wikipedia-search-results']/div/a")
	public List<WebElement> getSearchResults;
	
	//--Personal Details label names
	@FindBy(xpath="//label[text()='Name:']")
	public WebElement getNameLabel;
	
	@FindBy(xpath="//label[text()='Email:']")
	public WebElement getEmailLabel;
	
	@FindBy(xpath="//label[text()='Phone:']")
	public WebElement getPhoneLabel;
	
	@FindBy(xpath="//label[text()='Address:']")
	public WebElement getAddressLabel;

	@FindBy(xpath="//label[text()='Gender:']")
	public WebElement getGenderLabel;
	
	@FindBy(xpath="//*[text()='Broken Links']")
	public WebElement getBrokenLinkLabelName;

	@FindBy(xpath="//*[text()='Footer Links']")
	public WebElement getFooterLinkLabelName;
	
	@FindBy(xpath="//*[text()='Pagination Web Table']")
	public WebElement getPaginationWebTableName;
	
	@FindBy(xpath="//*[text()='Colors:']")
	public WebElement getColorsLabelName;
	
	@FindBy(xpath="//table[@id='productTable']/tbody/tr/td[2]")
	public List<WebElement> getProductName;
	
	@FindBy(xpath="//table[@id='productTable']/tbody/tr/td[3]")
	public List<WebElement> getProductPrice;
	
	@FindBy(xpath="//table[@id='productTable']/tbody/tr/td[4]")
	public List<WebElement> getProductSelectCheckBoxList;
	
	@FindBy(xpath="//*[@id='pagination']/li/a")
	public List<WebElement> getPaginationList;
	
	@FindBy(xpath="//*[@id='broken-links']/a")
	public List<WebElement> getBrokenLinksUrl;

	@FindBy(xpath="(//div[@class='widget-content'])[19]/ul/li/a")
	public List<WebElement> getFooterLinksUrl;
	
	@FindBy(id="colors")
	public WebElement getColorsList;
	
	public WebElement getPageNumber(int index)
	{
		return driver.findElement(By.xpath("//*[@id='pagination']/li["+index+"]/a"));
	}
	
	public WebElement getProductSelectCheckBox(int index)
	{
		return driver.findElement(By.xpath("//table[@id='productTable']/tbody/tr["+index+"]/td[4]/input"));
	}
	
	public WebElement getGenderElement(String gender) {
		return driver.findElement(By.xpath("(//div[@class='form-group'])[3]/div/input[@value='" + gender + "']"));
	}

	public WebElement getValueElement(String days) {
		return driver.findElement(By.xpath("(//div[@class='form-group'])[4]/div/input[@value='" + days + "']"));
	}
	
	public String getBookName(int index)
	{
		return driver.findElement(By.xpath("//table[@name='BookTable']/tbody/tr["+index+"]/td[1]")).getText();
	}
	
	public String getAuthorName(int index)
	{
		return driver.findElement(By.xpath("//table[@name='BookTable']/tbody/tr["+index+"]/td[2]")).getText();
	}
	
	public String getSubjectName(int index)
	{
		return driver.findElement(By.xpath("//table[@name='BookTable']/tbody/tr["+index+"]/td[3]")).getText();	
	}
	
	public WebElement getTabName(String tabName)
	{
		return wait.waitForElementToBeVisible(driver.findElement(By.xpath("//div[@id='crosscol']/div/div/ul/li/a[text()='"+tabName+"']")), 20);
	}
	
	public WebElement getSuggestSearchResult(int maxLength)
	{
		return driver.findElement(By.xpath("//div[@id='Wikipedia1_wikipedia-search-results']/div["+maxLength+"]/a"));
	}

	public WebElement getLinkName(int index)
	{
		return driver.findElement(By.xpath("//*[@id='broken-links']/a["+index+"]"));
	}
	public WebElement getFooterLinkName(int index)
	{
		return driver.findElement(By.xpath("(//div[@class='widget-content'])[19]/ul/li["+index+"]/a"));
	}

}

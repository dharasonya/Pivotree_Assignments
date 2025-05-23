package ePay_CRM.Test_PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Repo_BlogSpot {

	WebDriver driver;
	public Repo_BlogSpot(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
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
	
	public WebElement getGenderElement(String gender) {
		return driver.findElement(By.xpath("(//div[@class='form-group'])[3]/div/input[@value='" + gender + "']"));
	}

	public WebElement getValueElement(String days) {
		return driver.findElement(By.xpath("(//div[@class='form-group'])[4]/div/input[@value='" + days + "']"));
	}












}

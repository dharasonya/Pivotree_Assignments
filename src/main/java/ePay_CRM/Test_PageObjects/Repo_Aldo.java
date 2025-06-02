package ePay_CRM.Test_PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ePay_CRM.Reusable_Utils.WaitUtils;

public class Repo_Aldo {

	WebDriver driver;
	WaitUtils wait;
	public Repo_Aldo(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait=new WaitUtils(driver);
	}
	
	@FindBy(xpath="//div[@id='aldo_handbags']/div/div/div/a")
	public List<WebElement> getTotalListHandBags;
	
	@FindBy(xpath="//div[@id='aldo_handbags']/div[1]/div/div/a")
	public List<WebElement> getHangListBagsItem_Women;
	
	@FindBy(xpath="//div[@id='aldo_handbags']/div[2]/div/div/a")
	public List<WebElement> getHangListBagsItem_Collections;

	@FindBy(xpath="//div[@id='aldo_handbags']/div[1]/a")
	public WebElement getListHandBags_Category_Women;

	@FindBy(xpath="//div[@id='aldo_handbags']/div[2]/a")
	public WebElement getListHandBags_Category_Collections;

	@FindBy(xpath="//div[@class='nav']/nav/ul/li/a[@role='button']")
	public List<WebElement> getMenuList;
	
	@FindBy(xpath="//div[@id='aldo_handbags']/div/a")
	public List<WebElement> getCountOfSubMenuCategory;
	
	public WebElement getHangBagsItem_Women(int index)
	{
		return driver.findElement(By.xpath("//div[@id='aldo_handbags']/div[1]/div/div["+index+"]/a"));
	}
	
	public WebElement getHangBagsItem_Collection(int index)
	{
		return driver.findElement(By.xpath("//div[@id='aldo_handbags']/div[2]/div/div["+index+"]/a"));
	}
	
	public WebElement getHangBagsItem_SubCategory(int index)
	{
		return driver.findElement(By.xpath("//div[@id='aldo_handbags']/div["+index+"]/a"));
	}
	
	public WebElement getMenuName(int index)
	{
		return driver.findElement(By.xpath("//div[@class='nav']/nav/ul/li["+index+"]/a[@role='button']"));
	}
	
}

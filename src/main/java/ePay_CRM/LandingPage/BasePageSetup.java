
package ePay_CRM.LandingPage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import ePay_CRM.Reusable_Utils.CallListeners;
import ePay_CRM.Reusable_Utils.CommonLogger;
import ePay_CRM.Test_ActionMethods.Method_Aldo;
import ePay_CRM.Test_ActionMethods.Method_BlogSpot;



public class BasePageSetup {

	WebDriver driver;
	private int count=0;
	protected Properties prop=new Properties();
	protected CallListeners event;

	public CallListeners getEvent() {
		return event;
	}

	public void setEvent(CallListeners event) {
		this.event = event;
	}

	private final Logger log = Logger.getLogger(BasePageSetup.class.getName());
	InputStream input;


	public Logger getLog() {
		return log;
	}

	public WebDriver LoadConfigFile(String BrowserValue) throws Exception
	{
		setCount(count++);
		input=new FileInputStream("./src/main/resources/EnviornmentSetup.properties");
		prop.load(input);  

		switch(BrowserValue.toLowerCase())
		{
		case "chrome" :
		{	
			ChromeOptions options=new ChromeOptions();
			//options.addArguments("--headless");
			//options.addArguments("--disable-gpu"); // Optional: Use to disable GPU rendering in headless mode
			//options.addArguments("--window-size=1920,1080"); // Optional: Set window size

			//options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			
			setDriver(new ChromeDriver());	
			break;
		}

		case "firefox" :
		{	
			FirefoxOptions options=new FirefoxOptions();
			//WebDriverManager.firefoxdriver().clearDriverCache().setup();
			//WebDriverManager.firefoxdriver().clearResolutionCache().setup();
			//WebDriverManager.firefoxdriver().setup();
			//options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			//addArguments("incognito");
			//options.setAcceptInsecureCerts(true);
			setDriver(new FirefoxDriver());
			//event.setBrowservalue(BrowserValue);
			break;
		}
		case "edge" :
		{	  
			
			EdgeOptions options=new EdgeOptions();
			//options.addArguments("--headless");
			//options.addArguments("--disable-gpu"); // Optional: Use to disable GPU rendering in headless mode
			//options.addArguments("--window-size=1920,1080"); // Optional: Set window size

			//WebDriverManager.edgedriver().setup();
			//options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			//driver=new EdgeDriver();
			//setDriver(new EdgeDriver(options));
			setDriver(new EdgeDriver());
			
			System.out.println(" driver : "+getDriver());
			break;
		}
		default:
			throw new IllegalArgumentException("Unsupported Browser :- " + BrowserValue);
		}
		
		CommonLogger.log("Browser Launched :- "+BrowserValue);
	
		return getDriver();


	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public WebDriver getDriver() {

		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
		event=new CallListeners();
		event.setDriver(getDriver());  
		// Initialize Listeners with driver   
	}


	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

	@Parameters("BrowserValue")
	@BeforeMethod(alwaysRun=true)
	public void BrowserIntilization(String LaunchBrowserValue) throws Exception
	{
		CommonLogger.log("Browser Initilzation Started : "+LaunchBrowserValue);
		driver=LoadConfigFile(LaunchBrowserValue);
		event.setBrowservalue(LaunchBrowserValue);

		if (driver == null) {
			throw new IllegalStateException("Driver is not initialized.");
		}

	}

	public Method_BlogSpot Get_BlogSpot_Obj() throws Exception
	{
		Method_BlogSpot corestep=new Method_BlogSpot(driver);
		return corestep;  
	}
	public Method_Aldo Get_Aldo_Obj() throws Exception
	{
		Method_Aldo corestep=new Method_Aldo(driver);
		return corestep;  
	}

	
	@Parameters("BrowserValue")
	@AfterMethod(alwaysRun=true)
	public void TearDown(String BrowserValue)
	{
		if (driver != null) {
			
			CommonLogger.log("Closing Browser: " + BrowserValue);
			driver.quit();
			driver = null; // Clear driver reference
			
			CommonLogger.log("-------Execution Completed---");
		}
	}
	
	public void getUrl(String url)
	{
		driver.manage().window().maximize();
		try
		{
			driver.navigate().to(url);
			//driver.navigate().to(prop.getProperty("URL"));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));// newly added
			event.printSnap("Navigated to URL");
			CommonLogger.log(driver.getCurrentUrl());
		}
		catch(Exception e)
		{
			//getLog().info("Got RunTime Error-on Page Load, Retrying !!..");
			CommonLogger.log("Got RunTime Error-on Page Load, Retrying !!..");
			driver.navigate().refresh();
			//driver.navigate().to(prop.getProperty("URL"));
			driver.navigate().to(url);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));// newly added
			event.printSnap("Navigated to URL");
			CommonLogger.log(driver.getCurrentUrl());
		}
	}

	public void getUrl()
	{
		driver.manage().window().maximize();
		try
		{
			driver.navigate().to(prop.getProperty("URL"));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));// newly added
			event.printSnap("Navigated to URL");
			CommonLogger.log(driver.getCurrentUrl());
		}
		catch(Exception e)
		{
			//getLog().info("Got RunTime Error-on Page Load, Retrying !!..");
			CommonLogger.log("Got RunTime Error-on Page Load, Retrying !!..");
			driver.navigate().refresh();
			driver.navigate().to(prop.getProperty("URL"));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));// newly added
			event.printSnap("Navigated to URL");
			CommonLogger.log(driver.getCurrentUrl());
		}
	}


}

/*
 * FirefoxOptions options=new FirefoxOptions();
			WebDriverManager.firefoxdriver().clearDriverCache().setup();
			WebDriverManager.firefoxdriver().clearResolutionCache().setup();
			WebDriverManager.firefoxdriver().setup();
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver=new FirefoxDriver();*/

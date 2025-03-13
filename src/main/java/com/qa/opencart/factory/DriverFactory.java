package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exception.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverFactory 
{
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	private static final Logger LOG=Logger.getLogger(DriverFactory.class.getName());
	

	
	
	
	
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();
	
	
	public WebDriver initDriver(Properties prop)
	{
		String browserName=prop.getProperty("browser").toLowerCase();
		

		System.out.println("browser name is:"+browserName);
		LOG.info("browser name is :" +browserName);
		
		highlight=prop.getProperty("highlight").trim();
		optionsManager=new OptionsManager(prop);
		
		browserName = browserName.toLowerCase();
		if(browserName.equals("chrome"))
		{
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
				System.out.println("Remote is true and entering into docker space--------------------");
				init_remoteDriver(prop,"chrome");
			}
			else
			{
				//local run
				System.out.println("Remote is false and entering into local space--------------------");
				WebDriverManager.chromedriver().setup();
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
				
			}
			
		}
		else if(browserName.equals("firefox"))
		{
			if(Boolean.parseBoolean(prop.getProperty("firefox")))
			{
				init_remoteDriver(prop,"firefox");
			}
			else
			{
                //local run
				WebDriverManager.firefoxdriver().setup();
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
				
			}
			
		}
		else if(browserName.equals("edge"))
		{
			if(Boolean.parseBoolean(prop.getProperty("edge")))
			{
				init_remoteDriver(prop,"edge");
			}
			else
			{
                //local run
				WebDriverManager.firefoxdriver().setup();
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
				
			}
			
			
			
			tlDriver.set(new EdgeDriver());
		}
		else if(browserName.equals("safari"))
		{
			//only local execution is possible. safari is not supported by docker
			tlDriver.set(new SafariDriver());
		}
		else
		{
			System.out.println("Please pass the right browser name :"+ browserName);
			 LOG.info("Please pass the correct driver" + browserName);
			 throw new FrameworkException(AppError.BROWSER_NOT_FOUND);
	
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		
		return getDriver();		
		
	}
	
	
	// remote execution
	private void init_remoteDriver(String browser) 
	{
		
		System.out.println("Running test cases in remote machine.......with browser:" + browser);
		if(browser.equals("chrome"))
		{
			try {
				
				tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), optionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(browser.equals("firefox"))
		{
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getFirefoxOptions()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Please pass the right browser for remote execution..."+ browser);
		}
		
		
	}

	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	
	
	
	
	
	/*
	 * this method is used to initialize the driver in the basis of given browser name.
	 * This will return the driver instance
	 */
	
//	public WebDriver initDriver(Properties prop)
//	{
//		String browserName=prop.getProperty("browser").toLowerCase();
//		System.out.println("browser name is:"+browserName);
//		
//		browserName = browserName.toLowerCase();
//		if(browserName.equals("chrome"))
//		{
//			WebDriverManager.chromedriver().setup();
//			driver=new ChromeDriver();
//		}
//		else if(browserName.equals("firefox"))
//		{
//			WebDriverManager.firefoxdriver().setup();
//			driver=new FirefoxDriver();
//		}
//	
//		else if(browserName.equals("edge"))
//		{
//			driver=new EdgeDriver();
//		}
//		else if(browserName.equals("safari"))
//		{
//			driver=new SafariDriver();
//		}
//		else
//		{
//			System.out.println("Please pass the right browser name :"+ browserName);
//		}
//		
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get(prop.getProperty("url"));
//		
//		
//		return driver;		
//		
//	}
	/*
	 * this method is used to initialize the config properties.
	 */
	
	public Properties initprop()
	{
		Properties prop=new Properties();
		FileInputStream ip=null;
		
		String envName=System.getProperty("env");
		System.out.println("---------> Running Test cases on environment: ------->" + envName);
		
		if(envName==null)
		{
			System.out.println("No env is given..hence running it in QA env...");
			try {
				ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
			switch(envName)
			{
			case "qa":
				ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "stage":
				ip=new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "dev":
				ip=new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "uat":
				ip=new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
			case "prod":
				ip=new FileInputStream("./src/test/resources/config/config.properties");
				break;
				
			default:
				System.out.println("please pass the right env variable...."+ envName);
				throw new FrameworkException(AppError.ENV_NOT_FOUND);
			}
		}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
				
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return prop;
		
	}
	/**
	 * this method will take the screen shot
	 */
	public static String getScreenshot()
	{

		File srcFile=((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() +".png";
		File destination=new File(path);
		try 
		{
		FileUtils.copyFile(srcFile, destination);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return path;
		
		
	}
	
	
	
	
	
	
	
	
	

}

package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil 
{
	private WebDriver driver;
	private Select select;
	private Actions act;
	private JavaScriptUtil jsUtil;
	
	private static final String ELEMENT_NOT_FOUND_ERROR="element is not available on the page";
	
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
		act=new Actions(driver);
	   jsUtil=new JavaScriptUtil(driver);
	}
	
	public WebElement getElement(By locator)
	{
		WebElement ele= driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight))
		{
			jsUtil.flash(ele);
			return ele;
			
		}
		else
		{
			return ele;
		}

		
		
	}
	
	public  void doSendKeys(By locator,String value)
	{
		 WebElement ele=getElement(locator);
		 ele.clear();
		 ele.sendKeys(value);
		 
	}
	public  void doClick(By locator)
	{
		getElement(locator).click();
	}
	public String doGetText(By locator)
	{
		return getElement(locator).getText();
	}
	
	public String getAttribute(By locator,String attribute)
	{
		return getElement(locator).getAttribute(attribute);
	}
	public  boolean  doEleIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}
	public  List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
	public int getElementsCount(By locator)
	{
		return driver.findElements(locator).size();
	}
	public  ArrayList<String> getElementsTextList(By locator)
	{
		List<WebElement> eleList=getElements(locator);
		ArrayList<String> eleTextList=new ArrayList<String>();
		for(WebElement e:eleList)
		{
			String text=e.getText();
			if(text.length()!=0)
				{
				eleTextList.add(text);
				}
		}
		return eleTextList;
	}
	public  void doSelectDropDownByIndex(By locator,int index)
	{
		select=new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public  void doSelectDropDownByValue(By locator,String value)
	{
		select=new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public  void doSelectDropDownByVisibleText(By locator,String text)
	{
		select=new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	 public  void doSelectValueFromDropDOwn(By locator,String country) 
	 {
		 for(WebElement web:getElements(locator))
		  {
			 String ctry=web.getText();
			 System.out.println(ctry);
			 if(ctry.equals(country))
			 {
				 web.click();
				 break;
			  }
			}
	 }
	 public  void Search(By alllinks,String searchKey,By searchlocator, String value) throws InterruptedException
		{
			
			getElement(searchlocator).sendKeys(searchKey);
			Thread.sleep(3000);
			List<WebElement> autosugg=getElements(alllinks);
			for(WebElement aut:autosugg)
			{
				String auto=aut.getText();
				if(auto.equals(value))
				{
					System.out.println(auto);
				}
				
			}
			
			
		}
	 public void doSearch(String Tagname ,String text)
		{
			getElement(By.xpath("//"+Tagname+"[text()='"+text+"']")).click();
			
		}
	 
	 public  boolean isSingleElementPresent(By locator)                        
	 {                                                                               
	 	List<WebElement> list=getElements(locator);                                 
	 	System.out.println(list.size());                                            
	 	if(list.size()==1)                                                          
	 	{                                                                           
	 		System.out.println("Single search element is present on the page");     
	 		return true;                                                            
	 	}                                                                           
	 	else                                                                        
	 	{                                                                           
	 		System.out.println("no search or multiple search present on the page"); 
	 		return false;                                                           
	 	}                                                                           
	 } 
	 //******************************Actions Utils******************************************************
	 
	 public  void handleLevel1MenuItems(By ParentMenu,By ChildMenu) throws InterruptedException
		{
			
			WebElement content=getElement(ParentMenu);
			act.moveToElement(content).build().perform();
			Thread.sleep(3000);
			doClick(ChildMenu);
			 
		}
	 
	 public  void doActionsClick(By locator)
		{
		 
		 act.click(getElement(locator)).build().perform();
		}
		
		public void doActionsSendkeys(By locator,String str)
		{
			
			act.sendKeys(getElement(locator), str).build().perform();
		}
		
	
	//****************************************wait utils******************//
		
		public  WebElement waitForElementPresence(By locator,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		public void doSendKeysWithWait(By locator,int timeOut,String value)
		{
			waitForElementPresence(locator,timeOut).sendKeys(value);	
		}
		
		public void doClickWithWait(By locator,int timeOut)
		{
			waitForElementPresence(locator,timeOut).click();
		}
		
		public String getElementTextWithWait(By locator,int timeOut)
		{
			return waitForElementPresence(locator,timeOut).getText();
		}
		
		public  WebElement waitForElementVisible(By locator,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		public  List<WebElement> waitForElementsToBeVisible(By locator,int timeOut)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		
		public void doSendKeysWithVisibleElement(By locator,int timeOut,String value)
		{
			waitForElementVisible(locator,timeOut).sendKeys(value);	
		}
		
		public void doClickWihVisibleElement(By locator,int timeOut)
		{
			waitForElementVisible(locator,timeOut).click();
		}
		
		public String getElementTextWithVisibleElement(By locator,int timeOut)
		{
			return waitForElementVisible(locator,timeOut).getText();
		}
		
		public void clickWhenReady(By locator,int timeOut)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
			
		}
		
		
		public  Alert waitForAlert(int timeOut)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.alertIsPresent());
		}
		public  String getAlertText(int timeOut)
		{
			return waitForAlert(timeOut).getText();
		}
		public  void acceptAlert(int timeOut)
		{
			waitForAlert(timeOut).accept();
		}
		public  void dismissAlert(int timeOut)
		{
			waitForAlert(timeOut).dismiss();
		}
		public  void alertSendkeys(int timeOut,String value)
		{
			waitForAlert(timeOut).sendKeys(value);
		}
		
		public  String waitForTitleContains(int timeOut,String titleFraction)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			if(wait.until(ExpectedConditions.titleContains(titleFraction)))
				return driver.getTitle();
			else
			{
				return null;
			}
		}
		
		public  String waitForTitleIs(int timeOut,String titleValue)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			if(wait.until(ExpectedConditions.titleIs(titleValue)))
				return driver.getTitle();
			else
			{
				return null;
			}
		}
		
		public  String waitForUrlContains(int timeOut,String urlFraction)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			if(wait.until(ExpectedConditions.urlContains(urlFraction)))
			{
				return driver.getCurrentUrl();
			}
			else
			{
				return null;
			}
			
		}
		
		public  String waitForUrlIs(int timeOut,String urlvalue)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			if(wait.until(ExpectedConditions.urlToBe(urlvalue)))
			{
				return driver.getCurrentUrl();
			}
			else
			{
				return null;
			}
			
		}
		
		public  void waitForFrame(int timeOut,int frameIndex)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
			
		}
		
		public  void waitForFrame(int timeOut,String nameOrId)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
			
		}
		public  void waitForFrame(int timeOut,WebElement FrameElement)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameElement));
			
		}
		public  void waitForFrame(int timeOut,By locator)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
			
		}
		
		public  WebElement waitForElementToBeVisibleWithFluentWait(By locator,int timeOut,int poolingTime)
	    {
	    	Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
	                .withTimeout(Duration.ofSeconds(timeOut))
	                .pollingEvery(Duration.ofSeconds(poolingTime))
	                .ignoring(NoSuchElementException.class)
	                .withMessage(ElementUtil.ELEMENT_NOT_FOUND_ERROR + locator);
	    	
	    	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    	
	    }



}

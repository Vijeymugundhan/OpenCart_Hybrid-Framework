package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage 
{
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. By Locators
	
	private By logoutlink=By.linkText("Logout");
	private By search=By.name("search");
	private By SearchIcon=By.cssSelector("button.btn-default");
	private By accSecHeaders=By.cssSelector("div#content h2");
	
	//2.Constructor
	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	
	//3.Page Actions
	
	public String getAccPageTitle()
	{
		String title=eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT,AppConstants.ACC_PAGE_TITLE);
		System.out.println("Acc page title :"+title);
		return title;
		
		
		
		
//		String title=driver.getTitle();
//		System.out.println("Acc page title :"+title);
//		return title;
	}
	
	public boolean getLoginPageUrl()
	{
		String url= eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACC_PAGE_URL_PARAM);
		System.out.println("login page url:"+url);
		if(url.contains(AppConstants.ACC_PAGE_URL_PARAM))
		{
			return true;
		}
		else
			return false;
		
		
		
		
//		String url=driver.getCurrentUrl();
//		System.out.println("login page url :"+ url);
//		if(url.contains(AppConstants.ACC_PAGE_URL_PARAM))
//			return true;
//		else
//			return false;
		
	}
	
	public boolean isLogoutLinkExists()
	{
		return eleUtil.doEleIsDisplayed(logoutlink);
		
		//return driver.findElement(logoutlink).isDisplayed();
	}
	
	public boolean isSearchExists()
	{
		return eleUtil.doEleIsDisplayed(search);
		
		//return driver.findElement(search).isDisplayed();
	}
	
	public SearchResultsPage performSearch(String productKey)
	{
		System.out.println("Product Name is "+productKey);
		if(isSearchExists())
		{
			eleUtil.doSendKeys(search, productKey);// always before entering a value in the search field.Clear the values.
			eleUtil.doClick(SearchIcon);
			
			return new SearchResultsPage(driver);
			
		}
		else
		{
			System.out.println("search field is not present on the page..");
			return null;
		}
		
	}
	
	
	public ArrayList<String> getAccSecHeadersList()
	{
		List<WebElement> secList=eleUtil.getElements(accSecHeaders);
		System.out.println("total sections headers: "+ secList.size());
		ArrayList<String> actSecTextList=new ArrayList<String>();
		for(WebElement e: secList)
		{
			String text=e.getText();
			actSecTextList.add(text);
		}
		
		return actSecTextList;
		
		
		
//		List<WebElement> secList=driver.findElements(accSecHeaders);
//		System.out.println("total sections headers: "+ secList.size());
//		ArrayList<String> actSecTextList=new ArrayList<String>();
//		for(WebElement e: secList)
//		{
//			String text=e.getText();
//			actSecTextList.add(text);
//		}
//		
//		return actSecTextList;
	}
	

}

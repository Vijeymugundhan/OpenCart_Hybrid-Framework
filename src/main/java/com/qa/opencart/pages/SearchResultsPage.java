package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage 
{
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productSearchLayout=By.cssSelector("div.product-layout");
	
	
	public SearchResultsPage(WebDriver driver) 
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public boolean isSearchSucessful()
	{
		List<WebElement> searchList=eleUtil.waitForElementsToBeVisible(productSearchLayout, AppConstants.DEFAULT_LARGE_TIME_OUT);
		if(searchList.size()>0)
		{
			System.out.println("Search is sucessfully done...");
			return true;
		}
		return false;
	}
	
	public ProductInfoPage selectProduct(String mainProductName)
	{
		By mainPrName= By.xpath("//img[@title='"+mainProductName+"']");
		eleUtil.doClick(mainPrName);
		return new ProductInfoPage(driver);
		
	}

}

package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage 
{
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> productInfoMap;

	
	private By productImages=By.cssSelector("ul.thumbnails img");
	private By productMetaData=By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData=By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[2]/li");
	
	public ProductInfoPage(WebDriver driver) 
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getProductHeader(String mainProductName)
	{
		String xpath="//h1[text()='"+mainProductName+"']";
		String productHeader=eleUtil.doGetText(By.xpath(xpath));
		System.out.println("product header is :"+ productHeader);
		return productHeader;
	}
	
	public int getProductImagesCount()
	{
		return eleUtil.waitForElementsToBeVisible(productImages, AppConstants.DEFAULT_LARGE_TIME_OUT).size();
	}
	
	public String getProductPageTitle(String productTitle)
	{
		return eleUtil.waitForTitleIs(AppConstants.DEFAULT_LARGE_TIME_OUT,productTitle);
	}
	
	public String getProductPageURL(String searchKey)
	{
		return eleUtil.waitForUrlContains(AppConstants.DEFAULT_LARGE_TIME_OUT, searchKey);
	}
	
	public Map<String,String> getProductMetaData()
	{
		List<WebElement> metaList=eleUtil.getElements(productMetaData);
		productInfoMap=new HashMap<String, String>();
		for(WebElement e:metaList)
		{
			String metaText=e.getText();
			String[] meta=metaText.split(":");
			String metaKey=meta[0].trim();
			String metaValue=meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		productInfoMap.forEach((k,v)-> System.out.println(k + ":" + v));
		
		return productInfoMap;
	}
	
	public String getProductMetaPrice()
	{
		String price=eleUtil.getElement(productPriceData).getText();
		System.out.println(price);
		
		
		return price;
	}
	
	
	
	
	
	
	
}

	

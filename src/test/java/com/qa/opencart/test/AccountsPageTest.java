package com.qa.opencart.test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest 
{
	@BeforeClass
	public void accSetup()
	{
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority =1 )
	public void accPageTitleTest()
	{
		String actAccPageTitle=accPage.getAccPageTitle();
		System.out.println("The Title of the page is :"+ actAccPageTitle);
		Assert.assertEquals(actAccPageTitle,AppConstants.ACC_PAGE_TITLE);
	}
	
	@Test(priority =2 )
	public void accPageUrlTest()
	{
		Assert.assertTrue(accPage.getLoginPageUrl());
	}
	
	@Test(priority =3 )
	public void searchExistTest()
	{
		Assert.assertTrue(accPage.isSearchExists());
	}
	
	@Test(priority =4 )
	public void logoutLinkExistTest()
	{
		Assert.assertTrue(accPage.isLogoutLinkExists());
	}
	
	@Test(priority =5 )
	public void accHeadersTest()
	{
		ArrayList<String> actHeadersList=accPage.getAccSecHeadersList();
		System.out.println("Actual AccPage Headers: "+actHeadersList);
		Assert.assertEquals(actHeadersList,AppConstants.ACC_PAGE_SECTION_HEADERS);
	}
	
	@Test(priority=6)
	public void searchCheckTest()
	{
		searchResultsPage=accPage.performSearch("Macbook");
		Assert.assertTrue(searchResultsPage.isSearchSucessful());
	}
	
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][]
				{
					{"Macbook","MacBook Pro"},
					{"Macbook","MacBook Air"},
					{"iMac","iMac"},
					{"Samsung","Samsung SyncMaster 941BW"},
					{"Samsung","Samsung Galaxy Tab 10.1"},
				};
		
	}
	
	
	
	@Test(dataProvider ="getProductData",priority = 7)
	public void searchTest(String searchKey, String mainProductName)
	{
		searchResultsPage=accPage.performSearch(searchKey);
		if(searchResultsPage.isSearchSucessful())
		{
			productInfoPage=searchResultsPage.selectProduct(mainProductName);
			String actualProductHeader=productInfoPage.getProductHeader(mainProductName);
			Assert.assertEquals(actualProductHeader, mainProductName);
			
		}
		
		
	}
	
	

}

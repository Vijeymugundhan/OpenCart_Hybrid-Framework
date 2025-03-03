package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest
{
	@Description("login page title test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority =1)
	public void LoginPageTitleTest()
	{
		String actualTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle,AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Description("login page url test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageUrlTest()
	{
		Assert.assertTrue(loginPage.getLoginPageURL());
	}
	
	@Description("login page forgot password test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void IsForgotPwdLinkExistTest()
	{
		Assert.assertTrue(loginPage.isforgotPwdLinkExist());
	}
	
	@Description("login page to login with login and password test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void loginTest()
	{
		accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExists());
	}
	

}

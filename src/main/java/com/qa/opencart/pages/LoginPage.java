package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage 
{
	private WebDriver driver;
	private ElementUtil eleUtil;
	

	
	//1.By locator
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By logoImage=By.cssSelector("img[title='naveenopencart']");
	private By forgotPwdLink=By.linkText("Forgotten Password");
	private By registerLink=By.linkText("Register");
	
	
	//2.Page Constructor
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	
	//3.Page Actions
	
	public String getLoginPageTitle()
	{
		String title=eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT,AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("login page title :"+title);

		return title;
		
		
//		String title1=driver.getTitle();
//		System.out.println("login page title :"+title1);
//		return title1;
	}
	
	public boolean getLoginPageURL()
	{
		String url= eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARAM);
		System.out.println("login page url:"+url);
		if(url.contains(AppConstants.LOGIN_PAGE_URL_PARAM))
		{
			return true;
		}
		else
			return false;
		
		
		
		
		
		
//		String url= driver.getCurrentUrl();
//		System.out.println("login page url:"+url);
//		if(url.contains(AppConstants.LOGIN_PAGE_URL_PARAM))
//		{
//			return true;
//		}
//		else
//			return false;
		
	}
	
	public boolean isforgotPwdLinkExist()
	{
		return eleUtil.doEleIsDisplayed(forgotPwdLink);
		
		//return driver.findElement(forgotPwdLink).isDisplayed();
		
	}
	
	public AccountsPage doLogin(String username, String pwd)
	{
		System.out.println("user cred are:" + username + " : "+pwd);
		eleUtil.doSendKeysWithWait(emailId,AppConstants.DEFAULT_LARGE_TIME_OUT , username);
		eleUtil.doSendKeysWithWait(password,AppConstants.DEFAULT_LARGE_TIME_OUT , pwd);
		eleUtil.doClick(loginBtn);		
		
		
		
//		System.out.println("user cred are:" + username + " : "+pwd);
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		
		return new AccountsPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClickWihVisibleElement(registerLink, 10);
		return new RegisterPage(driver);
	}
	
	
	

}

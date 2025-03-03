package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterTest extends BaseTest
{
	@BeforeClass
	public void regSetup()
	{
		registerPage=loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegTestData()
	{
		Object regData[][]=ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider ="getRegTestData" )
	public void regiaterUserTest(String firstName,String lastName,String email,String telephone,String password,String subscribe)
	{
		String actSuccMessg=registerPage.userRegister(firstName,lastName,email,telephone,password,subscribe );
		Assert.assertEquals(actSuccMessg,AppConstants.ACC_CREATE_SUCC_MESSG );
	}
	

}

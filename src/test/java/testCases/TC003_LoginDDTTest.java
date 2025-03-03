package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDTTest extends BaseClass 
{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")
	public void verify_loginDDt(String email,String pwd,String exp) 
	{
		   logger.info("**********Starting  TC003_verify_loginDDt********************");
try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		LoginPage lp=new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterpswd(pwd);
		lp.btnlogin();
		 MyAccountPage ap=new MyAccountPage(driver);
		boolean targetpage=ap.isMyAccountPageExists();
		if (exp.equalsIgnoreCase("Valid"))
		{
			if (targetpage==true) 
			{
				ap.clickLogOut();
				Assert.assertTrue(true);
				
			}
			else{
				Assert.assertTrue(false);
			}
		} 
		if(exp.equalsIgnoreCase("Invalid")) {
			ap.clickLogOut();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		
}       catch(Exception e) 
{
	     Assert.fail();
}
	        
	        logger.info("**********Finised  TC003_verify_loginDDt********************");
	
	}

	}

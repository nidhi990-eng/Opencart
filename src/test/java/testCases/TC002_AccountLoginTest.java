package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_AccountLoginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void AccountLoginTest() {
	     logger.info("**********Starting  TC002_AccountLoginTest********************");
	     try {
			
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			LoginPage lp=new LoginPage(driver);
			lp.enterEmail(p.getProperty("email"));
			lp.enterpswd(p.getProperty("password"));
			lp.btnlogin();
	        MyAccountPage ap=new MyAccountPage(driver);
	        boolean targetpage=ap.isMyAccountPageExists();
	        Assert.assertEquals(targetpage, true,"login failed");
	      }
	      catch(Exception e) {
	    	  Assert.fail();
	      }
	        
	        logger.info("**********Finised  TC002_AccountLoginTest********************");
			
		

	}
}
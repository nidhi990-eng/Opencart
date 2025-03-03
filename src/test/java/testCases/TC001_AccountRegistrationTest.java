package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
	
		logger.info("**********Starting  TC001_AccountRegistrationTest********************");
		try {
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on MyACC link");
		hp.clickRegister();
		logger.info("clicked on Register link");
		AccountRegistrationPage ag=new AccountRegistrationPage(driver);
		logger.info("Providing customer details");
		ag.setFirstname(randomString().toUpperCase());
		ag.setLastname(randomString().toUpperCase());
		ag.setEmail(randomString()+"@gmail.com");
		ag.setTelephone(randomNumber());
		String password =randomalphanumberic();
		ag.setPassword(password);
		ag.setConfirmPassword(password);
		ag.setcheckedpolicy();
		ag.setContinue();
		Thread.sleep(3000);
		logger.info("Validating expected message");
		String confmsg=ag.getConfirmationMsg();
		if (confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
			
		}
		else {
			logger.error("test failed...");
		    logger.debug("Debug Logs");
			Assert.assertTrue(false);
			
		}
		Thread.sleep(3000);
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");	
		
		}
		catch(Exception e) {
		
			Assert.fail();
		}
		logger.info("*******Finished TC001_AccountRegistrationTest***********");
	
	}


	
}

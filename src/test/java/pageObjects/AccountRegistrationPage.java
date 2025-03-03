package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	
	public AccountRegistrationPage(WebDriver driver) {
		
		super(driver);
	}
	

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	@FindBy(xpath="//input[@id='input-lastname']") 
	WebElement txtLastname;
	@FindBy(xpath="//input[@id='input-email']") 
	WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']") 
	WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") 
	WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']") 
	WebElement txtConfirmPassword;
	@FindBy(xpath="//input[@name='agree']") 
	WebElement chkddpolicy;
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") 
	WebElement msgConfirmation;

	
	public void setFirstname(String fname) {
		txtFirstname.sendKeys(fname);
	}
	public void setLastname(String Lname) {
		txtLastname.sendKeys(Lname);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void setConfirmPassword(String confirmPassword) {
		txtConfirmPassword.sendKeys(confirmPassword);
	}
	
	public void setcheckedpolicy() {
		chkddpolicy.click();
	}
	public void setContinue() {
		btnContinue.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		}catch(Exception e) {
			return(e.getMessage());
		}
	}
}

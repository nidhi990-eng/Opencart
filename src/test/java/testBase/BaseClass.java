package testBase;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;   //log4j
	public Properties p;
	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups={"Regression","Master","Sanity"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException 
	{
		//loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac")){
				capabilities.setPlatform(Platform.MAC);	
			}else {
				System.out.println("No Matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) 
			{
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge");break;
			default :System.out.println("Invalid browser name..."); return;
			}
			driver =new RemoteWebDriver(new URL(" http://192.168.1.33:4444/wd/hub"),capabilities);
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		
		switch(br.toLowerCase()) 
		{
		case "chrome":driver=new ChromeDriver();break;
		case "edge" : driver=new EdgeDriver();break;
		case "firefox" : driver=new FirefoxDriver();break;
		default :System.out.println("Invalid browser name..."); return;
		}
		}
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appURL")); //reading url from properties file
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@AfterClass (groups={"Regression","Master","Sanity"})
	public void teardown() {
    	driver.close();
    }
	public String randomString() {
		
		@SuppressWarnings("deprecation")
		String generatestring=RandomStringUtils.randomAlphabetic(5);
		return generatestring;
		
	}
	
	public String randomNumber() {
		@SuppressWarnings("deprecation")
		String generaterandomNumber=RandomStringUtils.randomNumeric(10);
		return generaterandomNumber;
	}
	
	public String randomalphanumberic() {
		@SuppressWarnings("deprecation")
		String generatestring=RandomStringUtils.randomAlphabetic(2);
		@SuppressWarnings("deprecation")
		String generaterandomNumber=RandomStringUtils.randomNumeric(2);
		return (generatestring+"@"+generaterandomNumber);
		
	}
	public String captureScreen(String tname) throws IOException {
		
		String timeStamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		  File screenshotDir = new File(System.getProperty("user.dir") + "\\screenshots\\");
		    if (!screenshotDir.exists()) {
		        screenshotDir.mkdir();
		    }
		
		TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" +tname +"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		System.out.println(targetFile);
		return targetFilePath;
	
	}
	
}

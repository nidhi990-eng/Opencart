package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);*/
		
		System.out.println("onStart..............");
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName ="Test-Report"+ timeStamp +".html";
		
		
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
		
		sparkReporter.config().setDocumentTitle("opencard Automation Reopert");
		sparkReporter.config().setReportName("opencart Functional Report");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operationg System", os);
		
	    String browser=testContext.getCurrentXmlTest().getParameter("browser");
	    extent.setSystemInfo("Browser", browser);
	    
	    List<String> includedGroups =testContext.getCurrentXmlTest().getIncludedGroups();
	    if(!includedGroups.isEmpty()) {
	    	extent.setSystemInfo("Groups",includedGroups.toString());
	    }  	
	    System.out.println("onStart..............");
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+"got successfully executed");
		
		System.out.println("onTestSuccess..............");
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		  
		System.out.println("onTestFailure..............");
		  
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+"got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());	
		
	try{
		  String imgPath= new BaseClass().captureScreen(result.getName());
		  System.out.println("path of the imgpath "+imgPath); 
		  test.addScreenCaptureFromPath(imgPath,result.getName()+"screenshots");
		  }catch (IOException e1) {
			  e1.printStackTrace(); 
		  }
		System.out.println("onTestFailure..............");
	}
        public void onTestSkipped(ITestResult result) {
        	
        	System.out.println("onTestSkipped..............");
        	
        	test=extent.createTest(result.getClass().getName());
        	test.assignCategory(result.getMethod().getGroups());
        	test.log(Status.SKIP, result.getName()+"got skipped");
        	 test.log(Status.INFO, result.getThrowable().getMessage());
   
        	
        	System.out.println("onTestSkipped..............");
        }
      public void onFinish(ITestContext testContext) {
    	  
    	  System.out.println("onFinish.............."); 
    	  
    	  extent.flush();
    	  System.out.println("onFinish..............");
    	  String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
    	  System.out.println("path of the ss "+pathOfExtentReport);
    	  
    	  File extentReport=new File(pathOfExtentReport);
    	  if (Desktop.isDesktopSupported()) {
    	  try {
    		  Desktop.getDesktop().browse(extentReport.toURI());
    	  }catch(IOException e) {
    		  e.printStackTrace();
    	  }
    	  } else {
    	  System.out.println("Desktop is not supported, cannot open the report."); 
    	  }
      }  
        
      
}

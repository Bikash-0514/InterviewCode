package com.Test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListnerClass implements ITestListener{
	
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	public void ConfigureReport() {
	htmlReporter = new ExtentSparkReporter("ExtentListenerReportCreate.html");
	reports = new ExtentReports();
	reports.attachReporter(htmlReporter);
	
	reports.setSystemInfo("Machine", "lap1");
	reports.setSystemInfo("os", "window");
	reports.setSystemInfo("browser", "chrome");
	reports.setSystemInfo("user name", "Bikash");
	
	htmlReporter.config().setDocumentTitle("");
	htmlReporter.config().setReportName("");
	htmlReporter.config().setTheme(Theme.DARK);
			
		
		
	}
	
	
	
	public void onStart (ITestContext Result) {
		ConfigureReport();
		System.out.println("On Start method Invoked");
	}
	public void onFinish(ITestContext Tesult) {
		System.out.println("On Finish method Invoked");
		reports.flush();
	}
	
	
	//failed method called
	public void onTestFailure (ITestResult Result) {
		System.out.println("Name of failed method:"+ Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case:"+Result.getName(), ExtentColor.RED));
	}
	
	public void onTestSkipped(ITestResult Result) {
		System.out.println("Name of test method skipped:"+ Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the Skipped test case:"+Result.getName(), ExtentColor.ORANGE));
	}
	public void onTestStart(ITestResult Result) {
		System.out.println("Name of test method started:"+Result.getName());
	}
	public void onTestSuccess(ITestResult Result) {
		System.out.println("Test method sucessfully excuted:"+ Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the success test case:"+Result.getName(), ExtentColor.GREEN));
	}
	
public void onTestFailedWithinSuccessPercentage(ITestResult Result) {
	
	
}


}

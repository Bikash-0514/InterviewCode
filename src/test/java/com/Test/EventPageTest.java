package com.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

 public class EventPageTest {
	
	WebDriver driver;
	
	private ExtentReports extent;
    private ExtentTest test;
   
	
   @BeforeMethod
public void SetUp() {
System.setProperty("webdriver.edge.driver", "C:\\Users\\bikash\\Downloads\\edgedriver_win64\\msedgedriver.exe");
 driver = new EdgeDriver();
 driver.manage().window().maximize();
 driver.manage().deleteAllCookies();
 driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
 driver.get("https://dsa79.eventtitans.com/t/DSA79A");
 driver.findElement(By.id("login123")).click();
 driver.findElement(By.name("lemail")).sendKeys("bhargavi@clidiem.com");
 driver.findElement(By.name("lpassword")).sendKeys("Test@12345");
 driver.findElement(By.xpath("//button[contains(text(),' Login')]")).click();
 }
   
   
   
 
   
   
   
   
   
   
@Test(priority = 1)
public void TextInfoVerification() {
    String text1 = "DSA 79 A - All Joined & Virtual Only";
    String text2 = driver.findElement(By.id("h1TopicTitle")).getText();
    if (text1.equals(text2)) {
        System.out.println("Title Verification Passed");
    } else {
        System.out.println("Title Verification Failed");
    }
    String text3 = driver.findElement(By.tagName("body")).getText();
	System.out.println(text3);
    
    if (text3.contains("Expected Text in Body")) {
        System.out.println("Body Text Verification Passed");
    } else {
        System.out.println("Body Text Verification Failed");
    }
}


    @Test (priority = 2)
    
    public void TestVideoPlayback() throws InterruptedException {
    	
        WebElement joinUsButton = driver.findElement(By.xpath("//*[@id=\"WeWillBeLive\"]/div/div/span")); 
        joinUsButton.click();

         WebElement videoElement = driver.findElement(By.tagName("video")); 

        boolean isVideoPlaying = (Boolean) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return arguments[0].paused === false;", videoElement);

        if (isVideoPlaying) {
            System.out.println("Video is playing.");
            WebElement otherElement = driver.findElement(By.className("ytp-title-text"));
            String otherElementText = otherElement.getText();
        } else {
            System.out.println("Video playback failed.");
        }
    } 
    
    
    

   
   @Test (priority = 4)
public void validateClocker() {
	   String[] clockerSelectors = {
        "//h1[@class='allhed']/following-sibling::ul//li[1]",
        "//h1[@class='allhed']/following-sibling::ul//li[2]",
        "//h1[@class='allhed']/following-sibling::ul//li[3]",
        "//h1[@class='allhed']/following-sibling::ul//li[4]"
    };
    
    List<WebElement> clockerElements = new ArrayList<>();

    int clockerDays = Integer.parseInt(clockerElements.get(0).getText());
    int clockerHours = Integer.parseInt(clockerElements.get(1).getText());
    int clockerMinutes = Integer.parseInt(clockerElements.get(2).getText());
    int clockerSeconds = Integer.parseInt(clockerElements.get(3).getText());

    WebElement eventDateElement = driver.findElement(By.id("TopicdateId"));
    WebElement eventTimeElement = driver.findElement(By.id("TopicTimeId"));


    String eventDateString = eventDateElement.getText();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime eventDate = LocalDateTime.parse(eventDateString, formatter);

    LocalDateTime currentDateTime = LocalDateTime.now();

    Duration timeRemaining = Duration.between(currentDateTime, eventDate);

    long remainingDays = timeRemaining.toDays();
    long remainingHours = timeRemaining.toHours() % 24;
    long remainingMinutes = timeRemaining.toMinutes() % 60;
    long remainingSeconds = timeRemaining.getSeconds() % 60;

    if (remainingDays == clockerDays && remainingHours == clockerHours &&
        remainingMinutes == clockerMinutes && remainingSeconds == clockerSeconds) {
        System.out.println("Clocker details match the time remaining until the event.");
    } else {
        System.out.println("Clocker details do not match the time remaining until the event.");
    }
}
   
   @Test(priority = 3)
   public void DownloadFile() {
	   WebElement downloadButton = driver.findElement(By.xpath("//button[contains(text(), 'Download')]"));
       downloadButton.click();
if(driver.getCurrentUrl().contains("https://etpub.s3.amazonaws.com/Events/Files")) {
System.out.println("pdf sucessfully opened");
}

       
       File downloadedFile = new File(downloadButton + "https://etpub.s3.amazonaws.com/Events/Files");
       if (downloadedFile.exists()) {
           System.out.println("File downloaded successfully.");
       } else {
           System.out.println("File download failed.");
       }
   }
   
 
   
   
   
    
    

@AfterMethod
public void TearDown() {
	driver.quit();
	extent.flush();
	
	}

}
 
 




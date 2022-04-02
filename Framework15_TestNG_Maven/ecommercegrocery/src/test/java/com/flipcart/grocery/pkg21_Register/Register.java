package com.flipcart.grocery.pkg21_Register;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Register {
    public static WebDriver driver    = null;
    private static final Logger myLog = Logger.getLogger(Register.class);

    @BeforeClass
    public void beforeClass() throws Exception {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
        driver = invokeBrowser();
    }

    @Test
    public void Register1(){
        myLog.info("Register1--"+Thread.currentThread().getId());
        driver.get("http://demo.automationtesting.in/Register.html");
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
    }

    @Test
    @Parameters({"myname","myname2","address","email","phone"})
    public void Register2(String myname, String myname2, String address, String email, String phone) {
        myLog.info("Register1--"+Thread.currentThread().getId());
        driver.findElement(By.cssSelector("input[placeholder='First Name']")).sendKeys(myname);
        driver.findElement(By.cssSelector("input[placeholder='Last Name']")).sendKeys(myname2);
        driver.findElement(By.cssSelector("textarea[ng-model='Adress']")).sendKeys(address);
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys(phone);
        driver.findElement(By.cssSelector("input[value='Male']")).click();
        driver.findElement(By.id("checkbox1")).click();
        driver.findElement(By.id("submitbtn")).click();
        try{Thread.sleep(2000);}catch (Exception t){}
    }

    @BeforeMethod
    public void beforeMethod() {}

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()){
            System.setProperty("org.uncommons.reportng.escape-output", "false");
            try{
                String sDDMMYY = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                File src       = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotName = "screenshot_"+sDDMMYY+".jpg";
                String dest    = System.getProperty("user.dir") + "\\test-output\\html\\" + screenshotName;
                File finalDestination = new File(dest);

                try {
                    FileUtils.copyFile(src, finalDestination);
                    Reporter.log("<br>  <img src='"+ screenshotName +"' height='100' width='100' /><br>");
                    Reporter.log("<a href="+ screenshotName +"></a>");

                }catch (IOException eScreenshot) {
                    eScreenshot.printStackTrace();
                }
            }catch(Exception ePic){
                ePic.printStackTrace();
            }
        }
    }

    @AfterClass
    public void afterClass() throws Exception {
        driver.quit();
    }

    // Launch browser instance -- Currently only Chrome is supported
    // Note by default image loading is disabled to speedup the operation
    public WebDriver invokeBrowser(){
        String sChromeBinary=System.getProperty("user.dir") + "\\src\\test\\resources\\browserDriver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", sChromeBinary);
        System.setProperty("webdriver.chrome.silentOutput", "true");

        // Disable image loading - to speedup test execution
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.managed_default_content_settings.images", 2);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1400,800");
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.setExperimentalOption("useAutomationExtension", false);
        //options.setExperimentalOption("prefs", prefs);
        return new ChromeDriver(options);
    }



}
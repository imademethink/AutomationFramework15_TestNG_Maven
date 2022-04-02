package com.flipcart.grocery.pkg1_Login;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Login {
    public static WebDriver driver = null;

    @Test
    public void Login1(){
        System.out.println("Login1--"+Thread.currentThread().getId());
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
        driver = invokeBrowser();
        driver.get("https://chandanachaitanya.github.io/selenium-practice-site/");
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void Login2(){
        System.out.println("Login2--"+Thread.currentThread().getId());
        driver.findElement(By.id("bicycle-checkbox")).click();
        driver.findElement(By.id("hatchback-checkbox")).click();
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void Login3(){
        System.out.println("Login3--"+Thread.currentThread().getId());
        //Assert.fail("Failing somehow");
    }

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
        options.setExperimentalOption("prefs", prefs);
        return new ChromeDriver(options);
    }


}

//  POM.xml Maven dependencies
//  Maven plugin - Compiler plugin, Surefire plugin
//  Surefire plugin - running multiple testng xml files
//  Add your multiple sample class with proper package structure in xml file
//  Annotations
//      - BeforeClass/AfterClass, BeforeMethod/AfterMethod  should be in the same class where @Test is used
//      - BeforeSuite/AfterSuite, BeforeTest/AfterTest, BeforeMethod/AfterMethod declaration could be in other class
//  Failed test execution
//      - Using org.testng.IRetryAnalyzer and AnnotationTransformer
//  Parameter passing, Dataprovider usage from testng xml
//  Running classes in parallel
//  Logging using log4j
//  ReportNG HTML reporting
//  Using default listeners from pom.xml file
//  Running project using Maven commands -  mvn clean test


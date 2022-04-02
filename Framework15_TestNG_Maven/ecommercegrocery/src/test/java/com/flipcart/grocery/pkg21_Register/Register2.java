package com.flipcart.grocery.pkg21_Register;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Register2 {
    public static WebDriver driver = null;
    private static final Logger myLog = Logger.getLogger(Register2.class);
    public String myname ="";
    public String myname2="";
    public String address="";
    public String email  ="";
    public String phone  ="";

    @BeforeClass
    public void beforeClass() throws Exception {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
        driver = invokeBrowser();
    }

    @AfterClass
    public void afterClass() throws Exception {
        driver.quit();
    }

    @Test(dataProvider="EmpDataProviderExcel")
    public void EmpDataProcessingTest(String myname, String myname2, String address, String email, String phone) {
        this.myname =myname;
        this.myname2=myname2;
        this.address=address;
        this.email  =email;
        this.phone  =phone;
        myLog.info(this.myname);
        myLog.info(this.myname2);
        myLog.info(this.address);
        myLog.info(this.email);
        myLog.info(this.phone);

        driver.get("http://demo.automationtesting.in/Register.html");
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.findElement(By.cssSelector("input[placeholder='First Name']")).sendKeys(myname);
        driver.findElement(By.cssSelector("input[placeholder='Last Name']")).sendKeys(myname2);
        driver.findElement(By.cssSelector("textarea[ng-model='Adress']")).sendKeys(address);
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("9876543210");
        driver.findElement(By.cssSelector("input[value='Male']")).click();
        driver.findElement(By.id("checkbox1")).click();
        driver.findElement(By.id("submitbtn")).click();
        if(myname2.contains("Bond"))  Assert.fail("Asserting on purpose");
        try{Thread.sleep(2000);}catch (Exception t){}
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

    @DataProvider(name="EmpDataProviderExcel")
    public Object[][] getDataFromDataproviderExcel(){
        String sExcelFilePath = System.getProperty("user.dir")+ "\\src\\test\\resources\\userData\\UserInfo.xlsx";
        return ReadExcelcontent2D(sExcelFilePath);
    }

    public static String[][] ReadExcelcontent2D(String sFilePath) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(sFilePath));
            XSSFWorkbook workbook     = new XSSFWorkbook(excelFile);
            XSSFSheet worksheet       = workbook.getSheetAt(0);
            int totalNoOfRows         = 1 + worksheet.getLastRowNum();
            int totalNoOfCols         = worksheet.getRow(0).getLastCellNum();
            String[][] arrayExcelData = new String[totalNoOfRows][totalNoOfCols];
            for (int i= 0 ; i < totalNoOfRows; i++) {
                XSSFRow row = worksheet.getRow(i);
                for (int j=0; j < totalNoOfCols; j++) {
                    arrayExcelData[i][j] = row.getCell(j).toString();
                }
            }
            workbook.close();
            return arrayExcelData;
        }catch(Exception g){g.printStackTrace(); }
        return null;
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
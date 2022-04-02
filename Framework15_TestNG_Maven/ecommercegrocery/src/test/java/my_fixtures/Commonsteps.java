package my_fixtures;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class Commonsteps {

    @BeforeSuite
    public void CommonBeforeSuite(){
        System.out.println("=CommonBeforeSuite==");
    }

    @AfterSuite
    public void CommonAfterSuite(){
        System.out.println("=CommonAfterSuite==");
    }




    @BeforeTest
    public void CommonBeforeTest(){
        System.out.println("==CommonBeforeTest==");
    }

    @AfterTest
    public void CommonAfterTest(){
        System.out.println("==CommonAfterTest==");
    }




}

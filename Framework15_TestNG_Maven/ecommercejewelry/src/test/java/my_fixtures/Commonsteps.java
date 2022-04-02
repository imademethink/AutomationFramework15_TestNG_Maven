package my_fixtures;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class Commonsteps {
    private static final Logger myLog = Logger.getLogger(Commonsteps.class);

    @BeforeSuite
    public void CommonBeforeSuite(){
        myLog.info("=CommonBeforeSuite==");
    }

    @AfterSuite
    public void CommonAfterSuite(){
        myLog.info("=CommonAfterSuite==");
    }




    @BeforeTest
    public void CommonBeforeTest(){
        myLog.info("==CommonBeforeTest==");
    }

    @AfterTest
    public void CommonAfterTest(){
        myLog.info("==CommonAfterTest==");
    }




}

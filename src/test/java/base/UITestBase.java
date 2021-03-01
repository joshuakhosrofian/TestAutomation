package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utility.DriverUtil;
import utility.TestListener;

public abstract class UITestBase {

    @BeforeMethod
    protected void setUp(){
        DriverUtil.openBrowser();
    }

    @AfterMethod
    protected void cleanUp(){
        DriverUtil.closeBrowser();
    }

    protected void LOG(String stepInfo){
        TestListener.getTestcase().info(stepInfo);
    }

    protected void IMG(String message){
        String screenshot = ((TakesScreenshot) DriverUtil.getDriver()).getScreenshotAs(OutputType.BASE64);
        TestListener.getTestcase().addScreenCaptureFromBase64String(screenshot, message);
    }
}

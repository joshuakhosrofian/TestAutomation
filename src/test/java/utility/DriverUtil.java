package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtil {

    private static WebDriver driver;
    private static String CHOSEN_BROWSER         = "";
    private static final String CHROME           = "chrome";
    private static final String CHROME_PRIVATE   = "chrome_private";
    private static final String CHROME_HEADLESS  = "chrome_headless";
    private static final String FIREFOX          = "firefox";
    private static final String FIREFOX_HEADLESS = "firefox_headless";
    private static final String FIREFOX_PRIVATE  = "firefox_private";
    private static final String EDGE             = "ms_edge";
    private static final String EDGE_PRIVATE     = "ms_edge_private";
    private static final String IE               = "ms_ie";

    //region Browser Actions

    /**
     * This method opens the web browser using the ChromeDriver,
     * and maximizes the window
     */
    public static void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // === Google Chrome Browsers === //
        CHOSEN_BROWSER = CHROME;
        driver.manage().window().maximize();
    }


    /**
     * This method opens the web browser using driver
     * @param browserType a choice of multiple browsers depending the browser type
     */
    public static void openBrowser(String browserType) {
        if(browserType.equalsIgnoreCase(CHROME)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            CHOSEN_BROWSER = CHROME;
        }
        else if(browserType.equalsIgnoreCase(EDGE)) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            CHOSEN_BROWSER = EDGE;
        }
        else if(browserType.equalsIgnoreCase(FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            CHOSEN_BROWSER = FIREFOX;
        }
    }

    /**
     * Call this method to get the driver
     * @return driver or if driver is null, this message: "WebDriver instance is null"
     */
    public static WebDriver getDriver() {
        if(driver == null) {
            throw new NullPointerException("WebDriver instance is null");
        }
        return driver;
    }

    /**
     * Call this method to return chosen browser as a String
     * @return chosen browser as a String
     */
    public static String getChosenBrowser() {
        return CHOSEN_BROWSER;
    }

    /**
     * This method closes the browser.
     * If the driver is not null, then it will quit using the browser.
     */
    public static void closeBrowser(){
        if(CHOSEN_BROWSER.equals(FIREFOX)) {
            if(driver != null) {
                driver.quit();
                return;
            }
            return;
        }
        if (driver != null){
            driver.close();
            driver.quit();
        }
    }
}

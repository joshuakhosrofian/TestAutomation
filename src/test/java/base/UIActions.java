package base;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.DriverUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class UIActions {

    private static final Integer WAIT_TIME = 30;
    protected static WebDriver driver;
    private static WebDriverWait wait;
    private static ArrayList<String> tabs;

    /**
     *
     */
    public UIActions() {
        driver = DriverUtil.getDriver();
        wait = new WebDriverWait(driver, WAIT_TIME);
    }

    /**
     * Call this method to expand the driver window to full-screen
     */
    protected void fullScreen() {
        driver.manage().window().fullscreen();
    }

    /**
     * Call this method to maximize the Driver window
     */
    protected void maximize() {
        driver.manage().window().maximize();
    }

    /**
     * Call this method to switch driver to iFrame
     */
    protected void switchToIFrame() {
        WebElement iframe = waitUntilElementVisible(By.tagName("iframe"));
        driver = driver.switchTo().frame(iframe);
    }

    /**
     * This method switches driver to iFrame by locator
     * @param locator location of iFrame element
     */
    protected void switchToIFrame(By locator) {
        WebElement iframe = waitUntilElementVisible(locator);
        driver = driver.switchTo().frame(iframe);
    }


    /**
     * Call this method to switch the driver back to the default setting
     */
    protected void switchBackFromIframe() {
        driver = driver.switchTo().defaultContent();
    }


    /**
     * Call this method to open a new tab in the driver window
     */
    protected void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    /**
     * This method allows driver to switch to another tab
     * @param tabNumber int value of tab to switch to
     */
    protected void switchToTab(int tabNumber) {
        driver.switchTo().window(tabs.get(tabNumber));
    }

    /**
     * This method closes out a tab in the driver
     */
    protected void closeTab() {
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    /**
     * This method loads cookie using a cookie object
     * @param name name of cookie as a String
     * @param token cookie identifier (String)
     */
    protected void loadCookie(String name, String token) {
        Cookie cookie = new Cookie(name, token);
        driver.manage().addCookie(cookie);
    }

    /**
     * This method deletes a cookie
     * @param name name of cookie as a String
     */
    protected void deleteCookie(String name) {
        driver.manage().deleteCookieNamed(name);
    }

    /**
     * This method deletes all cookies
     */
    protected void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }
    //endregion

    /**
     * Call this method to go to site URL
     * @param url URL as a String
     */
    protected void gotoSite(String url) {
        driver.get(url);
    }

    /**
     * Refreshes the web page
     */
    protected void reload() {
        driver.navigate().refresh();
    }

    /**
     * Goes back to the previous web page
     */
    protected void goBack() {
        driver.navigate().back();
    }

    /**
     * Goes forward to the next web page
     */
    protected void goForward() {
        driver.navigate().forward();
    }

    /**
     * Call this method to get URL
     * @return this method will return the current URL
     */
    protected String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    /**
     * Returns the Title of the web page in the form of a String
     * @return title
     */
    protected String title() {
        return driver.getTitle();
    }


    /**
     * This is the click method using the locator, allows elements on the web page to be clicked.
     * If the element is not clickable, then it will provide the following message:
     * "Element was not clickable. Check its locators logic ( Ex: css, xpath .etc)"
     * @param locator location of web element
     */
    protected void click(By locator) {
        try {
            highlight(locator);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception ex) {
            System.out.println("Element was not clickable. Check its locators logic ( Ex: css, xpath .etc)");
        }
    }

    /**
     * This click method uses a String, using an xpath selector to find elements with a String
     * If cannot be found then it will return a message: "Element with given text could not be found."
     * @param text String form of expression element
     */
    protected void click(String text) {
        String expression = "//*[text()='" + text + "']";
        By selector = By.xpath(expression);
        waitUntilElementVisible(selector);
        List<WebElement> foundElems = driver.findElements(selector);
        if (foundElems.size() == 0) {
            throw new NotFoundException("Element with given text could not be found.");
        } else {
            for (WebElement elem : foundElems) {
                elem.click();
                break;
            }
        }
    }


    /**
     * This method double clicks if a certain feature on a webpage needs a double click
     * @param locator location of web element
     */
    protected void doubleClick(By locator) {
        highlight(locator);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        new Actions(driver)
                .doubleClick(element)
                .build()
                .perform();
    }

    /**
     * This method right clicks on a webpage
     * @param locator location of web element
     */
    protected void rightClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        new Actions(driver)
                .contextClick(element)
                .build()
                .perform();
    }


    /**
     * This method allows us to hover over elements without actually clicking on them
     * @param locator location of web element (named elem)
     */
    protected void hover(By locator) {
        WebElement elem = waitUntilElementVisible(locator);
        new Actions(driver).moveToElement(elem).build().perform();
    }


    /**
     * You can focus on elements without actually clicking on them using this method
     * @param locator location of web element
     */
    protected void focus(By locator) {
        WebElement element = waitUntilElementVisible(locator);
        if ("input".equals(element.getTagName())) {
            element.sendKeys("");
        } else {
            new Actions(driver).moveToElement(element).perform();
        }
    }

    /**
     * Call this method to move mouse down on a web element
     * @param element web element
     */
    protected void mouseDownOn(By element) {
        new Actions(driver)
                .moveToElement(waitUntilElementVisible(element))
                .clickAndHold().perform();
    }

    /**
     * Call this method to move to a certain web element
     * @param element web element
     */
    protected void moveTo(By element) {
        new Actions(driver).moveToElement(waitUntilElementVisible(element))
                .build()
                .perform();
    }

    /**
     * Call this method to move mouse up on a certain web element
     * @param element web element
     */
    protected void mouseUpOn(By element) {
        new Actions(driver).moveToElement(waitUntilElementVisible(element))
                .release()
                .perform();
    }

    /**
     * This allows the driver to drag and drop
     * @param base element that will be clicked and dragged on
     * @param target element that will be dropped into
     */
    protected void dragAndDrop(By base, By target) {
        mouseDownOn(base);
        moveTo(target);
        mouseUpOn(target);
    }

    /**
     * Call this method to move the cursor to view an element
     * @param selector element selector
     */
    protected void moveViewToElement(By selector) {
        WebElement where;
        Actions builder = new Actions(driver);
        where = waitUntilElementVisible(selector);
        builder.moveToElement(where).perform();
    }

    /**
     * Call this method to scroll to the bottom of the page
     */
    protected void scrollToBottom() {
        String jscode = "window.scrollTo(0, document.body.scrollHeight)";
        ((JavascriptExecutor) driver).executeScript(jscode);
    }

    /**
     * Call this method to scroll to the top of the page
     */
    protected void scrollToTop() {
        String jsCode = "window.scrollTo(0, 0)";
        ((JavascriptExecutor) driver).executeScript(jsCode);
    }

    /**
     * Call this to scroll down by pixel size
     * @param pixelnum pixel size
     */
    protected void scrollDownByPixel(int pixelnum) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixelnum + ")");
    }

    /**
     * Call this to scroll up by pixel size
     * @param pixelnum pixel size
     */
    protected void scrollUpByPixel(int pixelnum) {
        pixelnum = pixelnum - (pixelnum * 2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixelnum + ")");
    }

    /**
     * Call this method to highlight any element on a webpage
     * @param locator location of web element
     */
    protected void highlight(By locator) {
        WebElement element = waitUntilElementVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red;');", element);
    }

    /**
     * Call this method to flash any element on a webpage
     * @param locator location of web element
     */
    protected void flash(By locator) {
        WebElement element = waitUntilElementVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid blue;');", element);
        waitforMili(2);
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid white;');", element);
        waitforMili(2);
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid blue;');", element);
        waitforMili(2);
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid white;');", element);
        waitforMili(2);
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid blue;');", element);
    }


    /**
     * Call this method to highlight any web element in the form of text
     * @param locator location of web element
     */
    protected void textHighlight(By locator) {
        WebElement element = waitUntilElementVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsCode = "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');";
        js.executeScript(jsCode, element);
    }

    /**
     * Call this method to clear everything visible on webpage
     * @param locator location of web element
     */
    protected void clear(By locator) {
        waitUntilElementVisible(locator).clear();
    }

    //
    protected void write(By locator, String text) {
        highlight(locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Call this method to clear then write String text
     * @param locator location of web element
     * @param text element text as a String
     */
    protected void clearThenWrite(By locator, String text) {
        WebElement inputElem = waitUntilElementVisible(locator);
        inputElem.clear();
        inputElem.sendKeys(text);
    }

    /**
     * Call this method to implement a wait time between UIActions during automation
     * @param second int value of wait time seconds
     */
    protected void waitfor(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (Exception ignored) {
            // DO NOTHING
        }
    }

    /**
     * Call this method to implement a wait time in milliseconds between UIActions during automation
     * @param second int value of wait time in milliseconds
     */
    protected void waitforMili(int second) {
        try {
            Thread.sleep(second * 100);
        } catch (Exception ignored) {
            // DO NOTHING
        }
    }

    /**
     * Call this method to submit
     * @param element web element
     */
    protected void submit(By element) {
        waitUntilElementVisible(element).submit();
    }

    /**
     * Call this method to find an element
     * @param locator location of web element
     * @return web element you are looking for
     */
    protected WebElement findElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    /**
     * Call this method to provide a list of web elements on a webpage
     * @param locator location of web element
     * @return list of web elements
     */
    protected List<WebElement> listOfElements(By locator) {
        waitUntilElementVisible(locator);
        return driver.findElements(locator);
    }
    //endregion


    //region Selectors

    /**
     * This By method returns web element in the form of css
     * @param expression in the form of element css selector
     * @return web element
     */
    protected By css(String expression) {
        return By.cssSelector(expression);
    }

    /**
     * This By method returns web element in the form of id
     * @param expression in the form of element id
     * @return web element
     */
    protected By id(String expression) {
        return By.id(expression);
    }

    /**
     * This By method returns web element in the form of xpath
     * @param expression in the form of element xpath
     * @return web element
     */
    protected By xpath(String expression) {
        return By.xpath(expression);
    }

    /**
     * This By method returns web element in the form of link
     * @param expression in the form of element link
     * @return web element
     */
    protected By link(String expression) {
        return By.linkText(expression);
    }

    /**
     * This By method returns web element with link text that it contains
     * @param expression in the form of element link text
     * @return web element
     */
    protected By linkTextContains(String expression) {
        return By.partialLinkText(expression);
    }

    /**
     * Call this method to get name attribute
     * @param expression name attribute as a String
     * @return name attribute
     */
    protected By nameAttribute(String expression) {
        return By.name(expression);
    }

    /**
     * Call this method to get a tag name (with any web element)
     * @param expression tag name as a String
     * @return tag name
     */
    protected By withTag(String expression) {
        return By.tagName(expression);
    }
    //endregion


    //region Waiters

    /**
     * This method lets the web driver wait until the element is visible
     * @param locator location of web element
     * @return wait time for element visibility
     */
    private WebElement waitUntilElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * This method lets web driver wait until element is invisible
     * @param locator location of web element
     * @return if element is invisible, it will return a boolean value of false
     */
    private boolean waitUntilElementInvisible(By locator) {
        return false;
    }


    /**
     * Call this method to determine whether or not an element is displayed
     * @param locator location of web element
     * @return boolean value if element is displayed or not
     */
    protected boolean isElementDisplayed(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }
    //endregion


    //region Private Helper Methods

    /**
     * This method will click on whatever element depending on the int value of that element
     * @param element web element
     * @param index int of value of web element
     */
    private void click_NthElement(By element, int index) {
        waitUntilElementVisible(element);
        List<WebElement> elementAllElements = driver.findElements(element);
        WebElement elementElementByIdx = elementAllElements.get(index);
        elementElementByIdx.click();
    }
    //endregion
}
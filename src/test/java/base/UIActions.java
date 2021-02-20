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


    public UIActions() {
        driver = DriverUtil.getDriver();
        wait = new WebDriverWait(driver, WAIT_TIME);
    }


    protected void fullScreen() {
        driver.manage().window().fullscreen();
    }


    protected void maximize() {
        driver.manage().window().maximize();
    }


    protected void switchToIFrame() {
        WebElement iframe = waitUntilElementVisible(By.tagName("iframe"));
        driver = driver.switchTo().frame(iframe);
    }


    protected void switchToIFrame(By locator) {
        WebElement iframe = waitUntilElementVisible(locator);
        driver = driver.switchTo().frame(iframe);
    }


    protected void switchBackFromIframe() {
        driver = driver.switchTo().defaultContent();
    }


    protected void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    protected void switchToTab(int tabNumber) {
        driver.switchTo().window(tabs.get(tabNumber));
    }


    protected void closeTab() {
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }


    protected void loadCookie(String name, String token) {
        Cookie cookie = new Cookie(name, token);
        driver.manage().addCookie(cookie);
    }


    protected void deleteCookie(String name) {
        driver.manage().deleteCookieNamed(name);
    }


    protected void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }
    //endregion


    protected void gotoSite(String url) {
        driver.get(url);
    }


    protected void reload() {
        driver.navigate().refresh();
    }


    protected void goBack() {
        driver.navigate().back();
    }


    protected void goFoward() {
        driver.navigate().forward();
    }


    protected String getCurrentURL() {
        return driver.getCurrentUrl();
    }


    protected String title() {
        return driver.getTitle();
    }


    protected void click(By locator) {
        try {
            highlight(locator);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception ex) {
            System.out.println("Element was not clickalbe. Check its locators logic ( Ex: css, xpath .etc");
        }
    }


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


    protected void doubleClick(By locator) {
        highlight(locator);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        new Actions(driver)
                .doubleClick(element)
                .build()
                .perform();
    }


    protected void rightClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        new Actions(driver)
                .contextClick(element)
                .build()
                .perform();
    }


    protected void hover(By locator) {
        WebElement elem = waitUntilElementVisible(locator);
        new Actions(driver).moveToElement(elem).build().perform();
    }


    protected void focus(By locator) {
        WebElement element = waitUntilElementVisible(locator);
        if ("input".equals(element.getTagName())) {
            element.sendKeys("");
        } else {
            new Actions(driver).moveToElement(element).perform();
        }
    }


    protected void mouseDownOn(By element) {
        new Actions(driver)
                .moveToElement(waitUntilElementVisible(element))
                .clickAndHold().perform();
    }


    protected void moveTo(By element) {
        new Actions(driver).moveToElement(waitUntilElementVisible(element))
                .build()
                .perform();
    }


    protected void mouseUpOn(By element) {
        new Actions(driver).moveToElement(waitUntilElementVisible(element))
                .release()
                .perform();
    }


    protected void dragAndDrop(By base, By target) {
        mouseDownOn(base);
        moveTo(target);
        mouseUpOn(target);
    }


    protected void moveViewToElement(By selector) {
        WebElement where;
        Actions builder = new Actions(driver);
        where = waitUntilElementVisible(selector);
        builder.moveToElement(where).perform();
    }


    protected void scrollToBottom() {
        String jscode = "window.scrollTo(0, document.body.scrollHeight)";
        ((JavascriptExecutor) driver).executeScript(jscode);
    }


    protected void scrollToTop() {
        String jsCode = "window.scrollTo(0, 0)";
        ((JavascriptExecutor) driver).executeScript(jsCode);
    }


    protected void scrollDownByPixel(int pixelnum) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixelnum + ")");
    }


    protected void scrollUpByPixel(int pixelnum) {
        pixelnum = pixelnum - (pixelnum * 2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixelnum + ")");
    }


    protected void highlight(By locator) {
        WebElement element = waitUntilElementVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red;');", element);
    }

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


    protected void textHighlight(By locator) {
        WebElement element = waitUntilElementVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsCode = "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');";
        js.executeScript(jsCode, element);
    }


    protected void clear(By locator) {
        waitUntilElementVisible(locator).clear();
    }


    protected void write(By locator, String text) {
        highlight(locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    protected void clearThenWrite(By locator, String text) {
        WebElement inputElem = waitUntilElementVisible(locator);
        inputElem.clear();
        inputElem.sendKeys(text);
    }


    protected void waitfor(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (Exception ignored) {
            // DO NOTHING
        }
    }

    protected void waitforMili(int second) {
        try {
            Thread.sleep(second * 100);
        } catch (Exception ignored) {
            // DO NOTHING
        }
    }


    protected void submit(By element) {
        waitUntilElementVisible(element).submit();
    }


    protected WebElement findElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }


    protected List<WebElement> listOfElements(By locator) {
        waitUntilElementVisible(locator);
        return driver.findElements(locator);
    }
    //endregion


    //region Selectors
    protected By css(String expression) {
        return By.cssSelector(expression);
    }


    protected By id(String expression) {
        return By.id(expression);
    }


    protected By xpath(String expression) {
        return By.xpath(expression);
    }


    protected By link(String expression) {
        return By.linkText(expression);
    }


    protected By linktextContains(String expression) {
        return By.partialLinkText(expression);
    }


    protected By nameAttribute(String expression) {
        return By.name(expression);
    }


    protected By withTag(String expression) {
        return By.tagName(expression);
    }
    //endregion


    //region Waiters
    private WebElement waitUntilElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    private boolean waitUntilElementInvisible(By locator) {
        return false;
    }


    protected boolean isElementDisplayed(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }
    //endregion


    //region Private Helper Methods
    private void click_NthElement(By element, int index) {
        waitUntilElementVisible(element);
        List<WebElement> elementAllElements = driver.findElements(element);
        WebElement elementElementByIdx = elementAllElements.get(index);
        elementElementByIdx.click();
    }
    //endregion
}
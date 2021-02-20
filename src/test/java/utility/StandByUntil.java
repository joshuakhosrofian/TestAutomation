//package utility;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.util.List;
//
//public class StandByUntil {
//
//    private static WebDriverWait wait;
//
//    public StandByUntil(WebDriverWait webDriverWait) {
//        wait = WebDriverWait;
//    }
//
//
//    public Boolean titleOfPageMatchesWith(final String title) {
//        return wait.until(ExpectedConditions.titleIs(title));
//    }
//
//    public Boolean titleOfPageContains(final String title) {
//        return wait.until(ExpectedConditions.titleContains(title));
//    }
//
//    public Boolean currentURLChangesToThisURL(final String url) {
//        return wait.until(ExpectedConditions.urlContains(text));
//    }
//
//    public Boolean currentURLContainsText(final String text) {
//        return wait.until(ExpectedConditions.urlContains(text));
//    }
//
//    public Boolean currentURLMatchesWithRegex(final String regex) {
//        return wait.until(ExpectedConditions.urlMatches(regex));
//    }
//
//    public WebElement elementIsThere(final By locator) {
//        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//    }
//
//    public WebElement elementIsThereAndVisibleToUser(final  By locator) {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//    }
//
//    public List<WebElement> listOfTheseElementsPresentAndVisible(final By locator) {
//        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
//    }
//
//    public Boolean eachOfTheseElementsAreThereAndVisible(List<WebElement> elements) {
//        int size = wait.until(ExpectedConditions.visibilityOfAllElements(elements)).size();
//        return size != 0;
//    }
//
//    public WebElement elementIsVisibleToUser(WebElement element) {
//        return wait.until(ExpectedConditions.visibilityOf(element));
//    }
//
//
//    public Boolean textIsPresentInElement(By locator, String text) {
//        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
//    }
//
//
//    public Boolean textIsPresentInElement(WebElement element, String text) {
//        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
//    }
//
//
//    public Boolean valueAttributeTextPresent(WebElement element, String valueAttrText) {
//        return wait.until(ExpectedConditions.textToBePresentInElementValue(element, valueAttrText));
//    }
//
//
//    public Boolean valueAttributeTextPresent(By locator, String valueAttrText) {
//        return wait.until(ExpectedConditions.textToBePresentInElementValue(locator, valueAttrText));
//    }
//
//
//    public WebDriver frameIsReadyThenSwitch(String frameLocator) {
//        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
//    }
//
//    public WebDriver frameIsReadyThenSwitch(By locator) {
//        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
//    }
//
//
//    public WebDriver frameIsReadyThenSwitch(int frameNumber) {
//        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNumber));
//    }
//
//    public WebDriver frameIsReadyThenSwitch(WebElement frameElement) {
//        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
//    }
//
//
//    public Boolean elementIsInvisible(By locator) {
//        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//    }
//
//    public Boolean elementWithTextIsInvisible(By locator, String text) {
//        return wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
//    }
//
//    public WebElement elementIsClickable(By locator) {
//        return wait.until(ExpectedConditions.elementToBeClickable(locator));
//    }
//
//    public WebElement elementIsClickable(WebElement element) {
//        return wait.until(ExpectedConditions.elementToBeClickable(element));
//    }
//
//
//    public Boolean elementIsNoLongerPresentOnDOM(WebElement element) {
//        return wait.until(ExpectedConditions.stalenessOf(element));
//    }
//
//
//    public WebElement waitForElementToBeRefreshedAndClickable(By locator) {
//        return wait.until(ExpectedConditions.refreshed(
//                ExpectedConditions.elementToBeClickable(locator)));
//    }
//
//
//    public Boolean elementIsSelected(WebElement element) {
//        return wait.until(ExpectedConditions.elementToBeSelected(element));
//    }
//
//
//    public Boolean elementIsUnselected(WebElement element) {
//        try {
//            return wait.until(
//                    ExpectedConditions.elementSelectionStateToBe(
//                            elementIsVisibleToUser(element), false));
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//
//    public Boolean elementIsUnselected(By locator) {
//        try {
//            return wait.until(
//                    ExpectedConditions.elementSelectionStateToBe(
//                            locator, false));
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public Boolean javascriptExecutedWithoutError(String scripts) {
//        return wait.until(ExpectedConditions.javaScriptThrowsNoExceptions(scripts));
//    }
//}
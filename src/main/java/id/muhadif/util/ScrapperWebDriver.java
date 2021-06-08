package id.muhadif.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author muhadif
 * @create 07/06/21 21.48
 */
public class ScrapperWebDriver {
    private static final String USER_AGENT =
            "user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.1.1 Safari/605.1.15";
    private static final String GOOGLE_URL = "https://www.google.com";

    private static final String JS_WINDOW_OPEN = "window.open()";
    private static final String JS_SCROLL = "window.scrollBy(0,600)";
    private static final String JS_SCROLL_SLOW = "window.scrollBy(0,100)";
    private static final String JS_REMOVE_ELEMENT = "document.querySelector('%s').parentElement.remove();";

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private JavascriptExecutor jsExecutor;

    public ScrapperWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/muhadif/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(USER_AGENT);
        chromeOptions.setHeadless(true);

        webDriver = new ChromeDriver(chromeOptions);
        webDriverWait = new WebDriverWait(webDriver, 10);
        jsExecutor = (JavascriptExecutor) webDriver;
    }

    public List<String> prepareTwoTabs() {
        webDriver.get(GOOGLE_URL);
        jsExecutor.executeScript(JS_WINDOW_OPEN);
        return new ArrayList<>(webDriver.getWindowHandles());
    }

    public List<WebElement> getElementListByScrollingDown(String url, String xpath, String tab) {
        switchTab(tab);
        webDriver.get(url);
        jsExecutor.executeScript(JS_SCROLL);
        return webDriver.findElements(By.xpath(xpath));
    }

    public void openNewTab(String path, String tab) {
        switchTab(tab);
        webDriver.get(path);
    }

    public void waitOnElement(String xpath) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(xpath)));
    }

    public void removeElement(String element) {
        jsExecutor.executeScript(String.format(JS_REMOVE_ELEMENT, element));
    }

    public String getText(String xpath) {
        return webDriver.findElements(By.xpath(xpath)).isEmpty()
                ? "" : webDriver.findElement(By.xpath(xpath)).getText();
    }

    public String getText(String xpath, String attribute) {
        return webDriver.findElements(By.xpath(xpath)).isEmpty()
                ? "" : webDriver.findElement(By.xpath(xpath)).getAttribute(attribute);
    }

    public void clickElement(String xpath){
       webDriver.findElement(By.xpath(xpath)).click();
    }

    public void switchTab(String tab) {
        webDriver.switchTo().window(tab);
    }

    public void scrollDown(){
        jsExecutor.executeScript(JS_SCROLL_SLOW);
    }

    public void quit() {
        webDriver.quit();
    }

}

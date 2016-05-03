package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobject.element.DropDownValue;
import pageobject.element.PageElement;
import pageobject.element.PlaceholdersProvider;
import utils.UiSeleniumTestException;

import java.util.ArrayList;
import java.util.List;

import static context.SeleniumContext.SELENIUM_CONTEXT;

public class PageObject<T extends PageElement> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(PageObject.class);

    protected final long DEFAULT_TIMEOUT = SELENIUM_CONTEXT.getDefaultTimeout();
    protected final long ALERT_TIMEOUT = SELENIUM_CONTEXT.getAlertTimeout();
    protected final String URL = SELENIUM_CONTEXT.getUrl();
    protected final WebDriver SESSION;

    public PageObject(WebDriver session) {
        this.SESSION = session;
    }

    private WebElement getWebElement(T element, String... placeholders) {
        return new WebDriverWait(SESSION, DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(
                element.getBy(placeholders)));
    }

    private void sendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    private void sendKeys(WebElement element, Keys key) {
        element.sendKeys(key);
    }

    private void adjustCheckboxState(T element, boolean state, String... placeholders) {
        WebElement checkbox = getWebElement(element, placeholders);
        if (checkbox.isSelected() != state) {
            checkbox.click();
        }
    }

    private void setSelectedOption(T element, String visibleText, String... placeholders) throws
            UiSeleniumTestException {
        Select selectBox = new Select(getWebElement(element, placeholders));
        List<WebElement> options = selectBox.getOptions();
        List<String> optionsTexts = new ArrayList<>();

        boolean valueAvailable = false;
        for (WebElement option : options) {
            optionsTexts.add(option.getText());
            if (option.getText().equals(visibleText)) {
                valueAvailable = true;
                break;
            }
        }

        if (!valueAvailable) {
            throw new UiSeleniumTestException(
                    "There is no option with visible text equal to %s in drop down. Visible options: %s.", visibleText,
                    optionsTexts.toString());
        }

        selectBox.selectByVisibleText(visibleText);
    }

    private void waitForPageToLoad(WebElement controlElement) {
        new WebDriverWait(SESSION, DEFAULT_TIMEOUT).until(ExpectedConditions.stalenessOf(controlElement));
    }

    protected void waitForElementToBeVisible(T element, String... placeholders) {
        new WebDriverWait(SESSION, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(
                element.getBy(placeholders)));
    }

    protected void waitForElementToBeNotVisible(T element, String... placeholders) {
        new WebDriverWait(SESSION, DEFAULT_TIMEOUT).until(ExpectedConditions.invisibilityOfElementLocated(
                element.getBy(placeholders)));
    }

    protected void sendKeys(T element, Keys key, String... placeholders) {
        sendKeys(getWebElement(element, placeholders), key);
    }

    protected boolean isEnabled(T element, String... placeholders) {
        return getWebElement(element, placeholders).isEnabled();
    }

    protected boolean isVisible(T element, String... placeholders) {
        return getWebElement(element, placeholders).isDisplayed();
    }

    protected String getText(T element, String... placeholders) {
        return getWebElement(element, placeholders).getText();
    }

    protected void setSelectedOption(T element, DropDownValue value, String... placeholders) throws
            UiSeleniumTestException {
        setSelectedOption(element, value.getValue(), placeholders);
    }

    protected String getSelectedOption(T element, String... placeholders) {
        return new Select(getWebElement(element, placeholders)).getFirstSelectedOption().getText();
    }

    public void click(T element, String... placeholders) {
        getWebElement(element, placeholders).click();
    }

    public void clickAndWaitForPageToLoad(T element, String... placeholders) {
        WebElement controlElement = getWebElement(element, placeholders);
        click(element);
        waitForPageToLoad(controlElement);
    }

    public void sendKeys(T element, String text, String... placeholders) {
        sendKeys(getWebElement(element, placeholders), text);
    }

    public void pressTabKey(T element, String... placeholders) {
        sendKeys(element, Keys.TAB, placeholders);
    }

    public void clear(T element, String... placeholders) {
        getWebElement(element, placeholders).clear();
    }

    public void tickCheckbox(T element, String... placeholders) {
        adjustCheckboxState(element, true, placeholders);
    }

    public void untickCheckbox(T element, String... placeholders) {
        adjustCheckboxState(element, false, placeholders);
    }

    public void shouldHave(T element, String... placeholders) {
        getWebElement(element, placeholders);
    }

    public void shouldHave(T element, PlaceholdersProvider placeholdersProvider) {
        shouldHave(element, placeholdersProvider.getPlaceholders());
    }

    public void shouldHaveEnabled(T element, String... placeholders) throws UiSeleniumTestException {
        if (isEnabled(element, placeholders)) {
            throw new UiSeleniumTestException("Element located by %s = '%s' is not enabled.", element.getType(),
                    element.getExpression());
        }
    }

    public void closeAlert(String alertMessage) throws UiSeleniumTestException {
        new WebDriverWait(SESSION, ALERT_TIMEOUT).until(ExpectedConditions.alertIsPresent());
        Alert alert = SESSION.switchTo().alert();
        if (alert.getText().equals(alertMessage)) {
            alert.accept();
        } else {
            throw new UiSeleniumTestException("Expected alert with '%s' message but got '%s' message.", alertMessage,
                    alert.getText());
        }
    }

    public void noAlertPresent() throws UiSeleniumTestException {
        try {
            new WebDriverWait(SESSION, ALERT_TIMEOUT).until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException ex) {
            return;
        }
        Alert alert = SESSION.switchTo().alert();
        throw new UiSeleniumTestException("Alert with '%s' message appeared.", alert.getText());
    }

    public void scrollTo(T element, String... placeholders) {
        ((Locatable) getWebElement(element, placeholders)).getCoordinates().inViewPort();
    }

}

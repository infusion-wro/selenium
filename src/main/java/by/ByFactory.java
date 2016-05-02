package by;

import org.openqa.selenium.By;

public class ByFactory {

    public static By get(ByType locatorType, String expression, String... placeholders) {
        expression = String.format(expression, placeholders);
        switch(locatorType) {
            case XPATH:
                return By.xpath(expression);
            case ID:
                return By.id(expression);
            case NAME:
                return By.name(expression);
            default:
                return null;
        }
    }

}

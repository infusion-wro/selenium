package pageobject.element;

import org.openqa.selenium.By;

public interface PageElement {

    By getBy(String... placeholders);
    String getType();
    String getExpression();

}

package pageobject.impl;

import by.ByFactory;
import by.ByType;
import org.openqa.selenium.By;
import pageobject.element.PageElement;

public enum MainPageElement implements PageElement {

    RESULTS_LINK(ByType.XPATH, "//a[.=':: Wyniki']");

    private ByType BY_TYPE;
    private String EXPRESSION;

    MainPageElement(ByType byType, String expression) {
        BY_TYPE = byType;
        EXPRESSION = expression;
    }

    @Override
    public By getBy(String... placeholders) {
        return ByFactory.get(BY_TYPE, EXPRESSION, placeholders);
    }

    @Override
    public String getType() {
        return BY_TYPE.getTypeName();
    }

    @Override
    public String getExpression() {
        return EXPRESSION;
    }

}

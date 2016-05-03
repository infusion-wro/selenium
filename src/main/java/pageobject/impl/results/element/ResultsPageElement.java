package pageobject.impl.results.element;

import by.ByFactory;
import by.ByType;
import org.openqa.selenium.By;
import pageobject.element.PageElement;

import static by.ByType.ID;
import static by.ByType.XPATH;

public enum ResultsPageElement implements PageElement {

    TOURNAMENT_DROP_DOWN(ID, "rozgrywki"),
    SEASON_DROP_DOWN(ID, "sezon"),
    GO_BUTTON(XPATH, "//input[@value='OK']"),
    RESULTS_ROW(XPATH, "//table[@class='tab mecz']//tr[(td[1] = '%s') and (td[2] = '%s')  and (td[3]/a = '%s')]");

    private ByType BY_TYPE;
    private String EXPRESSION;

    ResultsPageElement(ByType byType, String expression) {
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

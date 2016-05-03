package pageobject.impl.main;

import org.openqa.selenium.WebDriver;
import pageobject.PageObject;
import pageobject.impl.results.ResultsPage;

public class MainPage extends PageObject<MainPageElement> {

    public MainPage(WebDriver session) {
        super(session);
        SESSION.get(URL);
    }

    public ResultsPage clickResultsPage() {
        click(MainPageElement.RESULTS_LINK);
        return new ResultsPage(SESSION);
    }

}

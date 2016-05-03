package pageobject.impl.results;

import org.openqa.selenium.WebDriver;
import pageobject.PageObject;
import pageobject.impl.results.element.ResultsPageElement;
import pageobject.impl.results.element.dropdown.SeasonDropDownValue;
import pageobject.impl.results.element.dropdown.TournamentDropDownValue;
import utils.UiSeleniumTestException;

import static pageobject.impl.results.element.ResultsPageElement.SEASON_DROP_DOWN;
import static pageobject.impl.results.element.ResultsPageElement.TOURNAMENT_DROP_DOWN;

public class ResultsPage extends PageObject<ResultsPageElement> {

    public ResultsPage(WebDriver session) {
        super(session);
    }

    public void selectTournament(TournamentDropDownValue value) throws UiSeleniumTestException {
        setSelectedOption(TOURNAMENT_DROP_DOWN, value);
    }

    public void selectSeason(SeasonDropDownValue value) throws UiSeleniumTestException {
        setSelectedOption(SEASON_DROP_DOWN, value);
    }

}

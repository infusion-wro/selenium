package selenium.realmadrid;

import org.junit.Test;
import pageobject.impl.main.MainPage;
import pageobject.impl.results.ResultsPage;
import pageobject.impl.results.element.ResultRow;
import selenium.UiSeleniumTest;

import static pageobject.impl.results.element.ResultsPageElement.GO_BUTTON;
import static pageobject.impl.results.element.ResultsPageElement.RESULTS_ROW;
import static pageobject.impl.results.element.dropdown.SeasonDropDownValue.SEASON_2015_2014;
import static pageobject.impl.results.element.dropdown.TournamentDropDownValue.OFFICIAL_MATCHES;

public class SampleTest extends UiSeleniumTest {

    private final String DATE = "2014.08.12";
    private final String TOURNAMENT = "Superpuchar Europy - Cardiff";
    private final String MATCH = "Real - Sevilla 2 : 0";

    @Test
    public void testMainPage() throws Exception {
        // given
        MainPage mainPage = new MainPage(SESSION);
        ResultsPage resultsPage = mainPage.clickResultsPage();

        ResultRow.Builder resultRowBuilder = new ResultRow.Builder();
        resultRowBuilder.withDate(DATE).withTournament(TOURNAMENT).withMatch(MATCH);
        ResultRow resultRow = resultRowBuilder.create();

        // when
        resultsPage.selectTournament(OFFICIAL_MATCHES);
        resultsPage.selectSeason(SEASON_2015_2014);
        resultsPage.click(GO_BUTTON);

        // then
        resultsPage.shouldHave(RESULTS_ROW, resultRow);
    }

}

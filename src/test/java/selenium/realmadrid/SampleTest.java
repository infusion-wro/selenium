package selenium.realmadrid;

import org.junit.Test;
import pageobject.impl.MainPage;
import pageobject.impl.MainPageElement;
import selenium.UiSeleniumTest;

public class SampleTest extends UiSeleniumTest {

    @Test
    public void testMainPage() {
        // given
        MainPage mainPage = new MainPage(SESSION);

        // then
        mainPage.shouldHave(MainPageElement.RESULTS_LINK);
    }

}

package pageobject.impl;

import org.openqa.selenium.WebDriver;
import pageobject.PageObject;

public class MainPage extends PageObject<MainPageElement> {

    public MainPage(WebDriver session) {
        super(session);
        SESSION.get(URL);
    }

}

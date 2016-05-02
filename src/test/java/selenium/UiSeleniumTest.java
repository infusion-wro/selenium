package selenium;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static context.SeleniumContext.SELENIUM_CONTEXT;

public class UiSeleniumTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(UiSeleniumTest.class);

    protected static WebDriver SESSION;

    @Before
    public void setUp() {
        SESSION = SELENIUM_CONTEXT.getNewSession();
    }

    @Rule
    public TestRule testWatcher = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            if (SELENIUM_CONTEXT.areScreenshotsEnabled()) {
                File srcFile = ((TakesScreenshot) SESSION).getScreenshotAs(OutputType.FILE);
                String dir = SELENIUM_CONTEXT.getScreenshotsDir();
                String timeStamp = new SimpleDateFormat(("yyyyMMdd_HH,,ss")).format(Calendar.getInstance().getTime());
                String filePath = String.format("%s/%s%s.png", dir, description.getMethodName(), timeStamp);
                try {
                    LOGGER.debug("Saving screenshot to: {}.", filePath);
                    FileUtils.copyFile(srcFile, new File(filePath));
                    SESSION.quit();
                } catch (IOException ex) {
                    LOGGER.error(ex.getMessage());
                    SESSION.quit();
                }
            } else {
                SESSION.quit();
            }
        }

        @Override
        protected void succeeded(Description description) {
            SESSION.quit();
        }

    };

}

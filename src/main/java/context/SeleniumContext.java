package context;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum SeleniumContext {

    SELENIUM_CONTEXT;

    private final Logger LOGGER = LoggerFactory.getLogger(SeleniumContext.class);

    private final String DEFAULT_CHROME_DRIVER_PATH = this.getClass().getResource("chromedriver.exe").getPath();
    private final String PROPERTIES_FILE_NAME = "selenium.properties";
    private final String URL;
    private final long DEFAULT_TIMEOUT;
    private final long ALERT_TIMEOUT;
    private final boolean SCREENSHOTS_ENABLED;
    private final String SCREENSHOTS_DIR;

    SeleniumContext() {
        Properties properties = null;
        try {
            properties = getProperties();
        } catch (IOException ex) {
            LOGGER.error("Error loading selenium context: {}.", ex.getMessage());
        }
        if (properties != null) {
            if (!StringUtils.isEmpty(properties.getProperty("webdriver.chrome.driver"))) {
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
            } else {
                System.setProperty("webdriver.chrome.driver", DEFAULT_CHROME_DRIVER_PATH);
            }
            URL = properties.getProperty("url");
            DEFAULT_TIMEOUT = Long.parseLong(properties.getProperty("timeout.default"));
            ALERT_TIMEOUT = Long.parseLong(properties.getProperty("timeout.alert"));
            SCREENSHOTS_ENABLED = Boolean.parseBoolean(properties.getProperty("screenshots.enabled"));
            SCREENSHOTS_DIR = properties.getProperty("screenshots.dir");
        } else {
            URL = StringUtils.EMPTY;
            DEFAULT_TIMEOUT = -1;
            ALERT_TIMEOUT = -1;
            SCREENSHOTS_ENABLED = false;
            SCREENSHOTS_DIR = StringUtils.EMPTY;
        }
    }

    private Properties getProperties() throws IOException {
        Properties properties = new Properties();

        InputStream inputStream = this.getClass().getResourceAsStream(PROPERTIES_FILE_NAME);
        if(inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException(String.format("Property file '%s' not found in the classpath.",
                    PROPERTIES_FILE_NAME));
        }

        return properties;
    }

    public WebDriver getNewSession() {
        return new ChromeDriver();
    }

    public String getPropertiesFileName() {
        return PROPERTIES_FILE_NAME;
    }

    public String getUrl() {
        return URL;
    }

    public long getDefaultTimeout() {
        return DEFAULT_TIMEOUT;
    }

    public long getAlertTimeout() {
        return ALERT_TIMEOUT;
    }

    public boolean areScreenshotsEnabled() {
        return SCREENSHOTS_ENABLED;
    }

    public String getScreenshotsDir() {
        return SCREENSHOTS_DIR;
    }

}

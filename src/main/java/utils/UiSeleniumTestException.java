package utils;

public class UiSeleniumTestException extends Exception {

    public UiSeleniumTestException(String message, String... placeholders) {
        super(String.format(message, placeholders));
    }

}

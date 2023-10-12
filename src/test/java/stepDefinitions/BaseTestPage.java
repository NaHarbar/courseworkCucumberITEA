package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class BaseTestPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseTestPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, ofSeconds(30));
    }
}

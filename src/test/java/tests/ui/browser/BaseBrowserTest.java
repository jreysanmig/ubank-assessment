package tests.ui.browser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.ConfigUtil;
import utils.DriverManager;

import java.util.Properties;

public class BaseBrowserTest {
    private WebDriver driver;

    protected HomePage homePage;

    @BeforeAll
    public static void setSystemProperties() {
        ConfigUtil.loadProperties();
    }

    @BeforeEach
    public void setup() {
        driver = new DriverManager().getDriver();
        driver.get(System.getProperty("baseUrl.ui.browser"));
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @AfterEach
    public void teatDown() {
        driver.quit();
    }
}

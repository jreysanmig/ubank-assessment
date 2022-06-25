package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private WebDriver driver;

    public WebDriver getDriver() {
        String browser = System.getProperty("browser");
        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            case "edge":
                System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
                driver = new EdgeDriver();
                break;

            case "chrome":
            default:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }

        return driver;
    }
}

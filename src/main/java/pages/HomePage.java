package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By signInErrorMessage = By.cssSelector(".alert-content");
    private By signInBtn = By.cssSelector(".sign-in-form__submit-button");
    private By signInEmailOrPhoneTxtBox = By.id("session_key");
    private By signInPasswordTxtBox = By.id("session_password");

    public HomePage clickSignIn() {
        driver.findElement(signInBtn).click();
        return this;
    }

    public boolean signInButtonIsPresent() {
        try {
            driver.findElement(signInBtn);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getSignInErrorMessage() {
        return driver.findElement(signInErrorMessage).getText();
    }

    public HomePage enterEmailOrPhone(String inputTxt) {
        WebElement emailOrPhoneTxtBox = driver.findElement(signInEmailOrPhoneTxtBox);
        emailOrPhoneTxtBox.clear();
        emailOrPhoneTxtBox.sendKeys(inputTxt);
        return this;
    }

    public HomePage enterPassword(String inputTxt) {
        WebElement passwordTxtBox = driver.findElement(signInPasswordTxtBox);
        passwordTxtBox.clear();
        passwordTxtBox.sendKeys(inputTxt);
        return this;
    }

    public void signInWithCredentials(String emailOrPhoneTxt, String passwordTxt) {
        enterEmailOrPhone(emailOrPhoneTxt);
        enterPassword(passwordTxt);
        clickSignIn();
    }

}

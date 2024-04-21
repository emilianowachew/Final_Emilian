package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Pattern;

public class RegistrationPage {
    public static final String REGISTRATION_URL = "http://training.skillo-bg.com:4200/users/register";
    private final WebDriver webDriver;

    public RegistrationPage(WebDriver driver) {
        this.webDriver = driver;
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlMatches(Pattern.quote(REGISTRATION_URL)));
    }


    public void fillInUserName(String username) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        WebElement usernameTextField = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.name("username"))));
        usernameTextField.sendKeys(username);
    }

    public void fillInEmail(String email) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        WebElement passwordTextField = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("/html/body/app-root/div[2]/app-register/div/div/form/div[2]/input"))));
        passwordTextField.sendKeys(email);
    }

    public void fillInPassword(String password) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        WebElement passwordTextField = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("defaultRegisterFormPassword"))));
        passwordTextField.sendKeys(password);
    }

    public void fillInConfirmPassword(String confirmpassword) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        WebElement passwordTextField = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("defaultRegisterPhonePassword"))));
        passwordTextField.sendKeys(confirmpassword);
    }

    public void clickSignIn() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.id("sign-in-button"))));
        signInButton.click();
    }

        public void navigateTo () {
            this.webDriver.get(REGISTRATION_URL);
        }
}



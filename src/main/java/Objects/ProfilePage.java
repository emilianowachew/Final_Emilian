package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    public static final String PROFILE_URL = "http://training.skillo-bg.com:4200/users/";
    private final WebDriver webDriver;

    public ProfilePage(WebDriver driver) {
        this.webDriver = driver;
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlContains(PROFILE_URL));
    }

    public boolean isUrlLoaded(String userId) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(PROFILE_URL + userId));
    }

    public boolean isPictureElementLoaded() {
        WebElement pictureElement = webDriver.findElement(By.xpath("//i[contains(@class, 'fas fa-user-edit')]"));
        return pictureElement.isDisplayed();
    }
}


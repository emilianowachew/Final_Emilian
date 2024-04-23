package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public static final String HOME_URL = "http://training.skillo-bg.com:4200/posts/all";
    private final WebDriver webDriver;

    public HomePage(WebDriver driver) {
        this.webDriver = driver;
    }

    public void navigateTo() {
        this.webDriver.get(HOME_URL);
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(HOME_URL));
    }

    public void likeElement() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        WebElement likeButtonHeart = webDriver.findElement(By.cssSelector("app-post-detail i.far.fa-heart"));
        wait.until(ExpectedConditions.elementToBeClickable(likeButtonHeart));
        likeButtonHeart.click();
    }
    public boolean isElementLiked() {
        WebElement likeButtonHeart = webDriver.findElement(By.cssSelector("app-post-detail i.far.fa-heart"));
        return likeButtonHeart.isDisplayed();
    }

    public void followElement() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        WebElement followButtonElement = webDriver.findElement(By.xpath("/html/body/app-root/div[2]/app-all-posts/div/div/div[1]/app-post-detail/div/app-small-user-profile/div/div[2]/button"));
        wait.until(ExpectedConditions.elementToBeClickable(followButtonElement));
        followButtonElement.click();
    }
    public boolean isElementFollowed() {
        WebElement followButtonElement = webDriver.findElement(By.xpath("/html/body/app-root/div[2]/app-all-posts/div/div/div[1]/app-post-detail/div/app-small-user-profile/div/div[2]/button"));
        return followButtonElement.isDisplayed();
    }
}
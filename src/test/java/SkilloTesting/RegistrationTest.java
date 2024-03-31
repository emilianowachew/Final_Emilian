package SkilloTesting;

import Objects.*;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class RegistrationTest {

    ChromeDriver webDriver;
    private boolean userReg = false;

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        if (webDriver != null) {
            webDriver.close();
        }
        if (userReg == true) {
        }


    }

    @DataProvider(name = "userData")
    public static Object[][] getUserData() {
        Faker faker = new Faker();

        String UserName = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String confirmPassword = password;

        return new Object[][]{
                {UserName, email, password, confirmPassword}
        };
    }
    @Test(dataProvider = "userData")
    public void RegisterTest(String Username, String email, String password, String confirmPassword){

        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        HomePage homePage = new HomePage(webDriver);

        registrationPage.navigateTo();
        Assert.assertTrue(registrationPage.isUrlLoaded());
        registrationPage.fillInUserName(Username);
        registrationPage.fillInEmail(email);
        registrationPage.fillInPassword(password);
        registrationPage.fillInConfirmPassword(confirmPassword);
        registrationPage.clickSignIn();

        Assert.assertTrue(homePage.isUrlLoaded(), "Current page is not homepage.");

    }
}


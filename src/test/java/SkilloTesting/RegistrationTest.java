package SkilloTesting;

import Objects.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationTest extends DriverObject {

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
        WebDriver driver = super.getWebDriver();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        HomePage homePage = new HomePage(driver);
        registrationPage.navigateTo();
        Assert.assertTrue(registrationPage.isUrlLoaded());
        registrationPage.isSignInButtonLoaded();
        Assert.assertTrue(registrationPage.isSignInButtonLoaded(),"The sign in button is not loaded");
        registrationPage.fillInUserName(Username);
        registrationPage.fillInEmail(email);
        registrationPage.fillInPassword(password);
        registrationPage.fillInConfirmPassword(confirmPassword);
        registrationPage.clickSignIn();

        Assert.assertTrue(homePage.isUrlLoaded(), "Current page is not homepage.");
    }
}



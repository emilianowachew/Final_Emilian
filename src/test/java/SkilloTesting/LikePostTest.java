package SkilloTesting;

import Objects.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LikePostTest extends DriverObject {

    @DataProvider(name = "getUser")
    public Object[][] getUsers() {
        return new Object[][]{
                {"emilian", "123456"},
        };
    }

    @Test(dataProvider = "getUser")
    public void loginTest(String username, String password) {
        WebDriver driver = super.getWebDriver();
        HomePage homePage = new HomePage(driver);
        Header header = new Header(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Home page is not loaded");

        header.clickLogin();

        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");
        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);

        loginPage.checkRememberMe();
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is not checked.");

        loginPage.clickSignIn();

        header.clickHome();

        Assert.assertTrue(homePage.isUrlLoaded(), "Current page is not home page");

        homePage.likeElement();

        homePage.isElementLiked();
        Assert.assertTrue(homePage.isElementLiked(), "The element is not liked");
    }
}
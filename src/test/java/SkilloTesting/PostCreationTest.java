package SkilloTesting;

import Objects.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;


public class PostCreationTest extends DriverObject {

    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        File postPicture = new File("src/main/resources/picture1.jpg");
        String caption = "Upload made by the automated test of Emilian";
        return new Object[][]{
                {"emilian","123456", "5488", postPicture, caption},
        };
    }
    @Test(dataProvider = "getUser")
    public void testCreatePost(String username, String password, String userId, File postPicture, String caption){
        WebDriver driver = super.getWebDriver();
        HomePage homePage = new HomePage(driver);
        Header header = new Header(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        PostPage postPage = new PostPage(driver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Home page is not loaded");
        header.clickLogin();

        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");
        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);

        loginPage.checkRememberMe();
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is not checked.");

        loginPage.clickSignIn();

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");

        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not profile page");

        header.clickNewPost();
        Assert.assertTrue(postPage.isNewPostLoaded(), "The new post form is not loaded");

        postPage.uploadPicture(postPicture);
        String actualImageText = postPage.uploadedImageText();
        Assert.assertTrue(postPage.isImageUploaded("picture1.jpg"), "Image is not uploaded");
        Assert.assertEquals(actualImageText, "picture1.jpg", "Incorrect image is uploaded");

        postPage.typePostCaption(caption);
        postPage.clickCreatePost();

        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");

        profilePage.isPictureElementLoaded();
        Assert.assertTrue(profilePage.isPictureElementLoaded(),"The  picture element is not loaded");
    }
}

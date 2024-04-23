package SkilloTesting;

import Objects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class PostCreationTest{
    ChromeDriver webDriver;
    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterMethod(alwaysRun = true)
    public void afterTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result);
            System.out.println("Screenshot taken for failed test: " + result.getName());
        }
        if (webDriver != null) {
            webDriver.quit();
        }
    }
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
        WebDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        PostPage postPage = new PostPage(driver);
        HomePage homePage = new HomePage(driver);
        Header header = new Header(driver);

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


    }
    private void takeScreenshot(ITestResult testResult) {
        System.out.println("Taking screenshot...");
        if (testResult.getStatus() != ITestResult.SUCCESS) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
                java.io.File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String testName = testResult.getName();
                String SCREENSHOTS_DIR = "src/test/java/SkilloTesting/SCREENSHOTS/";
                FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpeg")));
            } catch (IOException e) {
                System.out.println("Unable to create a screenshot file: " + e.getMessage());
            }
        }
    }
}

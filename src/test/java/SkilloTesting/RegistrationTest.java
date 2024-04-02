package SkilloTesting;

import Objects.*;
import com.github.javafaker.Faker;
import com.github.javafaker.File;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
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
    private void takeScreenshot(ITestResult testResult){
        if(ITestResult.FAILURE == testResult.getStatus()){
            try{
                TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
                File screeshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String testName = testResult.getName();
                FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg")));
            }catch (IOException e){
                System.out.println("Unable to create a screenshot file: " + e.getMessage());
            }
        }
    }
    }



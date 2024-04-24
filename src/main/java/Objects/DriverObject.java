package Objects;

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
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class DriverObject {
    public static final String SCREENSHOTS_DIR = "src/test/java/SkilloTesting/SCREENSHOTS/";
    private WebDriver webDriver;
    @BeforeSuite
    protected final void setupTestSuite() throws  IOException{
        cleanDirectory(SCREENSHOTS_DIR);
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTest(){
        this.webDriver = new ChromeDriver();
        this.webDriver.manage().window().maximize();
        this.webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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


    protected WebDriver getWebDriver(){
        return webDriver;
    }

    private void cleanDirectory(String directoryPath) throws IOException{
        File directory = new File(directoryPath);
        Assert.assertTrue(directory.isDirectory(), "Invalid directory!");

        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0){
            System.out.printf("All file are deleted in Directory: %s%n", directoryPath);
        }else {
            System.out.printf("Unable to delete the files in Directory: %s%n", directoryPath);
        }
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

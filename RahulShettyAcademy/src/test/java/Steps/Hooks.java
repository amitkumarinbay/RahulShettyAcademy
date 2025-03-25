package Steps;

import java.time.Duration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

    public static WebDriver driver;

    
    @Before
    public void setup() {
        try {
            // Setting up ChromeDriver using WebDriverManager
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            // Configuration Options for Chrome
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--force-device-scale-factor=0.5");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            System.out.println("Browser launched successfully.");
        } catch (Exception e) {
            System.err.println("Error occurred while launching the browser: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                // Capture Screenshot if Scenario Fails
                TakesScreenshot ts = (TakesScreenshot) driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                String screenshotName = scenario.getName().replaceAll(" ", "_");
                scenario.attach(screenshot, "image/png", screenshotName);
                System.out.println("Screenshot captured for failed scenario: " + scenario.getName());
            }
        } catch (Exception e) {
            System.err.println("Error while capturing screenshot: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed successfully.");
            }
        }
    }
}

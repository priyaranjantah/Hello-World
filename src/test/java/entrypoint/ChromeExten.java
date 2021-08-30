package entrypoint;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.SQLOutput;
import java.util.HashMap;

import static org.testng.AssertJUnit.assertTrue;

public class ChromeExten {

    WebDriver driver;


    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        chromeOptions.addArguments("use-fake-device-for-media-stream");
        chromeOptions.addArguments("use-fake-ui-for-media-stream");
        chromeOptions.addArguments("--disable-notifications");
        //chromeOptions.addArguments("headless");
        //chromeOptions.addExtensions(new File("\\\\src\\test\\resources\\ChromeExtension\\1.6.8_0.crx"));
        chromeOptions.addArguments("--no-sandbox");
        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        chromeOptions.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver = new ChromeDriver(capabilities);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();

    }

    @Test
    public void testScript() throws InterruptedException {
        driver.get("https://gethighlight.com/spa/home/accounts");
        String title = driver.getTitle();
        Assert.assertEquals(title,"Highlight");
        Thread.sleep(10000);
    }

}

package entrypoint;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.testng.AssertJUnit.assertTrue;

public class ChromeExten {

    WebDriver driver;


    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //System.setProperty("webdriver.chrome.driver",
                //System.getProperty("user.dir") + "/src/test/resources/webdrivers/chromedriver.exe";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        chromeOptions.addArguments("use-fake-device-for-media-stream");
        chromeOptions.addArguments("use-fake-ui-for-media-stream");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
        chromeOptions.addArguments("disable-infobars"); // disabling infobars
        chromeOptions.addArguments("--disable-extensions"); // disabling extensions
        chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
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
        Assert.assertEquals(title, "Highlight");
        Thread.sleep(10000);
    }

    @Test
    public void testPE() throws InterruptedException {
        driver.get("https://d2uzsvar03k0o3.cloudfront.net/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String title = driver.getTitle();
        Assert.assertEquals(title, "AWS Summarizer");
        driver.findElement(By.id("clientName")).sendKeys("Sample Client");
        driver.findElement(By.id("arn")).sendKeys("arn:aws:iam::163806924483:role/vasavi-pe-test-role");
        driver.findElement(By.id("externalId")).sendKeys("vasavi-external-id");
        driver.findElement(By.id("accessKey")).sendKeys("kdu-dev-token");
        driver.findElement(By.xpath("//button[@class='btn btn-custom' and text()='Validate']")).click();
        driver.findElement(By.xpath("//div[contains(@class,'results')]//h3")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-custom' and contains(text(),'Report')]")).click();

        driver.findElement(By.xpath("//button[@class='btn btn-custom' and contains(text(),'Report')]")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//button[@class='btn btn-custom' and text()='Validate']")).click();
        Thread.sleep(10000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='result-viewer']")));
        WebElement table = driver.findElement(By.xpath("//div[@class='result-viewer']"));
        if (table.isDisplayed()) {
            List<WebElement> allhost = table.findElements(By.tagName("label"));
            int total_node = allhost.size();
                for (int i = 0; i < total_node; i++) {
                    String nodeName = allhost.get(i).getText();
                    System.out.println(nodeName);
            }
            //driver.findElement(By.xpath("//h3[contains(text(),'Results')]//..//label")).;
            //driver.findElement(By.xpath("//button[@class='btn btn-custom' and contains(text(),'Report')]")).click();
        }
    }


}

package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserOption {

    static Logger logger = Logger.getLogger(BrowserOption.class.getName()); // Make logger static

    public static WebDriver BrowserFactory(String browser)
    {
        logger= Logger.getLogger(BrowserOption.class.getName());
        logger.setLevel(Level.INFO);// we will get a clear view of the test flow and important events that occur during the test execution.

        switch (browser.toLowerCase())
        {
            case "firefox":
                FirefoxOptions Firefoxoptions = new FirefoxOptions();
                Firefoxoptions.addArguments("--start-maximized");
                logger.info("Running test in " + browser );
                return new FirefoxDriver(Firefoxoptions);
            case "chrome":
                ChromeOptions Chromeoptions = new ChromeOptions();
                Chromeoptions.addArguments("--start-maximized");
                logger.info("Running test in" + browser );

                return new ChromeDriver(Chromeoptions);
            default:
                FirefoxOptions Defaultoptions = new FirefoxOptions();
                Defaultoptions.addArguments("--start-maximized");
                logger.info("Running test in firefoxdriver as " + browser + "is not exist");
                return new FirefoxDriver(Defaultoptions);
        }
    }
}

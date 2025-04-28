package objects;


import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserFactory {
    private static final Logger logger = Logger.getLogger(BrowserFactory.class.getName());
    private static ThreadLocal<WebDriver> driverThreadLocal=new ThreadLocal<>();

    public static WebDriver  setDriverThreadLocal(String browserName) {
        WebDriver driver =getBrowser(browserName);
        driverThreadLocal.set(driver);
        return driverThreadLocal.get();
    }
    public static WebDriver getDriverThreadLocal() {
        return driverThreadLocal.get();
    }




    public static WebDriver getBrowser(String browser) {

        logger.setLevel(Level.INFO);// we will get a clear view of the test flow and important events that occur during the test execution.
        logger.info("Running test in " + browser);

        switch (browser.toLowerCase())
        {
            case "firefox":
                FirefoxOptions Firefoxoptions = new FirefoxOptions();
                Firefoxoptions.addArguments("--start-maximized");
                Firefoxoptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
               return new FirefoxDriver(Firefoxoptions);
            case "chrome":
                ChromeOptions Chromeoptions = new ChromeOptions();
                Chromeoptions.addArguments("--start-maximized");
                Chromeoptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                return new ChromeDriver(Chromeoptions);
            default:
                FirefoxOptions Defaultoptions = new FirefoxOptions();
                Defaultoptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                Defaultoptions.addArguments("--start-maximized");
                logger.info("Running test in firefoxdriver as " + browser + "is not exist");
                return new FirefoxDriver(Defaultoptions);
        }
    //    driver.manage().window().maximize();

    }
}
package jp.gr.java_conf.uzresk.selenium.tools.test;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import jp.gr.java_conf.uzresk.selenium.tools.config.Config;
import jp.gr.java_conf.uzresk.selenium.tools.log.SeleniumToolsLogger;

/**
 * exec maven mvn -Dtest=x.x.x.x.AllTests test
 */
public class SeleniumTestSuite {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {

        SeleniumToolsLogger.log("Selenium Test Start at " + LocalDateTime.now());

        FileUtils.cleanDirectory(new File(Config.screenshotPath()));

        String mode = Config.getMode();
        if ("local".equals(mode)) {
            driver = new InternetExplorerDriver(getCapabilities());
        } else if ("remote".equals(mode)) {
            // TODO
            //    capabilities.setBrowserName("internet explorer");
            //    capabilities.setVersion("11");
            driver = new RemoteWebDriver(new URL(Config.remoteProtocol(), Config.remoteHost(),
                    Config.remotePort(), Config.remotePath()), getCapabilities());
        } else {
            throw new IllegalArgumentException("mode, please choose local or remote. ");
        }

        driver.manage().timeouts().implicitlyWait(Config.implicitlyWaitSeconds(), TimeUnit.SECONDS);
    }

    private static Capabilities getCapabilities() {

        String driverName = System.getProperty("driver", "ie");
        DesiredCapabilities capabilities = null;

        if ("ie".equals(driverName)) {
            capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(
                    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability("requireWindowFocus", true);
            System.setProperty("webdriver.ie.driver", "./driver/IEDriverServer.exe");
        } else if ("chrome".equals(driverName)) {
            capabilities = DesiredCapabilities.chrome();
        } else if ("firefox".equals(driverName)) {
            capabilities = DesiredCapabilities.firefox();
        } else if ("safari".equals(driverName)) {
            capabilities = DesiredCapabilities.safari();
        } else {
            throw new IllegalArgumentException("mode, please choose ie, firefox, chrome, safari");
        }
        return capabilities;
    }

    @AfterClass
    public static void afterClass() throws Exception {

        try {
            driver.quit();
        } catch (Throwable e) {
            // nothing to do.
            e.printStackTrace();
        }
        SeleniumToolsLogger.log("Selenium Test End at " + LocalDateTime.now());
        SeleniumToolsLogger.log("------------------------------------------------------------");
    }

}

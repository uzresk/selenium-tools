package jp.gr.java_conf.uzresk.selenium.tools.test;

import java.io.File;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

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

		// TODO 複数Driver対応
		System.setProperty("webdriver.ie.driver", "./driver/IEDriverServer.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability("requireWindowFocus", true);
		driver = new InternetExplorerDriver(capabilities);
	}

	@AfterClass
	public static void afterClass() throws Exception {

		SeleniumToolsLogger.log("Selenium Test End at " + LocalDateTime.now());
		SeleniumToolsLogger.log("------------------------------------------------------------");
	}

}

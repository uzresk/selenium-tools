package jp.gr.java_conf.uzresk.selenium.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import jp.gr.java_conf.uzresk.selenium.tools.config.Config;
import jp.gr.java_conf.uzresk.selenium.tools.test.SeleniumTestSuite;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TOP {

	@Test
	public void 表示() throws Exception {

		WebDriver driver = SeleniumTestSuite.getDriver();

		driver.switchTo().frame("content");

		driver.findElement(By.name("userId")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin2006");

		// because button tag no name
		driver.findElement(By.name("password")).submit();

		new WebDriverWait(driver, Config.waitTimeoutInSeconds(), Config.waitSleepInMillis())
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						final WebElement element = driver.findElement(By.partialLinkText("積算書作成"));
						return element.isEnabled();
					}
				});

	}
}

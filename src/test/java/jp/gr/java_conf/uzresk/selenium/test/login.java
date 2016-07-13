package jp.gr.java_conf.uzresk.selenium.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import jp.gr.java_conf.uzresk.selenium.tools.config.Config;
import jp.gr.java_conf.uzresk.selenium.tools.test.SeleniumTestSuite;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class login {

	@Test
	public void disp() throws Exception {

		WebDriver driver = SeleniumTestSuite.getDriver();

		driver.get(Config.baseUrl());
	}
}

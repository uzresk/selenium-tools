package jp.gr.java_conf.uzresk.selenium.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import jp.gr.java_conf.uzresk.selenium.tools.test.SeleniumTestSuite;

/**
 * exec maven
 *
 * mvn -Dtest=jp.tokyometro.cims.selenium.test.CimsSeleniumSuite \
 * -DargLine=-javaagent:aspectjweaver-1.8.6.jar test
 */
@RunWith(Suite.class)
@SuiteClasses({ login.class, TOP.class })
public class SeleniumSuite extends SeleniumTestSuite {

}

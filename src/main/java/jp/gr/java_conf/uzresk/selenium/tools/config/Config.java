package jp.gr.java_conf.uzresk.selenium.tools.config;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.yaml.snakeyaml.Yaml;

public class Config {

	public static String baseUrl() {
		return value("selenium.baseurl");
	}

	public static String screenshotPath() {
		return value("selenium.screenshot_path");
	}

	public static int waitTimeoutInSeconds() {

		return intValue("selenium.wait.timeout_in_seconds");
	}

	public static int waitSleepInMillis() {
		return intValue("selenium.wait.sleep_in_millis");
	}

	/*----------------------------------------------------------------------------*/

	@SuppressWarnings("unchecked")
	private static Map<String, Map<String, Map<String, Object>>> configuration = (Map<String, Map<String, Map<String, Object>>>) new Yaml()
			.load(ClassLoader.getSystemResourceAsStream("config.yml"));

	public static String value(String key) {
		return Config.value(key, String.class);
	}

	@SuppressWarnings("unchecked")
	public static List<String> list(String key) {
		return Config.value(key, List.class);
	}

	public static int intValue(String key) {
		return Config.value(key, Integer.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> T value(String key, Class<T> returnType) {

		if (!key.contains(".")) {
			return (T) ConvertUtils.convert(configuration.get(key), returnType);
		}

		String[] keys = key.split("\\.");

		if (key == null || "".equals(key) || keys.length > 3) {
			throw new IllegalArgumentException("Illegal key [" + key + "]");
		}

		if (keys.length == 2) {
			return (T) ConvertUtils.convert(configuration.get(keys[0]).get(keys[1]), returnType);
		}

		return (T) ConvertUtils.convert(configuration.get(keys[0]).get(keys[1]).get(keys[2]), returnType);
	}

}

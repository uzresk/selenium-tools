package jp.gr.java_conf.uzresk.selenium.tools.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleniumToolsLogger {

	private static final Logger LOG = LoggerFactory.getLogger("jp.gr.java_conf.uzresk.selenium.tools");

	public static void log(Object message) {

		if(LOG.isInfoEnabled()) {
			LOG.info(message.toString());
		}
	}
}

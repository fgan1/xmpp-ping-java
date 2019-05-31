import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesManager {

	private static final Logger LOGGER = Logger.getLogger(PropertiesManager.class);

	private static Properties properties;

	public static void init(String path) throws IOException {
		String configurationPath = path;
		File configurationFile = new File(configurationPath);
		if (!configurationFile.exists()) {
			LOGGER.warn("Informed path to ping.conf file must be valid.");
			System.exit(1);
		}

		FileInputStream input = new FileInputStream(configurationFile);
		properties = new Properties();
		properties.load(input);
	}

	public static String getOwnXMPPComponentJid() {
		return properties.getProperty("xmpp_own_component_jid");
	}

	public static String getOwnXMPPComponentPassword() {
		return properties.getProperty("xmpp_own_component_password");
	}
	
	public static String getXMPPServerIp() {
		return properties.getProperty("xmpp_server_ip");
	}
	
	public static int getXMPPServerPort() {
		String xmppServerPortStr = properties.getProperty("xmpp_server_port");
		return Integer.parseInt(xmppServerPortStr);
	}	
	
	public static long getPeriodToPing() {
		String periodoToPingStr = properties.getProperty("period_to_ping");
		return Long.parseLong(periodoToPingStr);
	}		
	
	public static String getXMPPComponentToPing() {
		return properties.getProperty("xmpp_component_to_ping");
	}	
}

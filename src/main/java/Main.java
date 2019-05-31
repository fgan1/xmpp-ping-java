import java.io.IOException;

import org.apache.log4j.Logger;
import org.xmpp.component.ComponentException;

import xmpp.ManagerXmppComponent;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws IOException {		
		LOGGER.info("Starting ping");
		PropertiesManager.init(args[0]);
		
		String ownXMPPComponentJid = PropertiesManager.getOwnXMPPComponentJid();
		String ownXMPPComponentpassport = PropertiesManager.getOwnXMPPComponentPassword();
		String xmppServerIp = PropertiesManager.getXMPPServerIp();
		int xmppPort = PropertiesManager.getXMPPServerPort();
		long periodToPing = PropertiesManager.getPeriodToPing();
		String componentToPing = PropertiesManager.getXMPPComponentToPing();
		ManagerXmppComponent xmpp = new ManagerXmppComponent(ownXMPPComponentJid, 
				ownXMPPComponentpassport,
				xmppServerIp, 
				xmppPort, 
				periodToPing, 
				componentToPing);
		try {
			xmpp.connect();
			LOGGER.info("XMPP connected");
		} catch (ComponentException e) {
			LOGGER.error("Conflict in the initialization of xmpp component.", e);
			System.exit(1);
		}
	}
	
}

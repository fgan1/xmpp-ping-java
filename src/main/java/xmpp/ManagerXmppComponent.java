package xmpp;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.jamppa.component.AsyncPacketSender;
import org.jamppa.component.PacketSender;
import org.jamppa.component.XMPPComponent;
import org.xmpp.packet.IQ;
import org.xmpp.packet.IQ.Type;
import org.xmpp.packet.Packet;
import org.xmpp.packet.PacketError;

public class ManagerXmppComponent extends XMPPComponent implements AsyncPacketSender {

	private static final int DEFAULT_DELAY = 2000;
	private static final String QUERY_XMPP_PARAMETER = "query";
	public static final String PING_NAMESPACE = "http://xmpp-ping.org/ping";

	private final Timer timer = new Timer();
	
	private final static Logger LOGGER = Logger.getLogger(ManagerXmppComponent.class.getName());

	public ManagerXmppComponent(String jid, String password, String server, int port, long period, String address) {
		super(jid, password, server, port);
		
		addGetHandler(new PingHandler());
		
		schedulePing(period, address, this);
	}

	public static void ping(String address, PacketSender packetSender) throws Exception {
		if (packetSender == null) {
			throw new IllegalArgumentException("Packet sender not set");
		}
		IQ iq = new IQ(Type.get);
		iq.setTo(address);
		iq.getElement().addElement(QUERY_XMPP_PARAMETER, ManagerXmppComponent.PING_NAMESPACE);

		IQ response = (IQ) packetSender.syncSendPacket(iq);
		PacketError errorOnResponse = response != null ? response.getError() : null;
		if (response == null || errorOnResponse != null) {
			LOGGER.info(String.format("Ping to %s not ok.", address));
			LOGGER.error("Error while received the ping response: " + errorOnResponse.getText());
			return;
		}
		LOGGER.info(String.format("Ping to %s ok.", address));
	}

	private void schedulePing(final long period, final String address, final PacketSender packetSender) {		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					ping(address, packetSender);
				} catch (Exception e) {
					LOGGER.error("Error in the ping operation", e);
				}
			}
		}, DEFAULT_DELAY, period);
	}

	@Override
	protected void send(Packet packet) {
		packet.setFrom(getJID());
		LOGGER.debug("(sending IQ to " + packet.getTo() + ", packetId " + packet.getID() + ", XML " + packet.toXML());
		super.send(packet);
	}

}

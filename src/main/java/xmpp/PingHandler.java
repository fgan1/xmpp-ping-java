package xmpp;

import org.jamppa.component.handler.AbstractQueryHandler;
import org.xmpp.packet.IQ;

public class PingHandler extends AbstractQueryHandler {

	public PingHandler() {
		super(ManagerXmppComponent.PING_NAMESPACE);
	}

	public IQ handle(IQ query) {
		IQ response = IQ.createResultIQ(query);
		return response;
	}

}


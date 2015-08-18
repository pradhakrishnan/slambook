package org.slambook.application.service.websocket;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class SlamBookWebSocketHandler implements WebSocketHandler {

	Set<WebSocketSession> connectionList = new HashSet<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		connectionList.add(session);
	}

	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		String payLoad = message.getPayload().toString();
	}

	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		if(null != session)
			connectionList.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}

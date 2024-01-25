package com.iron.alltact.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iron.alltact.chat.model.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

	private final ObjectMapper mapper;
	private final Set<WebSocketSession> sessions = new HashSet<>();
	private final Map<Long, Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("{} is connected.", session.getId());
		sessions.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log.info("payload {}", payload);

		ChatMessageDTO chatMessageDto = mapper.readValue(payload, ChatMessageDTO.class);
		log.info("session {}", chatMessageDto.toString());

		Long chatRoomId = chatMessageDto.getChatRoomId();

		if (!chatRoomSessionMap.containsKey(chatRoomId)) {
			chatRoomSessionMap.put(chatRoomId, new HashSet<>());
		}
		Set<WebSocketSession> chatRoomSession = chatRoomSessionMap.get(chatRoomId);

		if (chatMessageDto.getMessageType().equals(ChatMessageDTO.MessageType.ENTER)) {
			chatRoomSession.add(session);
		}
		if (chatRoomSession.size() >= 3) {
			removeClosedSession(chatRoomSession);
		}
		sendMessageToChatRoom(chatMessageDto, chatRoomSession);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("{} is disconnected.", session.getId());
		sessions.remove(session);
	}

	private void sendMessageToChatRoom(ChatMessageDTO chatMessageDto, Set<WebSocketSession> chatRoomSession) {
		chatRoomSession.parallelStream().forEach(session -> sendMessage(session, chatMessageDto));
	}

	private void removeClosedSession(Set<WebSocketSession> chatRoomSession) {
		chatRoomSession.removeIf(sess -> !sessions.contains(sess));
	}

	public <T> void sendMessage(WebSocketSession session, T message) {
		try {
			session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}


}

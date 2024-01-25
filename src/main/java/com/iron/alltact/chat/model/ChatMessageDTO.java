package com.iron.alltact.chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {
	public enum MessageType {
		ENTER, TALK
	}

	private MessageType messageType;
	private Long chatRoomId;
	private Long senderId;
	private String message;
}

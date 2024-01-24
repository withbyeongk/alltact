package com.iron.alltact.member.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Member {

	private Long memberNo;
	private String email;
	private String password;
	private String name;
	private LocalDateTime createdTime;

}

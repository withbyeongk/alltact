package com.iron.alltact.member.dto;

import com.iron.alltact.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.NONE)
public class AddMemberDTO {


	private String email;
	private String password;
	private String name;

	public Member toEntity() {
		Member.MemberBuilder memberBuilder = Member.builder();
		return memberBuilder.email(email).password(password).name(name).build();
	}
}

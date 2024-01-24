package com.iron.alltact.member.service;

import com.iron.alltact.member.domain.Member;
import com.iron.alltact.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;


	public Long addMember(Member newMember) {
		return memberRepository.addMember(newMember);
	}
}

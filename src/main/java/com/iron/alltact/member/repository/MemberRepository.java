package com.iron.alltact.member.repository;

import com.iron.alltact.member.domain.Member;
import com.iron.alltact.mybatis.mappers.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final MemberMapper memberMapper;

	public Long addMember(Member newMember) {
		return memberMapper.addMember(newMember);
	}
}

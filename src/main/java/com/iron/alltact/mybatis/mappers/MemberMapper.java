package com.iron.alltact.mybatis.mappers;

import com.iron.alltact.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	Long addMember(Member newMember);

}

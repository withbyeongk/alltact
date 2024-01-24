package com.iron.alltact.member.controller;

import com.iron.alltact.member.domain.Member;
import com.iron.alltact.member.dto.AddMemberDTO;
import com.iron.alltact.member.dto.ResponseDTO;
import com.iron.alltact.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> addMember(AddMemberDTO memberDTO
			, HttpServletRequest request) {

		ResponseDTO.ResponseDTOBuilder responseDTOBuilder = ResponseDTO.builder();

		// email 중복 검사

		// 등록
		Member newMember = memberDTO.toEntity();
		log.debug("request add member ---");
		log.debug("member = {}", newMember.toString());
		Long memberNo = memberService.addMember(newMember);
		log.debug("no = {}", memberNo);

		responseDTOBuilder.code("200").message("success add member id : " + memberNo);

		return ResponseEntity.ok(responseDTOBuilder.build());
	}
}



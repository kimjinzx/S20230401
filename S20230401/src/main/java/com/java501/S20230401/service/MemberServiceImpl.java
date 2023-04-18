package com.java501.S20230401.service;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.MemberDao;
import com.java501.S20230401.model.Member;
import com.java501.S20230401.util.MemberSearchKeyword;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberDao md;
	
	@Override
	public Member getMember(String keyword, MemberSearchKeyword type) {
		Member member = null;
		switch(type) {
			case USERNAME: member = md.findByMemberUsername(keyword); break;
			case EMAIL: member = md.findByMemberEmail(keyword); break;
			case NICKNAME: member = md.findByMemberNickname(keyword); break;
			default: break;
		}
		return member;
	}
	
	@Override
	public int registMember(Member member) {
		return md.registMember(member);
	}
}

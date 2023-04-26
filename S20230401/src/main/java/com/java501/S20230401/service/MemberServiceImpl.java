package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.MemberDao;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Member;
import com.java501.S20230401.util.MemberSearchKeyword;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberDao md;
	
	// 양동균
	@Override
	public List<Member> allMemberList(Article article) {
		return md.allMemberList(article);
	}


	
	// 유현규
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
	@Override
	public Member getMemberById(int mem_id) {
		return md.getMemberById(mem_id);
	}
	@Override
	public void setAuthority(Integer mem_id, int authority) {
		md.setAuthority(mem_id, authority);
	}
	
	

}

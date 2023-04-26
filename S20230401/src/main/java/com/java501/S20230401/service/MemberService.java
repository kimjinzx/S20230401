package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Member;
import com.java501.S20230401.util.MemberSearchKeyword;

public interface MemberService {

	// 양동균
	List<Member> allMemberList(Article article);


	// 유현규
	public Member getMember(String keyword, MemberSearchKeyword type);
	public int registMember(Member member);
	public Member getMemberById(int mem_id);
	public void setAuthority(Integer mem_id, int authority);

}

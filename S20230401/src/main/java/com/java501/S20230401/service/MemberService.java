package com.java501.S20230401.service;

import com.java501.S20230401.model.Member;
import com.java501.S20230401.util.MemberSearchKeyword;

public interface MemberService {
	public Member getMember(String keyword, MemberSearchKeyword type);
	public int registMember(Member member);
}

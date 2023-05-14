package com.java501.S20230401.service;

import com.java501.S20230401.model.Member;
import com.java501.S20230401.util.MemberSearchKeyword;

public interface MemberService {
	// 양동균
	int dgReportMember(Member member); // 신고
	Integer dgCheckUser(String mem_username); // 유저 체크

	// 유현규
	public Member getMember(String keyword, MemberSearchKeyword type);
	public int registMember(Member member);
	public Member getMemberById(int mem_id);
	public void setAuthority(Integer mem_id, int authority);


}

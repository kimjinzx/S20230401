package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Member;
import com.java501.S20230401.model.MemberInfo;

@Repository
public interface MemberDao {

	List<Member> allMemberList(Article article);

	// 로그인
	public Member findByMemberUsername(String username);
	public Member findByMemberEmail(String email);
	public Member findByMemberNickname(String nickname);
	public MemberInfo findByMemberInfoName(String username);
	public int registMember(Member member);
	public Member getMemberById(int mem_id);
	public void setAuthority(Integer mem_id, int authority);

}

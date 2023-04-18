package com.java501.S20230401.dao;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Member;
import com.java501.S20230401.model.MemberInfo;

public interface MemberDao {
	public Member findByMemberUsername(String username);
	public Member findByMemberEmail(String email);
	public Member findByMemberNickname(String nickname);
	public MemberInfo findByMemberInfoName(String username);
}

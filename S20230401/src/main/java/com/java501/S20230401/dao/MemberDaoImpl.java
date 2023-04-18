package com.java501.S20230401.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Member;
import com.java501.S20230401.model.MemberInfo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDaoImpl implements MemberDao {
	private final SqlSession session;
	
	@Override
	public Member findByMemberUsername(String username) {
		return session.selectOne("hgGetMemberByUsername", username);
	}
	
	@Override
	public Member findByMemberEmail(String email) {
		return session.selectOne("hgGetMemberByEmail", email);
	}
	
	@Override
	public Member findByMemberNickname(String nickname) {
		return session.selectOne("hgGetMemberByNickname", nickname);
	}
	
	@Override
	public MemberInfo findByMemberInfoName(String username) {
		return session.selectOne("hgGetMemberInfoByUsername", username);
	}
}

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
	
	
	// 양동균
	@Override
	public int dgReportMember(Member member) {
		int result = 0;
		try {
			result = session.update("dgReportMember", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	// 유현규
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
	@Override
	public int registMember(Member member) {
		return session.insert("hgRegistMember", member);
	}
	@Override
	public Member getMemberById(int mem_id) {
		return session.selectOne("hgGetMemberById", mem_id);
	}
	@Override
	public void setAuthority(Integer mem_id, int authority) {
		Member member = new Member();
		member.setMem_id(mem_id);
		member.setMem_authority(authority);
		session.update("hgSetAuthority", member);
	}



}

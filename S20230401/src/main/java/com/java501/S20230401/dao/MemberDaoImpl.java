package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Member;
import com.java501.S20230401.model.MemberInfo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDaoImpl implements MemberDao {
	private final SqlSession session;
	
	
	// 양동균
	@Override
	public List<Member> allMemberList(Article article) {
		List<Member> allMemberList = null;
		try {
			allMemberList = session.selectList("dgAllMemberList", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allMemberList;
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

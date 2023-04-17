package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDaoImpl implements MemberDao {
	private final SqlSession session;
	
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

}

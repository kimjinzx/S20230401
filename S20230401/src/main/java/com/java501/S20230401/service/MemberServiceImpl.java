package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.MemberDao;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberDao memberDao;
	
	@Override
	public List<Member> allMemberList(Article article) {
		return memberDao.allMemberList(article);
	}

}

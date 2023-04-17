package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Member;

@Repository
public interface MemberDao {

	List<Member> allMemberList(Article article);

}

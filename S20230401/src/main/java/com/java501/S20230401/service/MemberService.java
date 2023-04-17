package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Member;

public interface MemberService {

	List<Member> allMemberList(Article article);

}

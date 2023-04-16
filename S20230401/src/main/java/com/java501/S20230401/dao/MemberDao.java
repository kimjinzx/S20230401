package com.java501.S20230401.dao;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Member;

public interface MemberDao {

	public Member findByMemberName(String username);

}

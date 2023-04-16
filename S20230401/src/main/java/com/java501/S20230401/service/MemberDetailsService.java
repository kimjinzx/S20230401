package com.java501.S20230401.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.MemberDao;
import com.java501.S20230401.model.Member;
import com.java501.S20230401.model.MemberDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
	private final MemberDao md;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = md.findByMemberName(username);
		if (member != null) return new MemberDetails(member);
		return null;
	}
}

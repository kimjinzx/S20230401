package com.java501.S20230401.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MemberDetails implements UserDetails {
	private final Member member;
	
	public MemberDetails(Member member) { this.member = member; }
	
	@Override
	public String getPassword() {
		return member.getMem_password();
	}
	
	@Override
	public String getUsername() {
		return member.getMem_username();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return null;
			}
		});
		return collect;
	}
}

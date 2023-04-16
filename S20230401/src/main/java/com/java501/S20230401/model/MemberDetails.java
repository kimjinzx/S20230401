package com.java501.S20230401.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.java501.S20230401.dao.CommDao;

import lombok.Data;

@Data
public class MemberDetails implements UserDetails {
	private final Member member;
	@Autowired
	private CommDao cd;
	
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
		collect.add(() -> cd.getValueById(member.getMem_authority()));
		return collect;
	}
	
	@Override
	public boolean isEnabled() {
		return member.getIsdelete() == 0;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}

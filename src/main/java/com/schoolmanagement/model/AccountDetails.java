package com.schoolmanagement.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountDetails implements UserDetails {

	private User user;

	public AccountDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	public String getFullName() {
		return this.user.getFullName();
	}

	public boolean hasRole (String roleName) {
		return this.user.hasRole(roleName);
	}

	public Class getAClass() {
		return this.user.getUserInfo().getAClass();
	}

	public Set<Role> getRole () {
		return this.user.getRoles();
	}

	public String getImage() {
		return this.user.getUserImagePath();
	}

	public Integer getId () {
		return this.user.getId();
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

	@Override
	public boolean isEnabled() {
		for (Role role : this.user.getRoles()) {
			if (!role.getRoleName().equalsIgnoreCase("STUDENT")) {
				return !user.getUserInfo().getDeleted();
			}
		}
		return true;
	}
}

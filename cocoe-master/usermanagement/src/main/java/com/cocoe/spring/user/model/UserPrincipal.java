package com.cocoe.spring.user.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private User user;
	
	 
	public UserPrincipal(User user) {
	this.user=user;	
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return getAuthorities(this.user.getRoles());
			
	}

	@Override
	public String getPassword() {
		
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return this.user.getIsNotLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return this.user.getIsActive();
	}
	
	  private Collection<? extends GrantedAuthority> getAuthorities(
		      Collection<Role> roles) {
		 
		        return getGrantedAuthorities(getPrivileges(roles));
		    }
		 
		    private List<String> getPrivileges(Collection<Role> roles) {
		 
		        List<String> privileges = new ArrayList<>();
		        List<Privilege> collection = new ArrayList<>();
		        for (Role role : roles) {
		            collection.addAll(role.getPrivileges());
		        }
		        for (Privilege item : collection) {
		            privileges.add(item.getName());
		        }
		        return privileges;
		    }
		 
		    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		        List<GrantedAuthority> authorities = new ArrayList<>();
		        for (String privilege : privileges) {
		            authorities.add(new SimpleGrantedAuthority(privilege));
		        }
		        return authorities;
		    }

}

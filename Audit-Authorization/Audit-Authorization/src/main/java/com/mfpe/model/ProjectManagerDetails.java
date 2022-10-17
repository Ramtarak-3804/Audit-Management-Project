package com.mfpe.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mfpe.exception.ExcludeFromJacocoGeneratedReport;

import io.swagger.annotations.ApiModel;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@ApiModel(value = "Project Manager Details getRoles and check Info about users")
public class ProjectManagerDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private int id;
	private String name;
	private String username;
	private String password;
	
	public ProjectManagerDetails(ProjectManager projectManager) {
		this.id = projectManager.getId();
		this.name = projectManager.getName();
		this.username = projectManager.getUsername();
		this.password = new BCryptPasswordEncoder(10).encode(projectManager.getPassword());
	}
	
	public String getName() {
		return this.name;
	}
	
	@ExcludeFromJacocoGeneratedReport
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@ExcludeFromJacocoGeneratedReport
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@ExcludeFromJacocoGeneratedReport
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@ExcludeFromJacocoGeneratedReport
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@ExcludeFromJacocoGeneratedReport
	@Override
	public boolean isEnabled() {
		return true;
	}

}

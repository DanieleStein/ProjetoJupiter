package br.com.projetojupiter.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.projetojupiter.model.Criador;

public class CriadorDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;
	

	public CriadorDetails(Criador criador) {
		this.email = criador.getEmail();
		this.senha = criador.getSenha();
	}

	public CriadorDetails() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
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
		return true;
	}

}

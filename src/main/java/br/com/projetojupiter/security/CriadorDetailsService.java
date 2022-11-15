package br.com.projetojupiter.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.projetojupiter.model.Criador;
import br.com.projetojupiter.repository.CriadorRepository;

@Service
public class CriadorDetailsService implements UserDetailsService {
	
	@Autowired
	private CriadorRepository criadorRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		 Optional<Criador> criador = criadorRepository.findByEmail(email);
		 
		 if(criador.isPresent()) {
			 return new CriadorDetails(criador.get());
		 } else {
			 throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		 }
	}
}

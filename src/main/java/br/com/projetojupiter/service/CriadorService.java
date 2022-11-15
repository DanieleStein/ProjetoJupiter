package br.com.projetojupiter.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetojupiter.model.Criador;
import br.com.projetojupiter.model.CriadorLogin;
import br.com.projetojupiter.repository.CriadorRepository;

@Service
public class CriadorService {
	
	@Autowired
	private CriadorRepository repository;
	
	public Criador cadastrarCriador(Criador criador) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(criador.getSenha());
		criador.setSenha(senhaEncoder);
		return repository.save(criador);
	}
	
	public Optional<CriadorLogin> logarCriador(Optional<CriadorLogin> criad) {
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		 Optional<Criador> criador = repository.findByEmail(criad.get().getEmail());
		 
		 if(criador.isPresent()) {
			 if(encoder.matches(criad.get().getSenha(), criador.get().getSenha())) {
				 String auth = criad.get().getEmail() + ":" + criad.get().getSenha();
				 
				 byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				 
				 String authHeader = "Basic " + new String(encodedAuth);
				 
				 criad.get().setToken(authHeader);
				 
				 return criad;
			 }
		 }
		 return null;
	}
}

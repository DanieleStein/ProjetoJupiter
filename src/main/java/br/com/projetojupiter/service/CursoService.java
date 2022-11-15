package br.com.projetojupiter.service;


import br.com.projetojupiter.model.Curso;
import br.com.projetojupiter.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getAll() {
        return cursoRepository.findAll();
    }



}

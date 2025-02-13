package com.uniovi.sdi2425211spring.services;


import com.uniovi.sdi2425211spring.entities.Professor;
import javax.annotation.PostConstruct;

import com.uniovi.sdi2425211spring.entities.User;
import com.uniovi.sdi2425211spring.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProfessorService {

    private final List<Professor> professorList = new LinkedList<>();

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @PostConstruct
    public void init() {
        if(professorRepository.count() == 0) {
            professorRepository.save(new Professor(1L,"4324","Manolo", "Suarez", "Ayudante"));
            professorRepository.save(new Professor(2L,"528974","Luca", "Doncic", "Profesor EF"));
        }
    }

    public List<Professor> getProfessorList() {
        return (List<Professor>) professorRepository.findAll();
    }
    public Professor getProfessor(Long id) {
        // Buscar el profesor por su ID en la base de datos
        return professorRepository.findById(id).orElse(null);
    }

    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }

    public void addProfessor(Professor professor) {
        // Guardar o actualizar el profesor en la base de datos
        professorRepository.save(professor);
    }
    public Professor getProfessorByDni(String dni) {
        return professorRepository.findByDni(dni);
    }
}

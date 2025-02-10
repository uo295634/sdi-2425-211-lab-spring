package com.uniovi.sdi2425211spring.services;


import com.uniovi.sdi2425211spring.entities.Professor;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProfessorService {
    private final List<Professor> professorList = new LinkedList<>();

    @PostConstruct
    public void init() {
        professorList.add(new Professor(1L,"4324","Manolo", "Suarez", "Ayudante"));
        professorList.add(new Professor(2L,"528974","Luca", "Doncic", "Profesor EF"));
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }
    public Professor getProfessor(Long id) {
        return professorList.stream()
                .filter(professor -> professor.getId().equals(id)).findFirst().get();
    }

    public void deleteProfessor(Long id) {
        professorList.removeIf(professor->professor.getId().equals(id));
    }

    public void addProfessor(Professor professor) {
        if (professor.getId() == null) {
            professor.setId(professorList.get(professorList.size() - 1).getId() + 1);
        }
        professorList.add(professor);
    }



}

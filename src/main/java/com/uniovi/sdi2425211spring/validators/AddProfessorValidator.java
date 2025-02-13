package com.uniovi.sdi2425211spring.validators;


import com.uniovi.sdi2425211spring.entities.Professor;
import com.uniovi.sdi2425211spring.services.ProfessorService;
import com.uniovi.sdi2425211spring.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;
@Component
public class AddProfessorValidator implements Validator {
    private final ProfessorService professorService ;
    public AddProfessorValidator(ProfessorService professorService) {
        this.professorService = professorService;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return Professor.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Professor prof = (Professor) target;
        String dni = prof.getDni();
        if (dni.length()!=9 ) {
            errors.rejectValue("dni", "Error.addProfessor.dni.length");}
        if (professorService.getProfessorByDni(dni) != null) {
            errors.rejectValue("dni", "Error.addProfessor.dni.duplicate");}
        if(prof.getName().isEmpty()){
            errors.rejectValue("name", "Error.addProfessor.name.empty");
        }
        if (prof.getSurname().isEmpty()){
            errors.rejectValue("surname", "Error.addProfessor.surname");
        }
    }
}

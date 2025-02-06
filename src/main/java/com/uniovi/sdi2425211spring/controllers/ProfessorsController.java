package com.uniovi.sdi2425211spring.controllers;

import com.uniovi.sdi2425211spring.entities.Professor;
import com.uniovi.sdi2425211spring.services.MarksService;
import com.uniovi.sdi2425211spring.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProfessorsController {
    @Autowired //Inyectar el servicio
    private ProfessorService professorService;
    @Autowired
    private MarksService marksService;

    @RequestMapping("/professor/add")
    public String setProfessor() {
        return "Añadir profesor";
    }
    @RequestMapping("/professor/list")
    public String getList() {
        return professorService.getProfessorList().toString();
    }
    @RequestMapping("/professor/details")
    public String getDetail(@RequestParam Long id) {
        return professorService.getProfessor(id).toString();
    }
    @RequestMapping("/professor/edit/{id}")
    public String getEdit(@PathVariable Long id) {
        return "Editando profesor ->" + id;
    }
    @RequestMapping("/professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return "Borrado profesor ->" + id;
    }

    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String addProfessor(@ModelAttribute Professor professor) {
        professorService.addProfessor(professor);
        return "Ok";
    }

    @RequestMapping(value = "/professor/edit", method = RequestMethod.POST)
    public String editProfessor() {
        return "Editando profesor";
    }


}

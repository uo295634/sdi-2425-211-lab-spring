package com.uniovi.sdi2425211spring.controllers;

import com.uniovi.sdi2425211spring.entities.Professor;
import com.uniovi.sdi2425211spring.entities.User;
import com.uniovi.sdi2425211spring.services.MarksService;
import com.uniovi.sdi2425211spring.services.ProfessorService;
import com.uniovi.sdi2425211spring.validators.AddProfessorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class ProfessorsController {
    private final AddProfessorValidator addProfessorValidator;
    @Autowired //Inyectar el servicio
    private ProfessorService professorService;

    public ProfessorsController(AddProfessorValidator addProfessorValidator) {
        this.addProfessorValidator = addProfessorValidator;
    }

    @RequestMapping(value = "/professor/add")
    public String setProfessor(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor/add";
    }
    @RequestMapping("/professor/list")
    public String getList(Model profesorModel) {
        profesorModel.addAttribute("professorList", professorService.getProfessorList());
        return "/professor/list";
    }
    @RequestMapping("/professor/details")
    public String getDetail(Model profesorModel, @RequestParam Long id) {
        profesorModel.addAttribute("professor", professorService.getProfessor(id));
        return "professor/details";
    }
    @RequestMapping("/professor/edit/{id}")
    public String editProfessor(@PathVariable Long id, Model model) {
        model.addAttribute("professor", professorService.getProfessor(id));
        return "professor/edit";  // Ensure this matches the actual template name
    }
    @RequestMapping("/professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return "redirect:/professor/list";
    }

    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String addProfessor(@ModelAttribute Professor professor) {
        professorService.addProfessor(professor);
        // Save professor logic
        return "redirect:/professor/list";
    }

    @RequestMapping(value = "/professor/edit/{id}", method = RequestMethod.POST)
    public String setEdit(@PathVariable Long id, @ModelAttribute Professor professor) {
        Professor editedProfessor = professorService.getProfessor(id);
        //Modificamos DNI, nombre y apellidos
        editedProfessor.setName(professor.getName());
        editedProfessor.setSurname(professor.getSurname());
        editedProfessor.setDni(professor.getDni());
        editedProfessor.setCategory(professor.getCategory());

        professorService.addProfessor(editedProfessor);
        return "redirect:/professor/details?id=" + professor.getId();
    }


}

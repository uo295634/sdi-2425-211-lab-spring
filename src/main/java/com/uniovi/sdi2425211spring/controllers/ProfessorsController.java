package com.uniovi.sdi2425211spring.controllers;

import com.uniovi.sdi2425211spring.entities.Professor;
import com.uniovi.sdi2425211spring.services.MarksService;
import com.uniovi.sdi2425211spring.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ProfessorsController {
    @Autowired //Inyectar el servicio
    private ProfessorService professorService;

    @RequestMapping(value = "/professor/add")
    public String setProfessor() {
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
    public String getEdit(@PathVariable Long id) {
        return "professor/edit/" + id;
    }
    @RequestMapping("/professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return "redirect:/professor/list";
    }

    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String addProfessor(@ModelAttribute Professor professor) {
        professorService.addProfessor(professor);
        return "redirect:/professor/list";
    }

    @RequestMapping(value = "/professor/edit/{id}", method = RequestMethod.POST)
    public String editProfessor(@ModelAttribute Professor professor, @PathVariable Long id) {
        professor.setId(id);
        professorService.addProfessor(professor);
        return "/professor/edit/" + id;
    }


}

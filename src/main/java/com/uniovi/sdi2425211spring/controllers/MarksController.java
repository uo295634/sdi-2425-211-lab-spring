package com.uniovi.sdi2425211spring.controllers;

import com.uniovi.sdi2425211spring.entities.Mark;
import com.uniovi.sdi2425211spring.entities.User;
import com.uniovi.sdi2425211spring.services.MarksService;
import com.uniovi.sdi2425211spring.services.UsersService;
import com.uniovi.sdi2425211spring.validators.AddMarksValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MarksController {
    // Inyectamos el servicio por inyección basada en constructor
    private final MarksService marksService;
    private final UsersService usersService;
    private final AddMarksValidator addMarksValidator;

    private final HttpSession httpSession;

    public MarksController(MarksService marksService, UsersService usersService, AddMarksValidator
            addMarksValidator, HttpSession httpSession) {
        this.marksService = marksService;
        this.usersService = usersService;
        this.addMarksValidator = addMarksValidator;
        this.httpSession = httpSession;
    }

    @RequestMapping("/mark/list")
    public String getList(Model model, Principal principal){
        String dni = principal.getName(); // DNI es el name de la autenticación
        User user = usersService.getUserByDni(dni);
        model.addAttribute("markList", marksService.getMarksForUser(user) );
        return "mark/list";
    }
    @RequestMapping("/mark/list/update")
    public String updateList(Model model, Principal principal) {
        String dni = principal.getName();
        User user = usersService.getUserByDni(dni);
        model.addAttribute("markList", marksService.getMarksForUser(user));
        return "mark/list::marksTable";
    }
    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String setMark(@ModelAttribute Mark mark) {
        marksService.addMark(mark);
        return "redirect:/mark/list";
    }

    @RequestMapping("/mark/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        return "mark/details";
    }

    @RequestMapping(value="/mark/add")
    public String getMark(Model model){
        model.addAttribute("usersList", usersService.getUsers());
        return "mark/add";
    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        marksService.deleteMark(id);
        return "redirect:/mark/list";
    }

    @RequestMapping(value = "/mark/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        model.addAttribute("usersList", usersService.getUsers());
        return "mark/edit";
    }

    @RequestMapping(value="/mark/edit/{id}", method=RequestMethod.POST)
    public String setEdit(@ModelAttribute Mark mark, @PathVariable Long id){
        Mark originalMark = marksService.getMark(id);
        // modificar solo score y description
        originalMark.setScore(mark.getScore());
        originalMark.setDescription(mark.getDescription());
        marksService.addMark(originalMark);
        return "redirect:/mark/details/"+id;
    }
    @RequestMapping(value = "/mark/{id}/resend", method = RequestMethod.GET)
    public String setResendTrue(@PathVariable Long id) {
        marksService.setMarkResend(true, id);
        return "redirect:/mark/list";
    }
    @RequestMapping(value = "/mark/{id}/noresend", method = RequestMethod.GET)
    public String setResendFalse(@PathVariable Long id) {
        marksService.setMarkResend(false, id);
        return "redirect:/mark/list";
    }
}

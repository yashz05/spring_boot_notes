package com.twp.ajavaproj.controller;


import com.twp.ajavaproj.entites.notes;
import com.twp.ajavaproj.model.dashboard_model;
import com.twp.ajavaproj.repo.noterepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller

public class notes_controller {

    @Autowired
    noterepo ntp;

    @PostMapping("/notes/new")
    public String insert(@ModelAttribute notes b, BindingResult r, RedirectAttributes redirectAttributes) {
        if (r.hasErrors()) {
            return "error";
        }

        ntp.save(b);
        redirectAttributes.addFlashAttribute("message", "Added Note Successfully !");
        return "redirect:/dashboard";


    }

    ;

    @RequestMapping("/notes")
    public ModelAndView notes_page(@CookieValue(value = "user", defaultValue = "GUEST") String name, Model m) {

        List<notes> not = ntp.findAll();
        m.addAttribute("name", name);
        m.addAttribute("list", not);
        return new ModelAndView("notes", "data", m);

    }

    @RequestMapping("/notes/{id}")
    public ModelAndView edit_notes(@CookieValue(value = "user", defaultValue = "GUEST") String name, Model m, @PathVariable Long id) {

        Optional<notes> not = ntp.findById(id);
        m.addAttribute("name", name);
        m.addAttribute("note", not.get());
        m.addAttribute("noteid", id);

        return new ModelAndView("notes_edit");

    }

    @PostMapping("/notes/update")
    public String update(@ModelAttribute notes b, BindingResult r, RedirectAttributes redirectAttributes) {
        if (r.hasErrors()) {
            return "error";
        }

        ntp.updateById(b.id, b.content, b.title);
        System.out.println(b.id);

        redirectAttributes.addFlashAttribute("message", "Update Note Successfully !");
        return "redirect:/notes";
    }

    @GetMapping("/notes/delete/{id}")
    public String delete(@ModelAttribute notes b, BindingResult r, RedirectAttributes redirectAttributes, @PathVariable Long id) {
        if (r.hasErrors()) {
            return "error";
        }
        ntp.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Deleted Note Successfully !");
        return "redirect:/notes";
    }
}

package ru.alishev.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alishev.dao.HospitalDAO;
import ru.alishev.models.Doctor;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalDAO hospitalDAO;

    @GetMapping({"/",""})
    public String allDoctors(Model model) {
        model.addAttribute("all", hospitalDAO.allDoctors());
        return "index";
    }

    @GetMapping("/{id}")
    public String doctor(@PathVariable("id") int id, Model model) {
        model.addAttribute("doctor", hospitalDAO.doctor(id));
        return "doctor";
    }

    @GetMapping("/add")
    public String newDoctor(@ModelAttribute("doctor") Doctor doctor) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("doctor") Doctor doctor) {
        hospitalDAO.create(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,  Model model) {
        model.addAttribute("doctor", hospitalDAO.doctor(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("doctor") Doctor doctor, @PathVariable("id") int id) {
        hospitalDAO.update(id, doctor);
        return "redirect:/doctors";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        hospitalDAO.delete(id);
        return "redirect:/doctors";
    }
}

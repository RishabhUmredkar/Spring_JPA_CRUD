package com.example.demo.controller;

import com.example.demo.Repo.EmpRepo;
import com.example.demo.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EmpRepo empRepo;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("emp", new Emp());
        return "reg";
    }

    @PostMapping("/save")
    public String saveEmp(@ModelAttribute("emp") Emp emp) {
        empRepo.save(emp);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String showEmpList(Model model) {
        List<Emp> empList = empRepo.findAll();
        model.addAttribute("empList", empList);
        return "list";
    }

    @GetMapping("/edit/{id}")
    public String editEmp(@PathVariable("id") int id, Model model) {
        Emp emp = empRepo.findById(id).orElse(null);
        model.addAttribute("emp", emp);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateEmp(@PathVariable("id") int id, @ModelAttribute("emp") Emp emp) {
        emp.setId(id);
        empRepo.save(emp);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable("id") int id) {
        empRepo.deleteById(id);
        return "redirect:/list";
    }
}

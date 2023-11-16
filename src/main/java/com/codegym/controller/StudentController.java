package com.codegym.controller;

import com.codegym.model.Student;
import com.codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
@Autowired
    private IStudentService studentService;
    @GetMapping("")
    public String index(Model model) {
        List<Student> studentList = studentService.findAll();
        model.addAttribute("students", studentList);
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("student", new Student());
        return "/create";
    }

    @PostMapping("/save")
    public String save(Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "/update";
    }

    @PostMapping("/update")
    public String update(Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(Student student, RedirectAttributes redirect) {
        studentService.remove(student.getId());
        redirect.addFlashAttribute("success", "Removed student successfully!,hehe");
        return "redirect:/students";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "/view";
    }
    @GetMapping("/search")
    public String search (@RequestParam String q, Model model){
        List<Student> studentList = studentService.findByName(q);
        model.addAttribute("students", studentList);
        return "/index";

    }


}

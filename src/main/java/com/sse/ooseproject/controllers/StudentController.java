package com.sse.ooseproject.controllers;

import com.sse.ooseproject.StudentRepository;
import com.sse.ooseproject.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public String students(Model model, @RequestParam String sort_by, @RequestParam Boolean sort_asc) {
        Sort.Direction sortDirection;
        if(sort_asc){
            sortDirection = Sort.Direction.ASC;
        }else{
            sortDirection = Sort.Direction.DESC;
        }
        List<Student> students = studentRepository.findAll(Sort.by(sortDirection, sort_by));
        model.addAttribute("sort_by", sort_by);
        model.addAttribute("sort_asc", sort_asc);
        model.addAttribute("students", students);

        // Returning the name of a view (found in resources/templates) as a string lets this endpoint return that view.
        return "students";
    }
}

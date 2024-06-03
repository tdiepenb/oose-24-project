package com.sse.ooseproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
    public StudentController(/* TODO add required repositories. */) {

    }

    @GetMapping("/students")
    public String students(Model model /* TODO add request parameters. */) {
        // TODO add functionality.

        // Returning the name of a view (found in resources/templates) as a string lets this endpoint return that view.
        return "students";
    }
}

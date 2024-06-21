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

    /**
     * Handles GET requests for retrieving a list of students.
     *
     * This method fetches all students from the repository, sorts them according to the specified
     * sort parameters, and adds the sorted list along with sorting parameters to the model.
     * The method then returns the name of the view to be rendered.
     *
     * @param model The Model object that will hold the data to be displayed on the view.
     * @param sort_by The field by which the students should be sorted.
     * @param sort_asc A boolean indicating whether the sorting should be in ascending order.
     * @return The name of the view to be rendered, in this case, "students".
     */
    @GetMapping("/students")
    public String students(Model model, @RequestParam(defaultValue = "firstName") String sort_by, @RequestParam(defaultValue = "true") Boolean sort_asc) {
        Sort.Direction sortDirection;
        if (sort_asc) {
            sortDirection = Sort.Direction.ASC;
        } else {
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

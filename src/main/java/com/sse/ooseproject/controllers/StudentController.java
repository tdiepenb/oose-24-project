package com.sse.ooseproject.controllers;

import com.sse.ooseproject.InstituteRepository;
import com.sse.ooseproject.StudentRepository;
import com.sse.ooseproject.StudentValidator;
import com.sse.ooseproject.models.Student;
import com.sse.ooseproject.models.Institute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;
    private final InstituteRepository instituteRepository;
    private final StudentValidator studentValidator = new StudentValidator();

    @Autowired
    public StudentController(StudentRepository studentRepository, InstituteRepository instituteRepository) {
        this.studentRepository = studentRepository;
        this.instituteRepository = instituteRepository;
    }

    /**
     * Handles GET requests for retrieving a list of students.
     * <p>
     * This method fetches all students from the repository, sorts them according to the specified
     * sort parameters, and adds the sorted list along with sorting parameters to the model.
     * The method then returns the name of the view to be rendered.
     *
     * @param model    The Model object that will hold the data to be displayed on the view.
     * @param sort_by  The field by which the students should be sorted.
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

    /**
     * Handles GET requests for creating a new student.
     * <p>
     * This method initializes a new student object, sets the page type to "new",
     * and provides a list of available study subjects. It then adds these attributes to the model
     * and returns the name of the view to be rendered.
     *
     * @param model The Model object that will hold the data to be displayed on the view.
     * @return The name of the view to be rendered, in this case, "edit_student".
     */
    @GetMapping("/student/new")
    public String newStudent(Model model) {
        Student newStudent = new Student();
        String pageType = "new";

        // get all institutes
        List<Institute> institutes = instituteRepository.findAll();
        List<String> studySubjects = new ArrayList<>();

        // add the provided study subject from each institute to the list of available study subjects
        institutes.forEach((institute) -> {
            studySubjects.add(institute.getProvidesStudySubject());
        });

        // sort the studySubjects in Alphabetical order
        studySubjects.sort(String::compareTo);

        model.addAttribute("student", newStudent);
        model.addAttribute("page_type", pageType);
        model.addAttribute("study_subjects", studySubjects);

        return "edit_student";
    }

}

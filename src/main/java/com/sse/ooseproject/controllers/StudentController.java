package com.sse.ooseproject.controllers;

import com.sse.ooseproject.InstituteRepository;
import com.sse.ooseproject.StudentRepository;
import com.sse.ooseproject.StudentValidationException;
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
    private final StudentValidator validator;


    @Autowired
    public StudentController(StudentRepository studentRepository, InstituteRepository instituteRepository) {
        this.studentRepository = studentRepository;
        this.instituteRepository = instituteRepository;
        this.validator = new StudentValidator(studentRepository, instituteRepository);
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
        institutes.forEach((institute) -> studySubjects.add(institute.getProvidesStudySubject()));

        // sort the studySubjects in Alphabetical order
        studySubjects.sort(String::compareTo);

        model.addAttribute("student", newStudent);
        model.addAttribute("page_type", pageType);
        model.addAttribute("study_subjects", studySubjects);

        return "edit_student";
    }


    /**
     * Handles POST requests for creating a new student.
     * <p>
     * This method validates the input student using the validator. If validation is successfully the student is saved
     * to the database, otherwise a StudentValidationException is returned by the validator which we catch and set the
     * Model attributes accordingly.
     *
     * @param model   The Model object that will hold the data to be displayed on the view.
     * @param student The student object to be created
     * @return The name of the view to be rendered, in this case, "edit_student".
     */
    @PostMapping("/student/new")
    public String addStudent(Model model, @ModelAttribute("student") Student student) {

        String messageType = "success";
        String message = "Successfully added the Student to the Database";
        //setting this to a new student makes it, so we get empty fields if creating the student was successfully
        Student newStudent = new Student();

        try {
            validator.validateStudent(student);
            studentRepository.save(student);
        } catch (StudentValidationException e) {
            // setting this to the input student makes it, so that we keep the inserted values if we get an error
            newStudent = student;
            messageType = "error";
            message = e.getMessage();
        }

        String pageType = "new";

        // get all institutes
        List<Institute> institutes = instituteRepository.findAll();
        List<String> studySubjects = new ArrayList<>();

        // add the provided study subject from each institute to the list of available study subjects
        institutes.forEach((institute) -> studySubjects.add(institute.getProvidesStudySubject()));

        // sort the studySubjects in Alphabetical order
        studySubjects.sort(String::compareTo);

        model.addAttribute("student", newStudent);
        model.addAttribute("page_type", pageType);
        model.addAttribute("study_subjects", studySubjects);
        model.addAttribute("message_type", messageType);
        model.addAttribute("message", message);

        return "edit_student";
    }

}

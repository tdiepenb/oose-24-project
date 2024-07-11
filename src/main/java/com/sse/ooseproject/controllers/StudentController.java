package com.sse.ooseproject.controllers;

import com.sse.ooseproject.*;
import com.sse.ooseproject.models.*;
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
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;


    @Autowired
    public StudentController(StudentRepository studentRepository, InstituteRepository instituteRepository, EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.instituteRepository = instituteRepository;
        this.validator = new StudentValidator(studentRepository, instituteRepository);
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
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
        String modelMessage = "Successfully added student " +
                student.getFirstName() + " " + student.getLastName() +
                " with MatNr: " +
                student.getMatNr() +
                " to the Database";
        //setting this to a new student makes it, so we get empty fields if creating the student was successfully
        Student newStudent = new Student();

        String pageType = "new";

        try {
            boolean success = validator.validateStudent(student, pageType);
            if (success) {
                studentRepository.save(student);
            }
        } catch (StudentValidationException e) {
            // setting this to the input student makes it, so that we keep the inserted values if we get an error
            newStudent = student;
            messageType = "error";
            modelMessage = e.getMessage();
        }


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
        model.addAttribute("message", modelMessage);

        return "edit_student";
    }

    /**
     * Handles GET requests for editing an existing student.
     * <p>
     * This method edits a student object, sets the page type to "edit",
     * and provides a list of available study subjects. It then adds these attributes to the model
     * and returns the name of the view to be rendered.
     *
     * @param id The is of the student which should be edited
     * @param model The Model object that will hold the data to be displayed on the view.
     * @return The name of the view to be rendered, in this case, "edit_student".
     */
    @GetMapping("/student/edit")
    public String editStudent(@RequestParam("id") Long id, Model model) {
        Student editStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        String pageType = "edit";

        // get all institutes
        List<Institute> institutes = instituteRepository.findAll();
        List<String> studySubjects = new ArrayList<>();

        // add the provided study subject from each institute to the list of available study subjects
        institutes.forEach((institute) -> studySubjects.add(institute.getProvidesStudySubject()));

        // sort the studySubjects in Alphabetical order
        studySubjects.sort(String::compareTo);

        model.addAttribute("student", editStudent);
        model.addAttribute("page_type", pageType);
        model.addAttribute("study_subjects", studySubjects);

        return "edit_student";
    }


    /**
     * Handles POST requests for editing a student.
     * <p>
     * This method validates the input student using the validator. If validation is successfully the edited student is saved
     * to the database, otherwise a StudentValidationException is returned by the validator which we catch and set the
     * Model attributes accordingly.
     *
     * @param model   The Model object that will hold the data to be displayed on the view.
     * @param student The student object to be created
     * @return The name of the view to be rendered, in this case, "edit_student".
     */
    @PostMapping("/student/edit")
    public String editStudent(@RequestParam("id") Long id, Model model, @ModelAttribute("student") Student student) {

        String messageType = "success";
        String modelMessage = "Successfully edited student " +
                student.getFirstName() + " " + student.getLastName() +
                " with MatNr: " +
                student.getMatNr() +
                " to the Database";
        //setting this to a new student makes it, so we get empty fields if creating the student was successfully
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

        String pageType = "edit";

        try {
            boolean success = validator.validateStudent(student, pageType);
            if (success) {
                existingStudent.setFirstName(student.getFirstName());
                existingStudent.setLastName(student.getLastName());
                existingStudent.setEmail(student.getEmail());
                existingStudent.setMatNr(student.getMatNr());
                existingStudent.setStudySubject(student.getStudySubject());
                studentRepository.save(existingStudent);
            }
        } catch (StudentValidationException e) {
            // setting this to the input student makes it, so that we keep the inserted values if we get an error
            existingStudent = student;
            messageType = "error";
            modelMessage = e.getMessage();
        }

        // get all institutes
        List<Institute> institutes = instituteRepository.findAll();
        List<String> studySubjects = new ArrayList<>();

        // add the provided study subject from each institute to the list of available study subjects
        institutes.forEach((institute) -> studySubjects.add(institute.getProvidesStudySubject()));

        // sort the studySubjects in Alphabetical order
        studySubjects.sort(String::compareTo);

        model.addAttribute("student", existingStudent);
        model.addAttribute("page_type", pageType);
        model.addAttribute("study_subjects", studySubjects);
        model.addAttribute("message_type", messageType);
        model.addAttribute("message", modelMessage);

        return "edit_student";
    }

    /**
     * Handles GET requests for showing enrollments.
     * <p>
     * This method gets the student and all the enrollments of the given semester of this student.
     * It gets all courses where the student can enroll based on the study subject.
     * It then adds these attributes to the model and returns the name of the view to be rendered.
     *
     * @param id The id of the student
     * @param semester the semester of the shown enrollments
     * @param model The Model object that will hold the data to be displayed on the view.
     * @return The name of the view to be rendered, in this case, "enrollment".
     */
    @GetMapping("/student/enroll")
    public String viewEnrollments(@RequestParam("id") Long id, @RequestParam(defaultValue = "2024 Spring") String semester, Model model) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        List<Enrollment> enrollments = enrollmentRepository.findByStudentIdAndSemester(id, semester);

        //get courses of the institute with the respective study subject
        Institute institute = instituteRepository.findByProvidesStudySubject(student.getStudySubject());
        List<Chair> chairsOfInstitute = institute.getChairs();
        List<Course> courses = new ArrayList<>();
        for (Chair chair : chairsOfInstitute) {
            courses.addAll(chair.getCourses());
        }

        model.addAttribute("student", student);
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("semester", semester);
        model.addAttribute("courses", courses);

        return "enrollment";
    }

    /**
     * Handles GET requests for creating a new enrollment for given student, course and semester.
     * <p>
     * This method gets the student and the course and creates an enrollment for the given semester.
     * It then saves the created enrollment and opens the enrollment view through the previous endpoints function.
     *
     * @param student_id The id of the student
     * @param course_id The id of the course
     * @param semester the given semester
     * @return The name of the view to be rendered, in this case, "enrollment".
     */
    @GetMapping("/enrollment/enroll")
    public String createEnrollment(@RequestParam("student_id") Long student_id, @RequestParam("course_id") Long course_id, @RequestParam("semester") String semester) {

        Student student = studentRepository.findById(student_id).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(course_id).orElseThrow(() -> new RuntimeException("Course not found"));

        EnrollmentId enrollmentId = new EnrollmentId(course_id, student_id);
        Enrollment enrollment = new Enrollment(enrollmentId, course, student, semester);

        enrollmentRepository.save(enrollment);

        return "redirect:/student/enroll?id=" + student_id + "&semester=" + semester;
    }

    /**
     * Handles GET requests for creating a new enrollment for given student, course and semester.
     * <p>
     * This method gets the student and the course and creates an enrollment for the given semester.
     * It then saves the created enrollment and opens the enrollment view through the previous endpoints function.
     *
     * @param student_id The id of the student
     * @param course_id The id of the course
     * @param semester the given semester
     * @return The name of the view to be rendered, in this case, "enrollment".
     */
    @GetMapping("/enrollment/delete")
    public String deleteEnrollment(@RequestParam("student_id") long student_id, @RequestParam("course_id") long course_id, @RequestParam("semester") String semester) {

        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseIdAndSemester(student_id, course_id, semester);
        enrollmentRepository.deleteById(enrollment.getId());

        return "redirect:/student/enroll?id=" + student_id + "&semester=" + semester;
    }

}

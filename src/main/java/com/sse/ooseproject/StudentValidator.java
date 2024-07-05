package com.sse.ooseproject;

import com.sse.ooseproject.models.Institute;
import com.sse.ooseproject.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StudentValidator {

    private final StudentRepository studentRepository;
    private final InstituteRepository instituteRepository;

    /**
     * Initializes the StudentValidator with a studentRepository and an instituteRepository
     *
     * @param studentRepository   The studentRepository to validate the student with
     * @param instituteRepository The instituteRepository to validate the student with
     */
    public StudentValidator(StudentRepository studentRepository, InstituteRepository instituteRepository) {
        this.studentRepository = studentRepository;
        this.instituteRepository = instituteRepository;
    }

    /**
     * A method that validates a given student object with the repositories provided during initialization.
     * If validation fails a StudentValidationException is thrown
     *
     * @param student The student object to validate
     * @throws StudentValidationException a StudentValidationException with an error message
     */
    public void validateStudent(Student student) throws StudentValidationException {

        // check if all fields are filled
        if (student.getFirstName() == null || student.getLastName() == null) {
            throw new StudentValidationException("First name and last name are required");
        }

        if (student.getEmail() == null) {
            throw new StudentValidationException("Email is required");
        }

        // check for valid email address
        if (!validateEmail(student.getEmail())) {
            throw new StudentValidationException("Email is not valid");
        }

        // check if matNr is valid
        if (student.getMatNr() == 0 || student.getMatNr() < 0) {
            throw new StudentValidationException("MatNr is invalid");
        }

        // check if student with this matNr already exists
        Student existingStudent = studentRepository.findByMatNr(student.getMatNr());

        if (existingStudent != null) {
            throw new StudentValidationException("Student with MatNr " + student.getMatNr() + " already exists");
        }

        // check if study subject exists
        List<Institute> institutes = instituteRepository.findAll();
        List<String> studySubjects = new ArrayList<>();
        institutes.forEach((institute) -> studySubjects.add(institute.getProvidesStudySubject()));

        if (!studySubjects.contains(student.getStudySubject())) {
            throw new StudentValidationException("Study subject " + student.getStudySubject() + " does not exist");
        }
    }


    /**
     * A method that validates if an e-mail address follows the standard e-mail pattern
     * Returns True if valid, False if invalid
     *
     * @param email the e-mail String to validate
     * @return boolean of validation status
     */
    private boolean validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}

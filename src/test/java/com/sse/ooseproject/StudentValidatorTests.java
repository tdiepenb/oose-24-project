package com.sse.ooseproject;

import com.sse.ooseproject.models.Institute;
import com.sse.ooseproject.models.Student;
import com.sse.ooseproject.models.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentValidatorTests {
    private StudentValidator studentValidator;
    private StudentRepository studentRepository;
    private InstituteRepository instituteRepository;
    private University university;
    private List<Institute> institutes;

    @BeforeEach
    public void setUpBeforeClass() {
        this.studentRepository = Mockito.mock(StudentRepository.class);
        this.instituteRepository = Mockito.mock(InstituteRepository.class);
        this.studentValidator = new StudentValidator(studentRepository, instituteRepository);
        this.university = new University("Summit Heights University");
        this.institutes = new ArrayList<>();
        this.institutes.add(new Institute("Institute of Advanced Mathematical Sciences", "Mathematics", university));
        this.institutes.add(new Institute("Institute of Innovation and Engineering Technology", "Engineering", university));
    }

    @Test
    @DisplayName("Testing valid student")
    public void testValidStudent() throws StudentValidationException {
        Mockito.when(instituteRepository.findAll()).thenReturn(institutes);
        Mockito.when(studentRepository.findByMatNr(123)).thenReturn(null);

        Student student = new Student("Max", "Mustermann", "mmuster@university.de", 123, "Mathematics", university);
        assertTrue(studentValidator.validateStudent(student));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "       "})
    @DisplayName("Testing First Name empty")
    public void testFirstNameEmpty(String name) {
        Mockito.when(instituteRepository.findAll()).thenReturn(institutes);
        Mockito.when(studentRepository.findByMatNr(123)).thenReturn(null);

        Student student = new Student(name, "Mustermann", "mmuster@university.de", 123, "Mathematics", university);
        StudentValidationException thrown = assertThrows(StudentValidationException.class, () -> studentValidator.validateStudent(student));

        assertEquals("First name and last name are required", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "       "})
    @DisplayName("Testing Last Name empty")
    public void testLastNameEmpty(String name) {
        Mockito.when(instituteRepository.findAll()).thenReturn(institutes);
        Mockito.when(studentRepository.findByMatNr(123)).thenReturn(null);

        Student student = new Student("Max", name, "mmuster@university.de", 123, "Mathematics", university);
        StudentValidationException thrown = assertThrows(StudentValidationException.class, () -> studentValidator.validateStudent(student));

        assertEquals("First name and last name are required", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "       "})
    @DisplayName("Testing Email empty")
    public void testEmailEmpty(String email) {
        Mockito.when(instituteRepository.findAll()).thenReturn(institutes);
        Mockito.when(studentRepository.findByMatNr(123)).thenReturn(null);

        Student student = new Student("Max", "Mustermann", email, 123, "Mathematics", university);
        StudentValidationException thrown = assertThrows(StudentValidationException.class, () -> studentValidator.validateStudent(student));

        assertEquals("Email is required", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"mmuster", "mmuster@", "mmuster@university", "mmuster@university.", ".mmuster@university.de", "mmuster@university..de."})
    @DisplayName("Testing Email invalid")
    public void testEmailInvalid(String email) {
        Mockito.when(instituteRepository.findAll()).thenReturn(institutes);
        Mockito.when(studentRepository.findByMatNr(123)).thenReturn(null);

        Student student = new Student("Max", "Mustermann", email, 123, "Mathematics", university);
        StudentValidationException thrown = assertThrows(StudentValidationException.class, () -> studentValidator.validateStudent(student));

        assertEquals("Email is not valid", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -50, -9999999})
    @DisplayName("Testing MatNr invalid")
    public void testMatNrInvalid(int matNr) {
        Mockito.when(instituteRepository.findAll()).thenReturn(institutes);
        Mockito.when(studentRepository.findByMatNr(123)).thenReturn(null);

        Student student = new Student("Max", "Mustermann", "mmuster@university.de", matNr, "Mathematics", university);
        StudentValidationException thrown = assertThrows(StudentValidationException.class, () -> studentValidator.validateStudent(student));

        assertEquals("MatNr is invalid", thrown.getMessage());
    }

    @Test
    @DisplayName("Testing MatNr already Exists")
    public void testMatNrAlreadyExists() {
        Mockito.when(instituteRepository.findAll()).thenReturn(institutes);
        Mockito.when(studentRepository.findByMatNr(123)).thenReturn(new Student("Max", "Müller", "mmueller@university.de", 123, "Biology", university));

        Student student = new Student("Max", "Mustermann", "mmuster@university.de", 123, "Mathematics", university);
        StudentValidationException thrown = assertThrows(StudentValidationException.class, () -> studentValidator.validateStudent(student));

        assertEquals("Student with MatNr " + student.getMatNr() + " already exists", thrown.getMessage());
    }

    @Test
    @DisplayName("Testing Study Subject not provided")
    public void testStudySubjectNotProvided() {
        Mockito.when(instituteRepository.findAll()).thenReturn(institutes);
        Mockito.when(studentRepository.findByMatNr(123)).thenReturn(null);

        Student student = new Student("Max", "Mustermann", "mmuster@university.de", 123, "International Relations", university);
        StudentValidationException thrown = assertThrows(StudentValidationException.class, () -> studentValidator.validateStudent(student));

        assertEquals("Study subject " + student.getStudySubject() + " does not exist", thrown.getMessage());
    }


}

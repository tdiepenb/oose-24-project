package com.sse.ooseproject.models;


import jakarta.persistence.*;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @EmbeddedId
    private EnrollmentId id;

    @ManyToOne
    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    private Student student;

    private String semester;

    public Enrollment() {
    }

    /**
     * Constructor for the Enrollment class that initializes the enrollment with a course, student, and semester.
     *
     * @param id       the enrollmentid of the enrollment
     * @param course   the course in which the student is enrolled
     * @param student  the student enrolled in the course
     * @param semester the semester during which the enrollment is made
     */
    public Enrollment(EnrollmentId id, Course course, Student student, String semester) {
        this.id = id;
        this.course = course;
        this.student = student;
        this.semester = semester;
    }

    public EnrollmentId getId() {
        return id;
    }

    public void setId(EnrollmentId id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}

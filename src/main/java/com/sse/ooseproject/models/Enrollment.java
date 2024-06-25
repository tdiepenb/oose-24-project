package com.sse.ooseproject.models;


import jakarta.persistence.*;

@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    private String semester;

    public Enrollment() {
    }

    /**
     * Constructor for the Enrollment class that initializes the enrollment with a course, student, and semester.
     *
     * @param course   the course in which the student is enrolled
     * @param student  the student enrolled in the course
     * @param semester the semester during which the enrollment is made
     */
    public Enrollment(Course course, Student student, String semester) {
        this.course = course;
        this.student = student;
        this.semester = semester;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

package com.sse.ooseproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "teaching_shift")
public class TeachingShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private String semester;

    public TeachingShift() {
    }

    /**
     * Constructor for the TeachingShift class that initializes the teaching shift with a course, employee, and semester.
     *
     * @param course   the course that is being taught
     * @param employee the employee (instructor) assigned to the teaching shift
     * @param semester the semester during which the teaching shift occurs
     */
    public TeachingShift(Course course, Employee employee, String semester) {
        this.course = course;
        this.employee = employee;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}

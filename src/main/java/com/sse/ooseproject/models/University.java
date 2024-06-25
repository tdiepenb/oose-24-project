package com.sse.ooseproject.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @OneToMany
    private List<Building> buildings;
    @OneToMany(mappedBy = "university")
    private List<Student> students;
    @OneToMany(mappedBy = "university")
    private List<Employee> employees;

    /**
     * Default constructor for the University class.
     * Initializes a new instance of the University class with default values.
     */
    public University() {
    }

    /**
     * Constructor for the University class that initializes the university with a name.
     *
     * @param name the name of the university
     */
    public University(String name) {
        this.name = name;
        this.buildings = new ArrayList<>();
        this.students = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

package com.sse.ooseproject.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chair")
public class Chair extends OrganizationalUnit {

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Employee chairOwner;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @OneToMany(mappedBy = "chair")
    private List<Course> courses;

    @OneToMany(mappedBy = "chair")
    private List<Employee> employees;

    public Chair() {
        super();
    }

    /**
     * Constructor for chair class
     *
     * @param name       the name of the chair
     * @param chairOwner owner of the chair
     * @param building   location of the chair
     * @param institute  the institute of the chair
     * @param university the university of the chair
     */
    public Chair(String name, Employee chairOwner, Building building, Institute institute, University university) {
        super(name);
        this.chairOwner = chairOwner;
        this.building = building;
        this.institute = institute;
        this.university = university;
        this.courses = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public Employee getChairOwner() {
        return chairOwner;
    }

    public void setChairOwner(Employee chairOwner) {
        this.chairOwner = chairOwner;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

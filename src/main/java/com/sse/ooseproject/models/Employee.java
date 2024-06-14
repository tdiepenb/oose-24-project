package com.sse.ooseproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends Person {

    private int staffNr;
    private Boolean isProfessor;

    public Employee() {
        super();
    }

    /**
     * Constructor of the Employee Class
     *
     * @param firstName    first name of the Employee
     * @param lastName     last name of the Employee
     * @param emailAddress e-Mail address of the Employee
     * @param staffNr      staff number of the Employee
     * @param isProfessor  flag True if the Employee is a professor
     */

    public Employee(String firstName, String lastName, String emailAddress, int staffNr, Boolean isProfessor) {
        super(firstName, lastName, emailAddress);
        this.staffNr = staffNr;
        this.isProfessor = isProfessor;
    }

    public int getStaffNr() {
        return staffNr;
    }

    public void setStaffNr(int staffNr) {
        this.staffNr = staffNr;
    }

    public Boolean getProfessor() {
        return isProfessor;
    }

    public void setProfessor(Boolean professor) {
        isProfessor = professor;
    }
}
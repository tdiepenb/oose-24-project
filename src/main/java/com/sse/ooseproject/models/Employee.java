package com.sse.ooseproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee extends Person {

    private int staffNr;
    private boolean isProfessor;

    public Employee() {
        super();
    }

    /**
     * Constructor of the Employee Class
     *
     * @param firstName   first name of the Employee
     * @param lastName    last name of the Employee
     * @param email       e-Mail address of the Employee
     * @param staffNr     staff number of the Employee
     * @param isProfessor flag True if the Employee is a professor
     */

    public Employee(String firstName, String lastName, String email, int staffNr, boolean isProfessor) {
        super(firstName, lastName, email);
        this.staffNr = staffNr;
        this.isProfessor = isProfessor;
    }

    public int getStaffNr() {
        return staffNr;
    }

    public void setStaffNr(int staffNr) {
        this.staffNr = staffNr;
    }

    public boolean getIsProfessor() {
        return isProfessor;
    }

    public void setIsProfessor(boolean professor) {
        isProfessor = professor;
    }
}
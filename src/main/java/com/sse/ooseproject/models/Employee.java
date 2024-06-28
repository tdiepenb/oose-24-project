package com.sse.ooseproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee extends Person {

    private int staffNr;
    private boolean isProfessor;
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;
    @OneToOne(mappedBy = "employee")
    private Chair ownedChair;

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
     * @param university university of the Employee
     * @param ownedChair when Employee owns a chair the owned chair
     */

    public Employee(String firstName, String lastName, String email, int staffNr, boolean isProfessor, University university, Chair ownedChair) {
        super(firstName, lastName, email);
        this.staffNr = staffNr;
        this.isProfessor = isProfessor;
        this.university = university;
        this.ownedChair = ownedChair;
    }

    public int getStaffNr() {
        return staffNr;
    }

    public void setStaffNr(int staffNr) {
        this.staffNr = staffNr;
    }

    public boolean isProfessor() {
        return isProfessor;
    }

    public void setProfessor(boolean professor) {
        isProfessor = professor;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Chair getOwnedChair() {
        return ownedChair;
    }

    public void setOwnedChair(Chair ownedChair) {
        this.ownedChair = ownedChair;
    }
}
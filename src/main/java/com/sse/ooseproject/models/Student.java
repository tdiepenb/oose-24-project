package com.sse.ooseproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends Person {
    // Note: This class does not need its own id attribute as that will be derived.
    private int matNr;

    private String studySubject;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    public Student() {
        super();
    }

    /**
     * Constructor for the Student class
     *
     * @param firstName    first name of the Student
     * @param lastName     last name of the Student
     * @param email        e-Mail address of the Student
     * @param matNr        matriculation number of the Student
     * @param studySubject study subject of the Student
     * @param university   the university the student is studying at
     */
    public Student(String firstName, String lastName, String email, int matNr, String studySubject, University university) {
        super(firstName, lastName, email);
        this.matNr = matNr;
        this.studySubject = studySubject;
        this.university = university;
    }

    public int getMatNr() {
        return matNr;
    }

    public void setMatNr(int matNr) {
        this.matNr = matNr;
    }

    public String getStudySubject() {
        return studySubject;
    }

    public void setStudySubject(String studySubject) {
        this.studySubject = studySubject;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}

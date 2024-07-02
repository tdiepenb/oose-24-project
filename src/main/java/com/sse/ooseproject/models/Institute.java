package com.sse.ooseproject.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "institute")
public class Institute extends OrganizationalUnit {

    private String providesStudySubject;
    @OneToMany(mappedBy = "institute")
    private List<Chair> chairs;
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    public Institute() {
        super();
    }

    /**
     * Constructor for Institute class
     *
     * @param name                 the name of the institute
     * @param providesStudySubject the provided study subject
     * @param university           the university of the institute
     */
    public Institute(String name, String providesStudySubject, University university) {
        super(name);
        this.providesStudySubject = providesStudySubject;
        this.chairs = new ArrayList<Chair>();
        this.university = university;
    }

    public String getProvidesStudySubject() {
        return providesStudySubject;
    }

    public void setProvidesStudySubject(String providesStudySubject) {
        this.providesStudySubject = providesStudySubject;
    }

    public List<Chair> getChairs() {
        return chairs;
    }

    public void setChairs(List<Chair> chairs) {
        this.chairs = chairs;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}

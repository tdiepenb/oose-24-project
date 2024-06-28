package com.sse.ooseproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "institute")
public class Institute extends OrganizationalUnit {

    private String providesStudySubject;
    @OneToMany(mappedBy = "institute")
    private List<Chair> chairs;

    public Institute() {
        super();
    }

    /**
     * Constructor for Institute class
     *
     * @param providesStudySubject the provided study subject
     */
    public Institute(String providesStudySubject) {
        this.providesStudySubject = providesStudySubject;
        this.chairs = new ArrayList<Chair>();
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
}

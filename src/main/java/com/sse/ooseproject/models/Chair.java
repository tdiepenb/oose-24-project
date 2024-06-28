package com.sse.ooseproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "chair")
public class Chair extends OrganizationalUnit{

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Employee chairOwner;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;

    public Chair(){
        super();
    }

    /**
     * Constructor for chair class
     *
     * @param chairOwner owner of the chair
     * @param building location of the chair
     * @param institute the institute of the chair
     */
    public Chair(Employee chairOwner, Building building, Institute institute){
        this.chairOwner = chairOwner;
        this.building = building;
        this.institute = institute;
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
}

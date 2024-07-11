package com.sse.ooseproject.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "building")
    private List<Room> rooms;

    // this is missing the @JoinColumn(name = "") annotaion because neither university nor building has an appropiate column
    @ManyToOne
    private University university;

    @OneToMany(mappedBy = "building")
    private List<Chair> chairs;

    public Building() {
    }

    /**
     * Constructor for the Building class that initializes the building with a name and associated university.
     *
     * @param name       the name of the building
     * @param university the university to which the building belongs
     */
    public Building(String name, University university) {
        this.name = name;
        this.university = university;
        this.rooms = new ArrayList<>();
        this.chairs = new ArrayList<>();
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Chair> getChairs() {
        return chairs;
    }

    public void setChairs(List<Chair> chairs) {
        this.chairs = chairs;
    }
}

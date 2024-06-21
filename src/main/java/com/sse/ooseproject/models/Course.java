package com.sse.ooseproject.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany
    private List<RoomOccupancy> roomOccupancies;
    @OneToMany
    private List<Enrollment> enrollments;
    @OneToMany
    private List<TeachingShift> teachingShifts;

    public Course() {
    }

    /**
     * Constructor for the Course class that initializes the course with a name.
     *
     * @param name the name of the course
     */
    public Course(String name) {
        this.name = name;
        this.roomOccupancies = new ArrayList<>();
        this.enrollments = new ArrayList<>();
        this.teachingShifts = new ArrayList<>();
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

    public List<RoomOccupancy> getRoomOccupancies() {
        return roomOccupancies;
    }

    public void setRoomOccupancies(List<RoomOccupancy> roomOccupancies) {
        this.roomOccupancies = roomOccupancies;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public List<TeachingShift> getTeachingShifts() {
        return teachingShifts;
    }

    public void setTeachingShifts(List<TeachingShift> teachingShifts) {
        this.teachingShifts = teachingShifts;
    }
}

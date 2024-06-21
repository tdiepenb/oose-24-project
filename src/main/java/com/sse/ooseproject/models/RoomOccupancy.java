package com.sse.ooseproject.models;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_occupancy")
public class RoomOccupancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;
    private LocalDateTime occupancyTime;

    public RoomOccupancy() {
    }

    /**
     * Constructor for the RoomOccupancy class that initializes the room occupancy with a room, course, and occupancy time.
     *
     * @param room          the room being occupied
     * @param course        the course associated with the room occupancy
     * @param occupancyTime the time of the room occupancy
     */
    public RoomOccupancy(Room room, Course course, LocalDateTime occupancyTime) {
        this.room = room;
        this.course = course;
        this.occupancyTime = occupancyTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getOccupancyTime() {
        return occupancyTime;
    }

    public void setOccupancyTime(LocalDateTime occupancyTime) {
        this.occupancyTime = occupancyTime;
    }
}

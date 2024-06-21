package com.sse.ooseproject.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String number;
    private int seats;
    private boolean isAuditorium;
    @OneToMany
    private List<RoomOccupancy> occupancies;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    public Room() {
    }

    /**
     * Constructor for the Room class that initializes the room with its attributes.
     *
     * @param number       the number or identifier of the room
     * @param seats        the number of seats available in the room
     * @param isAuditorium indicates if the room is an auditorium
     * @param building     the building to which the room belongs
     */
    public Room(String number, int seats, boolean isAuditorium, Building building) {
        this.number = number;
        this.seats = seats;
        this.isAuditorium = isAuditorium;
        this.building = building;
        this.occupancies = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isAuditorium() {
        return isAuditorium;
    }

    public void setAuditorium(boolean auditorium) {
        isAuditorium = auditorium;
    }

    public List<RoomOccupancy> getOccupancies() {
        return occupancies;
    }

    public void setOccupancies(List<RoomOccupancy> occupancies) {
        this.occupancies = occupancies;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}

package com.sse.ooseproject.models;

import jakarta.persistence.*;

@MappedSuperclass
public class Person {
    // Entities or MappedSuperclass models in Spring require an id. The @GeneratedValue annotation makes sure
    // that the id is automatically increased when inserting new objects into the database.
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // TODO add more attributes.
    private String firstName;
    private String lastName;
    private String emailAddress;

    /**
     * A Person object. Spring requires an empty constructor. Do not change this but rather implement another
     * plausible constructor.
     */
    public Person() {
    }

    /**
     * Constructor of the Person class
     *
     * @param firstName    The first name of the Person
     * @param lastName     The last name of the Person
     * @param emailAddress The e-Mail address of the Person
     */
    public Person(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    // TODO add getter and setter methods.


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

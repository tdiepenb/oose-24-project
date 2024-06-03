package com.sse.ooseproject.models;

import jakarta.persistence.*;

public class Person {
    // Entities or MappedSuperclass models in Spring require an id. The @GeneratedValue annotation makes sure
    // that the id is automatically increased when inserting new objects into the database.
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // TODO add more attributes.

    /**
     * A Person object. Spring requires an empty constructor. Do not change this but rather implement another
     * plausible constructor.
     */
    public Person() {}

    // TODO add a plausible constructor.

    // TODO add getter and setter methods.
}

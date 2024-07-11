package com.sse.ooseproject.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;

@Embeddable
public class EnrollmentId {

    @JoinColumn(name = "course_id")
    private long course_id;
    @JoinColumn(name = "student_id")
    private long student_id;

    public EnrollmentId() {
    }

    public EnrollmentId(Long course_id, Long student_id) {
        this.course_id = course_id;
        this.student_id = student_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }
}

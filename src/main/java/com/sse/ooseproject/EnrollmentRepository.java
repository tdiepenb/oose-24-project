package com.sse.ooseproject;

import com.sse.ooseproject.models.Enrollment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Enrollment e WHERE e.id.course_id = :course_id AND e.id.student_id = :student_id")
    void deleteByStudentIdAndCourseId(@Param("student_id") long student_id, @Param("course_id") long course_id);
}

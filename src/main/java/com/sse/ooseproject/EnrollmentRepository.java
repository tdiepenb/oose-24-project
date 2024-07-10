package com.sse.ooseproject;

import com.sse.ooseproject.models.Enrollment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Enrollment e WHERE e.id.course_id = :course_id AND e.id.student_id = :student_id AND e.semester = :semester")
    void deleteByStudentIdAndCourseIdAndSemester(@Param("student_id") long student_id, @Param("course_id") long course_id, @Param("semester") String semester);

    List<Enrollment> findByStudentIdAndSemester(Long id, String semester);
}

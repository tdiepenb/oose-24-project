package com.sse.ooseproject;

import com.sse.ooseproject.models.Enrollment;
import com.sse.ooseproject.models.EnrollmentId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudentIdAndSemester(Long id, String semester);

    Enrollment findByStudentIdAndCourseIdAndSemester(Long student_id, Long course_id, String semester);

    @Modifying
    @Transactional
    void deleteByStudentIdAndCourseId(Long student_id, Long course_id);

    @Modifying
    @Transactional
    void deleteById(EnrollmentId enrollment_id);
}

package com.sse.ooseproject;

import com.sse.ooseproject.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByMatNr(int matNr);
}

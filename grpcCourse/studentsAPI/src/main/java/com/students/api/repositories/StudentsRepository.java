package com.students.api.repositories;

import com.students.api.entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository
        extends JpaRepository<Students, Long> {


}

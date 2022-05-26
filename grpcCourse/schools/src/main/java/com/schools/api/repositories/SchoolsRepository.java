package com.schools.api.repositories;

import com.schools.api.entities.Schools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolsRepository extends JpaRepository<Schools,Long> {


}

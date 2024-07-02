package com.auth.RoleBasedAuthorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.RoleBasedAuthorization.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}

package com.example.StudentInformation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.StudentInformation.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}

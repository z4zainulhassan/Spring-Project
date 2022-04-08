package com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.Departments;

public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {
}

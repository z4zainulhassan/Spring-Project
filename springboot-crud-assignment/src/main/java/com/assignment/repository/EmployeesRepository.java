package com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

}

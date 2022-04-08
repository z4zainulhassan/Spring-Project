package com.assignment.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.assignment.entity.Employees;

public interface EmployeesPagingRepository extends PagingAndSortingRepository<Employees, Integer> {

}

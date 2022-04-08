package com.assignment.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.assignment.entity.Departments;

public interface DepartmentsPagingRepository extends PagingAndSortingRepository<Departments, Integer> {

}

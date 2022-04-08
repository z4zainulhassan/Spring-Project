package com.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import com.assignment.entity.Departments;
import com.assignment.entity.Employees;
import com.assignment.repository.DepartmentsPagingRepository;
import com.assignment.repository.DepartmentsRepository;
import com.assignment.repository.EmployeesRepository;

@Service
public class DepartmentsService {

	@Autowired
	DepartmentsRepository departmentRepository;

	@Autowired
	DepartmentsPagingRepository departmentPagingRepository;

	@Autowired
	EmployeesRepository employeeRepository;

	public Page<Departments> getAllDepartments(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return departmentPagingRepository.findAll(paging);
	}

	public Departments getDepartmentById(Integer id) {
		return departmentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not found."));
	}

	public Departments createDepartment(Departments departments) {
		if (departments.getDepartmentName() == null && departments.getDepartmentName() == "") {
			throw new RestClientException("Department name should not be null or empty.");
		}
		List<Employees> empList = employeeRepository.findAll();
		List<Departments> deptList = departmentRepository.findAll();
		for (Departments d : deptList) {
			boolean bol1 = d.getDepartmentName().equalsIgnoreCase(departments.getDepartmentName());
			if (bol1) {
				throw new RestClientException("Department name already exists.Please try another name.");
			}
			boolean bol2 = d.getManagerId() == departments.getManagerId();
			if (bol2) {
				throw new RestClientException("Department Manager Id already exists.Please try another name.");
			}
		}
		if (empList.size() > 0) {
			for (Employees emp : empList) {
				if (emp.getManagerId() == departments.getManagerId()) {
					emp.setManagerId(departments.getManagerId());
					employeeRepository.save(emp);
				}
			}
		}
		return departmentRepository.save(departments);
	}

	public Departments updateDepartment(Departments departments) {
		Departments dep = departmentRepository.getById(departments.getDepartmentId());
		if (dep == null) {
			throw new RestClientException("Department with id " + departments.getDepartmentId() + " not found.");
		}

		if (departments.getDepartmentName() == null && departments.getDepartmentName() == "") {
			throw new RestClientException("Department name should not be null or empty.");
		}

		List<Employees> empList = employeeRepository.findAll();
		List<Departments> deptList = departmentRepository.findAll();
		for (Departments d : deptList) {
			boolean bol1 = d.getDepartmentName().equalsIgnoreCase(departments.getDepartmentName());
			if (bol1) {
				throw new RestClientException("Department name already exists.Please try another name.");
			}
			boolean bol2 = d.getManagerId() == departments.getManagerId();
			if (bol2) {
				throw new RestClientException("Department Manager Id already exists.Please try another name.");
			}
		}
		if (empList.size() > 0) {
			for (Employees emp : empList) {
				if (emp.getDepartment() != null && emp.getDepartment().getDepartmentId() != null) {
					if (emp.getDepartment().getDepartmentId() == departments.getDepartmentId()) {
						emp.setManagerId(departments.getManagerId());
						employeeRepository.save(emp);
					}
				}
			}
		}
		return departmentRepository.save(departments);
	}

	public void deleteDepartmentById(Integer id) {
		List<Employees> empList = employeeRepository.findAll();
		if (empList.size() > 0) {
			for (Employees emp : empList) {
				if (emp.getDepartment() != null) {
					if (emp.getDepartment().getDepartmentId() == id) {
						emp.setManagerId(0);
						emp.setDepartment(null);
						employeeRepository.save(emp);
					}
				}
			}
		}
		departmentRepository.deleteById(id);
	}

	public Departments updateDepartmentOrEmployeeManagerIdById(Integer deptId, Integer managerId) {
		Departments departments = departmentRepository.getById(deptId);
		if (departments == null) {
			throw new RestClientException("Department with id " + deptId + " not found.");
		}
		List<Employees> empList = employeeRepository.findAll();
		if (empList.size() > 0) {
			for (Employees emp : empList) {
				if (emp.getDepartment() != null && emp.getDepartment().getDepartmentId() != null) {
					if (emp.getDepartment().getDepartmentId() == departments.getDepartmentId()) {
						emp.setManagerId(departments.getManagerId());
						employeeRepository.save(emp);
					}
				}
			}
		}
		departments.setManagerId(managerId);
		return departmentRepository.save(departments);
	}
}

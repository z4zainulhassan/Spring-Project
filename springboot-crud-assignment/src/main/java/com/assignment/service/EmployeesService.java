package com.assignment.service;

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
import com.assignment.repository.DepartmentsRepository;
import com.assignment.repository.EmployeesPagingRepository;
import com.assignment.repository.EmployeesRepository;

@Service
public class EmployeesService {

	@Autowired
	EmployeesRepository employeeRepository;

	@Autowired
	EmployeesPagingRepository employeePagingRepository;

	@Autowired
	DepartmentsRepository departmentRepository;

	public Page<Employees> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return employeePagingRepository.findAll(paging);
	}

	public Employees getEmployeeById(Integer id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not found."));
	}

	public Employees createEmployee(Employees employee) {
		if (employee.getFirstName() == null && employee.getLastName() == null) {
			throw new RestClientException("Employee firstname or lastname should not be null.");
		}

		if (employee.getFirstName().length() < 3) {
			throw new RestClientException("Employee firstname length must be more than 2 characters.");
		}

		if (employee.getLastName().length() < 3) {
			throw new RestClientException("Employee lastname length must be more than 2 characters.");
		}

		if (employee.getSalary() <= 0) {
			throw new RestClientException("Employee salary must be more than 0.");
		}

		if (employee.getPhoneNumber() == null || !isValidPhoneNumber(employee.getPhoneNumber())) {
			throw new RestClientException("Employee phone number contain only digits and dashes.");
		}
		return employeeRepository.save(employee);
	}

	public Employees updateEmployee(Employees employee) {
		Employees emp = employeeRepository.getById(employee.getEmployeeId());
		if (emp == null) {
			throw new RestClientException("Employee with id " + employee.getEmployeeId() + " not found.");
		}
		if (employee.getFirstName() == null && employee.getLastName() == null) {
			throw new RestClientException("Employee firstname or lastname should not be null.");
		}

		if (employee.getFirstName().length() < 3) {
			throw new RestClientException("Employee firstname length must be more than 2 characters.");
		}

		if (employee.getLastName().length() < 3) {
			throw new RestClientException("Employee lastname length must be more than 2 characters.");
		}

		if (employee.getSalary() <= 0) {
			throw new RestClientException("Employee salary must be more than 0.");
		}

		if (employee.getPhoneNumber() == null || !isValidPhoneNumber(employee.getPhoneNumber())) {
			throw new RestClientException("Employee phone number contain only digits and dashes.");
		}
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(Integer id) {
		employeeRepository.deleteById(id);
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		boolean isValid = phoneNumber.matches("^[0-9-]*$");
		return isValid;
	}

	public Employees updateEmpDepartment(Integer emp_id, Integer dept_id) {
		Employees employee = employeeRepository.getById(emp_id);
		if (employee == null) {
			throw new RestClientException("Employee with id " + emp_id + " not found");
		}

		Departments department = departmentRepository.getById(dept_id);
		if (department == null) {
			throw new RestClientException("Department with id " + dept_id + " not found");
		}

		employee.setManagerId(department.getManagerId());
		employee.setDepartment(department);
		employeeRepository.save(employee);
		return employee;
	}
}

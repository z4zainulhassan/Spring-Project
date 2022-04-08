package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.assignment.entity.Employees;
import com.assignment.service.EmployeesService;

import io.swagger.annotations.*;

@Api(value = "EmployeesController", description = "Employee Management Spring Rest Api")
@RestController
@RequestMapping("/employees")
public class EmployeesController {

	@Autowired
	EmployeesService employeeService;

//	@ApiOperation(value = "Get Pagination List of Employees in the system.", tags = "getEmployees")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!!!"),
//			@ApiResponse(code = 404, message = "Not Found!!!") })
	@GetMapping()
	public Page<Employees> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "2") Integer pageSize,
			@RequestParam(defaultValue = "employeeId") String sortBy) {
		Page<Employees> employees = employeeService.getAllEmployees(pageNo, pageSize, sortBy);
		return employees;
	}

//	@ApiOperation(value = "Get Employee By Id in the system.", tags = "getEmployeeById")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!!!"),
//			@ApiResponse(code = 404, message = "Not Found!!!") })
	@GetMapping("/{id}")
	public Employees getEmployeeById(@PathVariable Integer id) {
		Employees emp = employeeService.getEmployeeById(id);
		return emp;
	}

//	@ApiOperation(value = "Post Employee Data in the system.", tags = "createEmployee")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!!!"),
//			@ApiResponse(code = 404, message = "Not Found!!!") })
//	@ResponseStatus(HttpStatus.CREATED) // send HTTP 201 instead of 200 as new object created
	@PostMapping
	public Employees createEmployee(@RequestBody Employees employee) {
		return employeeService.createEmployee(employee);
	}

//	@ApiOperation(value = "Put/Update Employee Data in the system.", tags = "updateEmployee")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!!!"),
//			@ApiResponse(code = 404, message = "Not Found!!!") })
	@PutMapping()
	public Employees updateEmployee(@RequestBody Employees employee) {
		return employeeService.updateEmployee(employee);
	}

//	@ApiOperation(value = "Delete Employee Data By Id in the system.", tags = "deleteEmployeeById")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!!!"),
//			@ApiResponse(code = 404, message = "Not Found!!!") })
	@DeleteMapping(value = "/{id}")
	public void deleteEmployeeById(@PathVariable("id") Integer id) {
		employeeService.deleteEmployee(id);
	}

//	@ApiOperation(value = "Update Employee Department Data By EmpId and DeptId in the system.", tags = "updateEmpDepartment")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!!!"),
//			@ApiResponse(code = 404, message = "Not Found!!!") })
	@PatchMapping("/{empId}/dept/{deptId}")
	public Employees updateEmpDepartment(@PathVariable("empId") Integer emp_id,
			@PathVariable("deptId") Integer dept_id) {
		return employeeService.updateEmpDepartment(emp_id, dept_id);
	}
}

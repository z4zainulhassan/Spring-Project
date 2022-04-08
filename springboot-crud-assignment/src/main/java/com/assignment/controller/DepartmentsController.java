package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.entity.Departments;
//import com.assignment.entity.Employees;
import com.assignment.service.DepartmentsService;

import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;

@Api(value = "DepartmentsController", description = "Department Management Spring Rest Api")
@RestController
@RequestMapping("/departments")
public class DepartmentsController {

	@Autowired
	DepartmentsService departmentService;

//	@ApiOperation(value = "Get Department By Id in the system.", tags = "getDepartmentById")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!!!"),
//			@ApiResponse(code = 404, message = "Not Found!!!") })
	@GetMapping(value = "/{id}")
	public Departments getDepartmentById(@PathVariable Integer id) {
		Departments dept = departmentService.getDepartmentById(id);
		return dept;
	}

//	@ApiOperation(value = "Get Pagination List of Departments in the system.", tags = "getDepartments")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!!!"),
//			@ApiResponse(code = 404, message = "Not Found!!!") })
	@GetMapping
	public Page<Departments> getAllDepartments(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "2") Integer pageSize,
			@RequestParam(defaultValue = "departmentId") String sortBy) {
		Page<Departments> departments = departmentService.getAllDepartments(pageNo, pageSize, sortBy);
		return departments;
	}
	
//	@ApiOperation(value = "Post Department Data in the system.", tags = "createDepartment")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!!!"),
//			@ApiResponse(code = 404, message = "Not Found!!!") })
//	@ResponseStatus(HttpStatus.CREATED) // send HTTP 201 instead of 200 as new object created
	@PostMapping
	public Departments createDepartment(@RequestBody Departments departments) {
		return departmentService.createDepartment(departments);
	}

//	@ApiOperation(value = "Put/Update Department Data in the system.", tags = "updateDepartment")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!!!"),
//			@ApiResponse(code = 404, message = "Not Found!!!") })
	@PutMapping()
	public Departments updateDepartment(@RequestBody Departments departments) {
		return departmentService.updateDepartment(departments);
	}
	
//	@ApiOperation(value = "Delete Department Data By Id in the system.", tags = "deleteDepartmentById")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!!!"),
//			@ApiResponse(code = 404, message = "Not Found!!!") })
	@DeleteMapping(value = "/{id}")
	public void deleteDepartmentById(@PathVariable("id") Integer id) {
		departmentService.deleteDepartmentById(id);
	}
	
//	@ApiOperation(value = "Update Department ManagerId Or Employee ManagerId in the system.", tags = "updateDepartmentOrEmployeeManagerIdById")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"), @ApiResponse(code = 403, message = "Forbidden!!!"),
//			@ApiResponse(code = 404, message = "Not Found!!!") })
	@PatchMapping("/{deptId}/managerid/{managerId}")
	public Departments updateDepartmentManagerIdOrEmployeeManagerIdByDeptId(@PathVariable("managerId") Integer managerId, @PathVariable("deptId") Integer deptId) {
		return departmentService.updateDepartmentOrEmployeeManagerIdById(deptId, managerId);
	}
}
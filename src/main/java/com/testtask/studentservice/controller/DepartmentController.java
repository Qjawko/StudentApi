package com.testtask.studentservice.controller;

import com.testtask.studentservice.exception.NotFoundException;
import com.testtask.studentservice.model.Department;
import com.testtask.studentservice.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping("/{id}")
	public Department get(@PathVariable Long id) {
		return departmentService.findById(id)
				.orElseThrow(() -> new NotFoundException("Cannot get group with id: " + id));
	}

	@GetMapping()
	public Iterable<Department> get() {
		return departmentService.findAll();
	}

	@GetMapping("/search")
	public Iterable<Department> search(@RequestParam("text") String text) {
		return departmentService.search(text);
	}

	@PostMapping
	public Department create(@RequestBody Department aDepartment) {
		return departmentService.create(aDepartment);
	}

	@PutMapping
	public Department update(Department aDepartment) {
		return departmentService.update(aDepartment);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		departmentService.deleteById(id);
	}
}

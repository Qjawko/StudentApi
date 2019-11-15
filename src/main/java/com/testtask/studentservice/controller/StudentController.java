package com.testtask.studentservice.controller;

import com.testtask.studentservice.exception.NotFoundException;
import com.testtask.studentservice.model.Student;
import com.testtask.studentservice.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/{id}")
	public Student get(@PathVariable Long id) {
		return studentService.findById(id)
				.orElseThrow(() -> new NotFoundException("Cannot get student with id: " + id));
	}

	@GetMapping()
	public Iterable<Student> get() {
		return studentService.findAll();
	}

	@GetMapping("/search")
	public Iterable<Student> search(@RequestParam("text") String text) {
		return studentService.search(text);
	}

	@PostMapping
	public Student create(@RequestBody Student student) {
		return studentService.create(student);
	}

	@PutMapping
	public Student update(Student student) {
		return studentService.update(student);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		studentService.deleteById(id);
	}
}

package com.testtask.studentservice.service;

import com.testtask.studentservice.model.Student;
import com.testtask.studentservice.repository.StudentRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {
	private final StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Optional<Student> findById(Long id) {
		return studentRepository.findById(id);
	}

	@Override
	public Iterable<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student create(Student student) {
		student.setId(null);
		return studentRepository.save(student);
	}

	@Override
	public Student update(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteById(Long id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Iterable<Student> search(String searchString) {
		return search(new HashSet<>(Arrays.asList(searchString.split("[\\ \\-\\+\\_\\*\\\\]"))));
	}

	@Override
	public Iterable<Student> search(Set<String> words) {
		return studentRepository.searchByName(words.stream().map(x -> " +" + x + "*").collect(Collectors.joining()));
	}
}

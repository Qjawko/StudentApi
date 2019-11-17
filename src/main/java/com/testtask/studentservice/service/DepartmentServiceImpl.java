package com.testtask.studentservice.service;

import com.testtask.studentservice.exception.InvalidRequestParameterException;
import com.testtask.studentservice.model.Department;
import com.testtask.studentservice.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentRepository departmentRepository;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Optional<Department> findById(Long id) {
		return departmentRepository.findById(id);
	}

	@Override
	public Iterable<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public Department create(Department aDepartment) {
		aDepartment.setId(null);
		return departmentRepository.save(aDepartment);
	}

	@Override
	public Department update(Department aDepartment) {
		if (aDepartment.getId() == null) throw new InvalidRequestParameterException("ID cannot be null!");
		return departmentRepository.save(aDepartment);
	}

	@Override
	public void deleteById(Long id) {
		departmentRepository.deleteById(id);
	}

	@Override
	public Iterable<Department> search(String searchString) {
		return search(new HashSet<>(Arrays.asList(searchString.split("[\\ \\-\\+\\_\\*\\\\]"))));
	}

	@Override
	public Iterable<Department> search(Set<String> words) {
		return departmentRepository.searchByName(words.stream().map(x -> " +" + x + "*").collect(Collectors.joining()));
	}
}

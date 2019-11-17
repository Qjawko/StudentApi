package com.testtask.studentservice.service;

import com.testtask.studentservice.exception.InvalidRequestParameterException;
import com.testtask.studentservice.model.Institution;
import com.testtask.studentservice.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InstitutionServiceImpl implements InstitutionService {
	private final InstitutionRepository institutionRepository;

	@Autowired
	public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
		this.institutionRepository = institutionRepository;
	}

	@Override
	public Optional<Institution> findById(Long id) {
		return institutionRepository.findById(id);
	}

	@Override
	public Iterable<Institution> findAll() {
		return institutionRepository.findAll();
	}

	@Override
	public Institution create(Institution institution) {
		institution.setId(null);
		return institutionRepository.save(institution);
	}

	@Override
	public Institution update(Institution institution) {
		if (institution.getId() == null) throw new InvalidRequestParameterException("ID cannot be null!");
		return institutionRepository.save(institution);
	}

	@Override
	public void deleteById(Long id) {
		institutionRepository.deleteById(id);
	}

	@Override
	public Iterable<Institution> search(String searchString) {
		return search(new HashSet<>(Arrays.asList(searchString.split("[\\ \\-\\+\\_\\*\\\\]"))));
	}

	@Override
	public Iterable<Institution> search(Set<String> words) {
		return institutionRepository.searchByName(words.stream().map(x -> " +" + x + "*").collect(Collectors.joining()));
	}
}

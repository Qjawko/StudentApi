package com.testtask.studentservice.service;

import com.testtask.studentservice.exception.InvalidRequestParameterException;
import com.testtask.studentservice.model.Group;
import com.testtask.studentservice.repository.GroupRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupServiceImpl implements GroupService {
	private final GroupRepository groupRepository;

	public GroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public Optional<Group> findById(Long id) {
		return groupRepository.findById(id);
	}

	@Override
	public Iterable<Group> findAll() {
		return groupRepository.findAll();
	}

	@Override
	public Group create(Group group) {
		group.setId(null);
		return groupRepository.save(group);
	}

	@Override
	public Group update(Group group) {
		if (group.getId() == null) throw new InvalidRequestParameterException("ID cannot be null!");
		return groupRepository.save(group);
	}

	@Override
	public void deleteById(Long id) {
		groupRepository.deleteById(id);
	}

	@Override
	public Iterable<Group> search(String searchString) {
		return search(new HashSet<>(Arrays.asList(searchString.split("[\\ \\-\\+\\_\\*\\\\]"))));
	}

	@Override
	public Iterable<Group> search(Set<String> words) {
		return groupRepository.searchByName(words.stream().map(x -> " +" + x + "*").collect(Collectors.joining()));
	}
}

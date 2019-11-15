package com.testtask.studentservice.controller;

import com.testtask.studentservice.exception.NotFoundException;
import com.testtask.studentservice.model.Group;
import com.testtask.studentservice.service.GroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group")
public class GroupController {
	private final GroupService groupService;

	public GroupController(GroupService groupService) {
		this.groupService = groupService;
	}

	@GetMapping("/{id}")
	public Group get(@PathVariable Long id) {
		return groupService.findById(id)
				.orElseThrow(() -> new NotFoundException("Cannot get group with id: " + id));
	}

	@GetMapping()
	public Iterable<Group> get() {
		return groupService.findAll();
	}

	@GetMapping("/search")
	public Iterable<Group> search(@RequestParam("text") String text) {
		return groupService.search(text);
	}

	@PostMapping
	public Group create(@RequestBody Group group) {
		return groupService.create(group);
	}

	@PutMapping
	public Group update(Group group) {
		return groupService.update(group);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		groupService.deleteById(id);
	}
}

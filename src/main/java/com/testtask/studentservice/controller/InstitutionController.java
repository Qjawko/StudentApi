package com.testtask.studentservice.controller;


import com.testtask.studentservice.exception.NotFoundException;
import com.testtask.studentservice.model.Institution;
import com.testtask.studentservice.service.InstitutionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/institution")
public class InstitutionController {
	private final InstitutionService institutionService;

	public InstitutionController(InstitutionService institutionService) {
		this.institutionService = institutionService;
	}

	@GetMapping("/{id}")
	public Institution get(@PathVariable Long id) {
		return institutionService.findById(id)
				.orElseThrow(() -> new NotFoundException("Cannot get institution with id: " + id));
	}

	@GetMapping()
	public Iterable<Institution> get() {
		return institutionService.findAll();
	}

	@GetMapping("/search")
	public Iterable<Institution> search(@RequestParam("text") String text) {
		return institutionService.search(text);
	}

	@PostMapping
	public Institution create(@RequestBody Institution institution) {
		return institutionService.create(institution);
	}

	@PutMapping
	public Institution update(@RequestBody Institution institution) {
		return institutionService.update(institution);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		institutionService.deleteById(id);
	}
}

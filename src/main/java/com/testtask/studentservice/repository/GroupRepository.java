package com.testtask.studentservice.repository;

import com.testtask.studentservice.model.Group;
import com.testtask.studentservice.model.Institution;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends CrudRepository<Group, Long> {
	@Query( value = "select * from 'Group' g where match 'name' against (:searchString in boolean mode)",
			nativeQuery = true)
	Iterable<Group> searchByName(@Param("searchString") String searchString);
}

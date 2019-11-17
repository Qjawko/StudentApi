package com.testtask.studentservice.repository;

import com.testtask.studentservice.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
	@Query( value = "select * from department d where match 'name' against (:searchString in boolean mode)",
			nativeQuery = true)
	Iterable<Department> searchByName(@Param("searchString") String searchString);
}

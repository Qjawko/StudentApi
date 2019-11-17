package com.testtask.studentservice.repository;

import com.testtask.studentservice.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
	@Query( value = "select * from Student s where match (secondName, firstName, patronymic) against (:searchString in boolean mode)",
			nativeQuery = true)
	Iterable<Student> searchByName(@Param("searchString") String searchString);
}

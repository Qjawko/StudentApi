package com.testtask.studentservice.repository;


import com.testtask.studentservice.model.Institution;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends CrudRepository<Institution, Long> {
	@Query( value = "select * from Institution i where match 'name' against (:searchString in boolean mode)",
			nativeQuery = true)
	Iterable<Institution> searchByName(@Param("searchString") String searchString);
}

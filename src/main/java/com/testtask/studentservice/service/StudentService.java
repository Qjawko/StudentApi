package com.testtask.studentservice.service;

import com.testtask.studentservice.model.Group;
import com.testtask.studentservice.model.Student;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public interface StudentService {
	Optional<Student> findById(Long id);
	Iterable<Student> findAll();
	Student create(Student student);
	Student update(Student student);
	void deleteById(Long id);

	/**
	 * Performs full-text search against searchString in boolean mode.
	 * @see <a href="https://dev.mysql.com/doc/refman/8.0/en/fulltext-boolean.html">Mysql Full text search</a>
	 * @param searchString String, containing numerals, alphabetical symbols and spaces, symbols * / - +
	 * @return Students that match
	 */
	Iterable<Student> search(String searchString);

	/**
	 * Performs full-text search against words in boolean mode.
	 * Words will be prepended with '+' character
	 * See {@link #search(String) search} method
	 * @param words Set of words to be matched against. Should be alphabetical or numeral, without delimiters and other symbols
	 * @return Students that match
	 */
	Iterable<Student> search(Set<String> words);
}

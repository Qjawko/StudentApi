package com.testtask.studentservice.service;

import com.testtask.studentservice.model.Group;
import com.testtask.studentservice.model.Institution;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public interface GroupService {
	Optional<Group> findById(Long id);
	Iterable<Group> findAll();
	Group create(Group group);
	Group update(Group group);
	void deleteById(Long id);

	/**
	 * Performs full-text search against searchString in boolean mode.
	 * @see <a href="https://dev.mysql.com/doc/refman/8.0/en/fulltext-boolean.html">Mysql Full text search</a>
	 * @param searchString String, containing numerals, alphabetical symbols and spaces, symbols * / - +
	 * @return Groups that match
	 */
	Iterable<Group> search(String searchString);

	/**
	 * Performs full-text search against words in boolean mode.
	 * Words will be prepended with '+' character
	 * See {@link #search(String) search} method
	 * @param words Set of words to be matched against. Should be alphabetical or numeral, without delimiters and other symbols
	 * @return Groups that match
	 */
	Iterable<Group> search(Set<String> words);
}

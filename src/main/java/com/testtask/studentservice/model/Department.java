package com.testtask.studentservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = "institution")
@EqualsAndHashCode(exclude = "institution")
@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	@JsonManagedReference
	Set<Student> students;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn
	Institution institution;
}

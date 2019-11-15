package com.testtask.studentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "group")
@EqualsAndHashCode(exclude = "group")
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String secondName;
	String firstName;
	String patronymic;

	@ManyToOne
	@JsonIgnore
	Group group;

	@SuppressWarnings("unused")
	public String getFullName() {
		return secondName + " " + firstName + " " + patronymic;
	}
}

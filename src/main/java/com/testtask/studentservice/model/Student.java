package com.testtask.studentservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "department")
@EqualsAndHashCode(exclude = "department")
//@Table(indexes = @Index(name = "FULLTEXT", columnList = "secondName, firstName, patronymic"))
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String secondName;
    String firstName;
    String patronymic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonBackReference
    Department department;

    @SuppressWarnings("unused")
    public String getFullName() {
        return secondName + " " + firstName + " " + patronymic;
    }
}

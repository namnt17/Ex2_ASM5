package com.example.ex2_asm5.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    @NonNull
    String name;

    @Column(name = "birth_day")
    @NonNull
    Date birthDay;

    @Column(name = "gender")
    @NonNull
    Boolean gender;

    @Column(name = "gpa")
    Float gpa;

    @Column(name = "ranking")
    String rank;

}

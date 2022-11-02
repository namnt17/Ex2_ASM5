package com.example.ex2_asm5.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    Long id = UUID.randomUUID().getLeastSignificantBits();

    @NonNull
    String name;

    @NonNull
    Date birthDay;

    @NonNull
    Boolean gender;

    Float gpa;

    String rank;

}

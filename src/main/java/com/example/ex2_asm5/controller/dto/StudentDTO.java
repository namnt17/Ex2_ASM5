package com.example.ex2_asm5.controller.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class StudentDTO {
    Long id;
    String name;
    Date birthDay;
    Boolean gender;
    Float gpa;
    String rank;
}

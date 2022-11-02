package com.example.ex2_asm5.controller.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class StudentRequest {

    @NotEmpty(message = "name cannot empty")
    String name;

    @NotEmpty(message = "birth day cannot empty")
    Date birthDay;

    @NotEmpty(message = "gender cannot empty")
    Boolean gender;

    Float gpa;
    String rank;
}

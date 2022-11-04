package com.example.ex2_asm5.controller.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class StudentRequest {

    @NotNull(message = "name must not null")
    String name;

    @NotNull(message = "birthday must not null")
    String birthDay;

    @NotNull(message = "gender must not null")
    boolean gender;

    @Size(min = 0, max = 10, message = "GPA must be between 0 to 10")
    float gpa;

    String rank;
}

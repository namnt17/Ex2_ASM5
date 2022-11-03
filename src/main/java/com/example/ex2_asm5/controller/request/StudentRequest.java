package com.example.ex2_asm5.controller.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class StudentRequest {

    String name;

    String birthDay;

    boolean gender;

    float gpa;
    String rank;
}

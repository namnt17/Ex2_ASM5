package com.example.ex2_asm5.controller.request;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.NotEmpty;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class NameRequest {

    @NotEmpty(message = "name cannot empty")
    String name;
}

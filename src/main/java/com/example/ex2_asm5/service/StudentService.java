package com.example.ex2_asm5.service;

import com.example.ex2_asm5.controller.dto.StudentDTO;
import com.example.ex2_asm5.controller.request.NameRequest;
import com.example.ex2_asm5.controller.request.StudentRequest;
import com.example.ex2_asm5.entity.Student;

import java.util.List;

public interface StudentService {

    Student create(StudentRequest student);

    Student update(StudentRequest request, Long id);

    List<Student> listAll(NameRequest request);

    void delete(Long id);
}

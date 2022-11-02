package com.example.ex2_asm5.service;

import com.example.ex2_asm5.controller.dto.StudentDTO;
import com.example.ex2_asm5.controller.request.NameRequest;
import com.example.ex2_asm5.controller.request.StudentRequest;

import java.text.ParseException;
import java.util.List;

public interface StudentService {

    StudentDTO create(StudentRequest student);

    StudentDTO update(StudentRequest request, Long id);

    List<StudentDTO> listAll(NameRequest request);

    void delete(Long id);
}

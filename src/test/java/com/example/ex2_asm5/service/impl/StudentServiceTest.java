package com.example.ex2_asm5.service.impl;

import com.example.ex2_asm5.controller.dto.StudentDTO;
import com.example.ex2_asm5.controller.request.NameRequest;
import com.example.ex2_asm5.controller.request.StudentRequest;
import com.example.ex2_asm5.entity.Student;
import com.example.ex2_asm5.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StudentServiceTest {

    @Autowired
    private StudentServiceImpl service;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Rollback()
    void create() {
        StudentRequest student = new StudentRequest();
        student.setName("Nam");
        student.setBirthDay("17/02/2000");
        student.setGender(true);
        student.setGpa((float) 3.4);
        student.setRank("Gioi");
        StudentDTO student1 = service.create(student);
        Student student2 = studentRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("This student is not found!"));
        assertEquals(student1.getName(), student2.getName());
    }



    @Test
    void update() {
        Student student = studentRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("This student is not found!"));
        StudentRequest request = new StudentRequest();
        request.setName("Duc");
        request.setGpa((float) 2.6);
        request.setRank("Kha");
        request.setBirthDay("28/10/2022");
        service.update(request, student.getId());
        assertEquals("Duc", student.getName());
    }

    @Test
    @Rollback
    void listAll() {
        int totalRecordsInDataBase = studentRepository.findAll().size();
        NameRequest nameRequest = new NameRequest();
        nameRequest.setName("");
        List<StudentDTO> students = service.listAll(nameRequest);
        int totalRecordsExpected = students.size();
        assertEquals(totalRecordsExpected, totalRecordsInDataBase);
    }

    @Test
    @Rollback
    void delete() {
        int totalRecordsInDataBase = studentRepository.findAll().size();
        service.delete(1L);
        NameRequest nameRequest = new NameRequest();
        nameRequest.setName("");
        List<StudentDTO> students = service.listAll(nameRequest);
        int totalRecordsExpected = students.size();
        assertNotEquals(totalRecordsInDataBase, totalRecordsExpected);
    }

}
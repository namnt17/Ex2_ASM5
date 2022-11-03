package com.example.ex2_asm5.service.impl;

import com.example.ex2_asm5.controller.dto.StudentDTO;
import com.example.ex2_asm5.controller.request.StudentRequest;
import com.example.ex2_asm5.entity.Student;
import com.example.ex2_asm5.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @Rollback
    void update() {
        Student student = studentRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("This student is not found!"));
        StudentRequest request = new StudentRequest();
        request.setName("Duc");
        request.setGpa((float) 2.6);
        request.setRank("Kha");
        request.setBirthDay("28/10/2022");
        service.update(request, student.getId());
        assertEquals(student.getName(), "Duc");
    }

    @Test
    void listAll() {
    }

    @Test
    void delete() {
    }

//    private Method sumWithMethodPrivate() throws NoSuchMethodException {
//        Method method = service.getClass().getDeclaredMethod("sum", Integer.class, Integer.class);
//        method.setAccessible(true);
//        return method;
//    }
//
//    @Test
//    void testMethodPrivate() throws Exception {
//        StudentServiceImpl service = new StudentServiceImpl();
//        assertEquals(5, sumWithMethodPrivate().invoke(service, 2,3));
//    }
}
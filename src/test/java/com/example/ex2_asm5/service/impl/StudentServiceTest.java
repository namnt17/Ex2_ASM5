package com.example.ex2_asm5.service.impl;

import com.example.ex2_asm5.controller.request.NameRequest;
import com.example.ex2_asm5.controller.request.StudentRequest;
import com.example.ex2_asm5.entity.Student;
import com.example.ex2_asm5.repository.StudentRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ContextConfiguration()
class StudentServiceTest {


    @InjectMocks
    private StudentServiceImpl service;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    List<Student> mockList;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void create() {
        String sDate1="31/12/1998";
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Student student = new Student();
        student.setId(1L);
        student.setName("Nhi");
        student.setBirthDay(date1);
        student.setGender(true);
        student.setGpa((float) 2.4);
        student.setRank("Trung binh");

        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setName("Nhi");
        studentRequest.setBirthDay("31/12/1998");
        studentRequest.setGender(true);
        studentRequest.setGpa((float) 2.4);
        studentRequest.setRank("Trung Binh");
        when(studentRepository.save(ArgumentMatchers.any(Student.class))).thenReturn(student);
        Student studentCreated = service.create(studentRequest);
        assertEquals(studentCreated.getName(), student.getName());
    }



    @Test
    void update() {
        String sDate1="31/12/1998";
        String sDate2="17/02/2000";
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Student student = new Student();
        student.setId(1L);
        student.setName("Nam");
        student.setBirthDay(date1);

        StudentRequest request = new StudentRequest();
        request.setName("Duc");
        request.setBirthDay("17/02/2000");
        given(studentRepository.findById(student.getId())).willReturn(Optional.of(student));
        service.update(request, student.getId());
        verify(studentRepository).save(student);
        verify(studentRepository).findById(student.getId());
    }

    @Test
    void listAll() {
        String sDate1="31/12/1998";
        String sDate2="17/02/2000";
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Student student1 = new Student(1L, "Nam", date1, true, (float) 3.4, "Gioi");
        Student student2 = new Student(2L, "Duc", date2, true, (float) 2.6, "Kha");
        mockList.add(student1);
        mockList.add(student2);
        Mockito.verify(mockList).add(student1);
        Mockito.verify(mockList).add(student2);

        given(studentRepository.findAll()).willReturn(mockList);
        NameRequest nameRequest = new NameRequest();
        nameRequest.setName(null);
        List<Student> students = service.listAll(nameRequest);
        assertEquals(students.size(), mockList.size());
        verify(studentRepository).findAll();
    }

    @Test
    void delete() {
        String sDate1="31/12/1998";
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Student student = new Student();
        student.setId(1L);
        student.setName("Nam");
        student.setBirthDay(date1);
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        service.delete(student.getId());
        verify(studentRepository).deleteById(student.getId());
    }

}
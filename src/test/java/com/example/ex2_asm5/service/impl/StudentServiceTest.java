package com.example.ex2_asm5.service.impl;

import com.example.ex2_asm5.controller.dto.StudentDTO;
import com.example.ex2_asm5.controller.request.NameRequest;
import com.example.ex2_asm5.controller.request.StudentRequest;
import com.example.ex2_asm5.entity.Student;
import com.example.ex2_asm5.repository.StudentRepository;
import com.example.ex2_asm5.repository.mapper.StudentMapper;
import com.example.ex2_asm5.repository.mapper.StudentMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@SpringBootTest
//@Transactional
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = {StudentMapper.class, StudentMapperImpl.class})
@ContextConfiguration()
class StudentServiceTest {

    @Spy
    @InjectMocks
    private StudentServiceImpl service;

    @Mock
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;



    @Test
    @Rollback
    void create() {
        // 1.create mock data
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
//        List<Student> mockStudents = new ArrayList<>(List.of(new Student(1L, "Nam", date1, true, (float) 3.4, "Gioi"),
//                                                            new Student(2L, "Duc", date2, true, (float) 2.6, "Kha")));
        List<Student> mockStudents = new ArrayList<>();
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

        StudentDTO studentCreated = service.create(studentRequest);
        assertEquals(studentCreated.getName(), student.getName());
        verify(studentRepository).save(student);
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
        List<Student> mockStudents = new ArrayList<>(List.of(new Student(1L, "Nam", date1, true, (float) 3.4, "Gioi"),
                                                            new Student(2L, "Duc", date2, true, (float) 2.6, "Kha")));

        given(studentRepository.findAll()).willReturn(mockStudents);
        NameRequest nameRequest = new NameRequest();
        nameRequest.setName("");
        List<StudentDTO> students = service.listAll(nameRequest);
        int totalRecordsExpected = students.size();
        assertArrayEquals(totalRecordsExpected, totalRecordsInDataBase);
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
package com.example.ex2_asm5.service.impl;

import com.example.ex2_asm5.controller.dto.StudentDTO;
import com.example.ex2_asm5.controller.request.NameRequest;
import com.example.ex2_asm5.controller.request.StudentRequest;
import com.example.ex2_asm5.entity.Student;
import com.example.ex2_asm5.repository.StudentRepository;
import com.example.ex2_asm5.repository.mapper.StudentMapper;
import com.example.ex2_asm5.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private  StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentServiceImpl() {
    }

    public Date convertStringToDate(String date){
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDTO create(StudentRequest request){
        Student newStudent = studentMapper.mapToEntity(request);
        studentRepository.save(newStudent);
        return studentMapper.mapToDTO(newStudent);
    }

    @Override
    public StudentDTO update(StudentRequest request, Long id){
        Student student = checkExitsStudent(id);
        updateOrCreate(student, request);
        studentRepository.save(student);
        return studentMapper.mapToDTO(student);
    }

    @Override
    public List<StudentDTO> listAll(NameRequest request) {
        List<Student> students = studentRepository.findAll();
        return students
                .stream()
                .filter(student -> request.getName().equalsIgnoreCase(student.getName()))
                .map(studentMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Student student = checkExitsStudent(id);
        studentRepository.delete(student);
    }

    public Student checkExitsStudent(Long id){
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("This Student is not found!!"));
    }

    public void updateOrCreate(Student student, StudentRequest request){
        student.setGender(request.getGender());
        student.setGpa(request.getGpa());
        student.setBirthDay(convertStringToDate(request.getBirthDay()));
        student.setName(request.getName());
        student.setRank(request.getRank());
    }


    private int sum(Integer a, Integer b) {
        return a +b;
    }


}

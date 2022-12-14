package com.example.ex2_asm5.service.impl;

import com.example.ex2_asm5.controller.request.NameRequest;
import com.example.ex2_asm5.controller.request.StudentRequest;
import com.example.ex2_asm5.entity.Student;
import com.example.ex2_asm5.repository.StudentRepository;
import com.example.ex2_asm5.repository.mapper.StudentMapper;
import com.example.ex2_asm5.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public static Date convertStringToDate(String date){
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student create(StudentRequest request){
        Student newStudent = new Student();
        newStudent.setGender(request.isGender());
        newStudent.setBirthDay(convertStringToDate(request.getBirthDay()));
        newStudent.setGpa(request.getGpa());
        newStudent.setName(request.getName());
        newStudent.setRank(request.getRank());
        studentRepository.save(newStudent);
        return newStudent;
    }

    @Override
    public Student update(StudentRequest request, Long id){
        Optional<Student> student = checkExitsStudent(id);
        updateOrCreate(student.get(), request);
        studentRepository.save(student.get());
        return student.get();
    }

    @Override
    public List<Student> listAll(NameRequest request) {
         return studentRepository.findAllByNameContaining(request.getName());
    }

    @Override
    public void delete(Long id) {
        Optional<Student> student = checkExitsStudent(id);
        studentRepository.deleteById(student.get().getId());
    }

    public Optional<Student> checkExitsStudent(Long id){
        return Optional.ofNullable(studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("This Student is not found!!")));
    }

    public void updateOrCreate(Student student, StudentRequest request){
        student.setGender(request.isGender());
        student.setGpa(request.getGpa());
        student.setBirthDay(convertStringToDate(request.getBirthDay()));
        student.setName(request.getName());
        student.setRank(request.getRank());
    }


}

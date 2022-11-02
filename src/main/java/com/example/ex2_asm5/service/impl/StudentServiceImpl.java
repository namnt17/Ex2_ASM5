package com.example.ex2_asm5.service.impl;

import com.example.ex2_asm5.controller.dto.StudentDTO;
import com.example.ex2_asm5.controller.request.NameRequest;
import com.example.ex2_asm5.controller.request.StudentRequest;
import com.example.ex2_asm5.entity.Student;
import lombok.AllArgsConstructor;
import com.example.ex2_asm5.repository.StudentRepository;
import com.example.ex2_asm5.repository.mapper.StudentMapper;
import com.example.ex2_asm5.service.StudentService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    @Override
    public StudentDTO create(StudentRequest request) {
        Student newStudent = new Student();
        updateOrCreate(newStudent, request);
        studentRepository.save(newStudent);
        return studentMapper.mapToDTO(newStudent);
    }

    @Override
    public StudentDTO update(StudentRequest request, Long id) {
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
        student.setBirthDay(request.getBirthDay());
        student.setName(request.getName());
        student.setRank(request.getRank());
    }
}

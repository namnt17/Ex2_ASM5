package com.example.ex2_asm5.controller;


import com.example.ex2_asm5.controller.request.NameRequest;
import com.example.ex2_asm5.controller.request.StudentRequest;
import com.example.ex2_asm5.entity.Student;
import com.example.ex2_asm5.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "/students")
@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("")
    public Student createStudent(@RequestBody @Valid @Validated StudentRequest request){
        return studentService.create(request);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody @Valid @Validated StudentRequest request, @PathVariable("id") Long id){
        return studentService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Long id){
        studentService.delete(id);
    }

    @GetMapping("/list")
    public List<Student> listStudents (@RequestBody NameRequest request){
        return studentService.listAll(request);
    }
}

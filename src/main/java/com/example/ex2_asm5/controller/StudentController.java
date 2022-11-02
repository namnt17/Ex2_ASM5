package com.example.ex2_asm5.controller;


import com.example.ex2_asm5.controller.dto.StudentDTO;
import com.example.ex2_asm5.controller.request.StudentRequest;
import com.example.ex2_asm5.controller.request.NameRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.ex2_asm5.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("")
    public StudentDTO createStudent(@RequestBody @Valid StudentRequest request){
        return studentService.create(request);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@RequestBody @Valid StudentRequest request, @PathVariable("id") Long id){
        return studentService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Long id){
        studentService.delete(id);
    }

    @GetMapping("/list")
    public List<StudentDTO> listStudents (@RequestBody @Valid NameRequest request){
        return studentService.listAll(request);
    }
}

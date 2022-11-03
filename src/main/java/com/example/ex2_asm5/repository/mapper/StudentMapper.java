package com.example.ex2_asm5.repository.mapper;

import com.example.ex2_asm5.controller.dto.StudentDTO;
import com.example.ex2_asm5.controller.request.StudentRequest;
import com.example.ex2_asm5.entity.Student;
import com.example.ex2_asm5.service.impl.StudentServiceImpl;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = {StudentServiceImpl.class})
public interface StudentMapper {

    @Named("mapToDTOs")
    StudentDTO mapToDTO (Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "birthDay", expression = "java(StudentServiceImpl.convertStringToDate(studentRequest.getBirthDay()))")
    Student mapToEntity (StudentRequest studentRequest);


    @IterableMapping(qualifiedByName = "mapToDTOs")
    List<StudentDTO> mapToDTOs (List<Student> students);

}

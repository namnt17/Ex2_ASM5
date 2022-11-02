package com.example.ex2_asm5.repository.mapper;

import com.example.ex2_asm5.controller.dto.StudentDTO;
import com.example.ex2_asm5.entity.Student;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {

    @Named("mapToDTOs")
    StudentDTO mapToDTO (Student student);

    @IterableMapping(qualifiedByName = "mapToDTOs")
    List<StudentDTO> mapToDTOs (List<Student> students);

}

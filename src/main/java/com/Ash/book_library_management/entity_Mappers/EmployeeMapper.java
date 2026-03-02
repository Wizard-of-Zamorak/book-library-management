package com.Ash.book_library_management.entity_Mappers;

import com.Ash.book_library_management.entity.Employee;
import com.Ash.book_library_management.entity_DTO.EmployeeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDTO toDto(Employee employee);
    Employee toEntity(EmployeeDTO employeeDTO);
}

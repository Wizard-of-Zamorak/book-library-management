package com.Ash.book_library_management.services;

import com.Ash.book_library_management.entity.Author;
import com.Ash.book_library_management.entity.Employee;
import com.Ash.book_library_management.entity.Role;
import com.Ash.book_library_management.entity_DTO.EmployeeDTO;
import com.Ash.book_library_management.entity_Mappers.EmployeeMapper;
import com.Ash.book_library_management.entity_Mappers.EmployeeMapperImpl;
import com.Ash.book_library_management.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {

//        Optional<Employee> employee = employeeRepository.findById(id);
//        return employee.map(employeeMapper::toDto);

        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) return null;
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee createdEmployee = employeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(createdEmployee);
        return employeeMapper.toDto(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployeeOpt = employeeRepository.findById(id);

        if (existingEmployeeOpt.isPresent()) {
            Employee existingEmployee = existingEmployeeOpt.get(); // unwrap Optional
            existingEmployee.setFirstName(employeeDTO.getFirstName());
            existingEmployee.setLastName(employeeDTO.getLastName());
            existingEmployee.setDateOfBirth(employeeDTO.getDateOfBirth());

            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return employeeMapper.toDto(updatedEmployee);
        }

        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}

package com.Ash.book_library_management.repository;

import com.Ash.book_library_management.entity.Employee;
import com.Ash.book_library_management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByRole(Role role);
}

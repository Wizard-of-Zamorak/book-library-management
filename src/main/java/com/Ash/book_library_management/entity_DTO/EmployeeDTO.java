package com.Ash.book_library_management.entity_DTO;

import com.Ash.book_library_management.entity.Role;

import java.time.LocalDate;

public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Role role;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String firstName, String lastName, Role role, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

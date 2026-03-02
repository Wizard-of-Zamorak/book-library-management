package com.Ash.book_library_management.entity_DTO;

import com.Ash.book_library_management.entity.Role;

public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Role role;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String firstName, String lastName, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

package com.Ash.book_library_management.services;

import com.Ash.book_library_management.entity.Role;
import com.Ash.book_library_management.entity_DTO.EmployeeDTO;
import com.Ash.book_library_management.entity_DTO.MemberDTO;

import java.util.List;

public interface MemberService {

    List<MemberDTO> getAllMembers();

    MemberDTO getMemberById(Long id);

    MemberDTO createMember(MemberDTO memberDTO);

    MemberDTO updateMember(Long id, MemberDTO memberDTO);

    void deleteMember(Long id);

}

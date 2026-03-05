package com.Ash.book_library_management.entity_Mappers;

import com.Ash.book_library_management.entity.Member;
import com.Ash.book_library_management.entity_DTO.MemberDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDTO toDto(Member member);

    Member toEntity(MemberDTO memberDTO);
}

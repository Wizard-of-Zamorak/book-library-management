package com.Ash.book_library_management.entity_Mappers;

import ch.qos.logback.core.model.ComponentModel;
import com.Ash.book_library_management.entity.Author;
import com.Ash.book_library_management.entity_DTO.AuthorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDto(Author author);
    Author toEntity(AuthorDTO authorDTO);
}

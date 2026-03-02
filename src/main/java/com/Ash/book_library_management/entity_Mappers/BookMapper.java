package com.Ash.book_library_management.entity_Mappers;


import com.Ash.book_library_management.entity.Book;
import com.Ash.book_library_management.entity_DTO.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDto(Book book);
    Book toEntity(BookDTO bookDTO);
}

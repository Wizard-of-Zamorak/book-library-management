package com.Ash.book_library_management.entity_Mappers;


import com.Ash.book_library_management.entity.Book;
import com.Ash.book_library_management.entity_DTO.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "author.id", target = "authorId")
    BookDTO toDto(Book book);

    @Mapping(source = "authorId", target = "author.id")
    Book toEntity(BookDTO bookDTO);
}

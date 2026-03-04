package com.Ash.book_library_management.services;

import com.Ash.book_library_management.entity_DTO.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDTO> getAllBooks();

    Optional<BookDTO> getBookById (Long id);

    BookDTO createBook(BookDTO bookDTO);

    BookDTO updateBook(Long id, BookDTO bookDTO);

    void deleteBook(Long id);

    List<BookDTO> getBooksByAuthorId(Long authorId);
}

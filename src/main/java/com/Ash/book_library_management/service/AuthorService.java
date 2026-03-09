package com.Ash.book_library_management.service;

import com.Ash.book_library_management.entity_DTO.AuthorDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<AuthorDTO> getAllAuthors();

    Optional<AuthorDTO> getAuthorById(Long id);

    AuthorDTO createAuthor(AuthorDTO authorDTO);

    AuthorDTO updateAuthor(long id, AuthorDTO authorDTO);

    void deleteAuthor(Long id);
}

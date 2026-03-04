package com.Ash.book_library_management.services;


import com.Ash.book_library_management.entity.Author;
import com.Ash.book_library_management.entity_DTO.AuthorDTO;
import com.Ash.book_library_management.entity_Mappers.AuthorMapper;
import com.Ash.book_library_management.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toDto) // convert entity to DTO
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorDTO> getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.map(authorMapper::toDto);
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author createdAuthor = authorMapper.toEntity(authorDTO);
        Author savedAuthor = authorRepository.save(createdAuthor);
        return authorMapper.toDto(savedAuthor);
    }

    @Override
    public AuthorDTO updateAuthor(long id, AuthorDTO authorDTO) {
        Optional<Author> existingAuthorOpt = authorRepository.findById(id);

        if (existingAuthorOpt.isPresent()) {
            Author existingAuthor = existingAuthorOpt.get(); // unwrap Optional
            existingAuthor.setFirstName(authorDTO.getFirstName());
            existingAuthor.setLastName(authorDTO.getLastName());
            existingAuthor.setDateOfBirth(authorDTO.getDateOfBirth());
            existingAuthor.setCountryOfOrigin(authorDTO.getCountryOfOrigin());

            Author updatedAuthor = authorRepository.save(existingAuthor);
            return authorMapper.toDto(updatedAuthor);
        }

        return null; // or handle "not found" case later
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}

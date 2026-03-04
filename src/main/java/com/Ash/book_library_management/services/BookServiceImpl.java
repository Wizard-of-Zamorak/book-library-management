package com.Ash.book_library_management.services;


import com.Ash.book_library_management.entity.Author;
import com.Ash.book_library_management.entity.Book;
import com.Ash.book_library_management.entity_DTO.BookDTO;
import com.Ash.book_library_management.entity_Mappers.BookMapper;
import com.Ash.book_library_management.repository.AuthorRepository;
import com.Ash.book_library_management.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;



    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(bookMapper::toDto);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book createdBook = bookMapper.toEntity(bookDTO);

        Optional<Author> authorOpt = authorRepository.findById(bookDTO.getAuthorId());
        authorOpt.ifPresent(createdBook::setAuthor);
        Book savedBook = bookRepository.save(createdBook);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Optional<Book> existingBookOpt = bookRepository.findById(id);

        if (existingBookOpt.isPresent()) {
            Book existingBook = existingBookOpt.get(); // unwrap Optional
            existingBook.setTitle(bookDTO.getTitle());

            Optional<Author> authorOpt = authorRepository.findById(bookDTO.getAuthorId());
            authorOpt.ifPresent(existingBook::setAuthor);
//            Author author = authorRepository.findById(bookDTO.getAuthorId()).orElse(null);
//            existingBook.setAuthor(author);

            existingBook.setGenre(bookDTO.getGenre());
            existingBook.setYear(bookDTO.getYear());

            Book updatedBook = bookRepository.save(existingBook);
            return bookMapper.toDto(updatedBook);
        }

        return null; // or handle "not found" case later
    }

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public List<BookDTO> getBooksByAuthorId(Long authorId) {
        return List.of();
    }
}

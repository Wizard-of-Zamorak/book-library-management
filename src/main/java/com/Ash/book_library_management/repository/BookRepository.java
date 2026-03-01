package com.Ash.book_library_management.repository;

import com.Ash.book_library_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

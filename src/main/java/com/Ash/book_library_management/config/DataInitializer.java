package com.Ash.book_library_management.config;

import com.Ash.book_library_management.entity.*;
import com.Ash.book_library_management.repository.AuthorRepository;
import com.Ash.book_library_management.repository.BookRepository;
import com.Ash.book_library_management.repository.EmployeeRepository;
import com.Ash.book_library_management.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final EmployeeRepository employeeRepository;
    private final MemberRepository memberRepository;

    public DataInitializer(AuthorRepository authorRepository,
                           BookRepository bookRepository,
                           EmployeeRepository employeeRepository,
                           MemberRepository memberRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.employeeRepository = employeeRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Prevent duplicate entries
        if (authorRepository.count() > 0 || employeeRepository.count() > 0) {
            System.out.println("Data already exists. Skipping initialization.");
            return;
        }

        // --- Authors ---
        Author author1 = new Author("J.K.", "Rowling", LocalDate.of(1965, 7, 31), "United Kingdom");
        Author author2 = new Author("George R.R.", "Martin", LocalDate.of(1948, 9, 20), "United States");
        Author author3 = new Author("Robert", "Martin", LocalDate.of(1952, 12, 5), "United States");
        Author author4 = new Author("Agatha", "Christie", LocalDate.of(1890, 9, 15), "United Kingdom");
        Author author5 = new Author("Stephen", "King", LocalDate.of(1947, 9, 21), "United States");
        Author author6 = new Author("Isaac", "Asimov", LocalDate.of(1920, 1, 2), "Russia");

        authorRepository.saveAll(Arrays.asList(author1, author2, author3, author4, author5, author6));

        // --- Books ---
        bookRepository.saveAll(Arrays.asList(
                new Book("Harry Potter and the Sorcerer's Stone", author1, "Fantasy", 1997, 3),
                new Book("Harry Potter and the Chamber of Secrets", author1, "Fantasy", 1998, 2),
                new Book("Harry Potter and the Prisoner of Azkaban", author1, "Fantasy", 1999, 4),

                new Book("A Game of Thrones", author2, "Fantasy", 1996, 1),
                new Book("A Clash of Kings", author2, "Fantasy", 1998, 3),
                new Book("A Storm of Swords", author2, "Fantasy", 2000, 2),
                new Book("A Feast for Crows", author2, "Fantasy", 2005, 4),

                new Book("Clean Code", author3, "Programming", 2008, 2),
                new Book("Clean Architecture", author3, "Programming", 2017, 3),
                new Book("The Pragmatic Programmer", author3, "Programming", 1999, 1),

                new Book("Murder on the Orient Express", author4, "Mystery", 1934, 2),
                new Book("Death on the Nile", author4, "Mystery", 1937, 4),
                new Book("And Then There Were None", author4, "Mystery", 1939, 3),

                new Book("The Shining", author5, "Horror", 1977, 2),
                new Book("It", author5, "Horror", 1986, 1),
                new Book("Misery", author5, "Horror", 1987, 3),

                new Book("Foundation", author6, "Sci-Fi", 1951, 4),
                new Book("I, Robot", author6, "Sci-Fi", 1950, 2),
                new Book("The Caves of Steel", author6, "Sci-Fi", 1953, 3)
        ));

        // --- Members ---
        Member member1 = new Member("John", "Doe", LocalDate.of(1990, 5, 10), true);
        Member member2 = new Member("Jane", "Smith", LocalDate.of(1985, 8, 22), true);
        Member member3 = new Member("Alice", "Johnson", LocalDate.of(2000, 3, 15), false);

        memberRepository.saveAll(Arrays.asList(member1, member2, member3));

        // --- Employees ---
        Employee emp1 = new Employee("Alice", "Johnson", Role.LIBRARIAN, LocalDate.of(1985, 4, 12));
        Employee emp2 = new Employee("Bob", "Smith", Role.MANAGER, LocalDate.of(1978, 6, 23));
        Employee emp3 = new Employee("Carol", "Williams", Role.ADMIN, LocalDate.of(1990, 2, 14));

        employeeRepository.saveAll(Arrays.asList(emp1, emp2, emp3));

        System.out.println("Sample data initialized successfully!");
    }
}

package com.DiditalLibrarySystem.service;

import com.DiditalLibrarySystem.entity.BookEntity;
import com.DiditalLibrarySystem.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    private BookEntity book;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();

        book = new BookEntity();
        book.setBookId("101");
        book.setTitle("Spring Boot Guide");
        book.setAuthor("Sahil");
        book.setGenre("Technology");
        book.setAvailabilityStatus("Available");

        bookRepository.save(book);
    }

    @Test
    void testGetAllBooks() {
        List<BookEntity> books = bookService.getAllBooks();
        assertFalse(books.isEmpty());
    }

    @Test
    void testGetBookById_Success() {
        Optional<BookEntity> foundBook = bookService.getBookById("101");

        assertTrue(foundBook.isPresent());
        assertEquals("Spring Boot Guide", foundBook.get().getTitle());
    }

    @Test
    void testAddBook() {
        BookEntity newBook = new BookEntity();
        newBook.setBookId("102");
        newBook.setTitle("Java Basics");
        newBook.setAuthor("Jane");
        newBook.setGenre("Programming");
        newBook.setAvailabilityStatus("Available");

        BookEntity savedBook = bookService.addBook(newBook);

        assertNotNull(savedBook);
        assertEquals("Java Basics", savedBook.getTitle());
    }

    @Test
    void testUpdateBook() {
        BookEntity updatedBook = new BookEntity();
        updatedBook.setTitle("Updated Spring Boot");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setGenre("Updated Genre");
        updatedBook.setAvailabilityStatus("Checked Out");

        BookEntity book = bookService.updateBook("101", updatedBook);

        assertNotNull(book);
        assertEquals("Updated Spring Boot", book.getTitle());
    }

    @Test
    void testDeleteBook() {
        bookService.deleteBook("101");
        Optional<BookEntity> foundBook = bookService.getBookById("101");

        assertFalse(foundBook.isPresent());
    }
}


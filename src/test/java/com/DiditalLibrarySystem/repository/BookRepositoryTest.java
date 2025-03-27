package com.DiditalLibrarySystem.repository;

import com.DiditalLibrarySystem.entity.BookEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private BookEntity book;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll(); // Clean up before tests

        book = new BookEntity();
        book.setBookId("101");
        book.setTitle("Spring Boot Guide");
        book.setAuthor("Sahil");
        book.setGenre("Technology");
        book.setAvailabilityStatus("Available");

        bookRepository.save(book);
    }

    @Test
    void testFindByBookId_Success() {
        Optional<BookEntity> foundBook = bookRepository.findByBookId("101");

        assertTrue(foundBook.isPresent());
        assertEquals("Spring Boot Guide", foundBook.get().getTitle());
    }

    @Test
    void testFindByBookId_NotFound() {
        Optional<BookEntity> foundBook = bookRepository.findByBookId("999");

        assertFalse(foundBook.isPresent());
    }

    @Test
    void testDeleteBook() {
        bookRepository.deleteByBookId("101");
        Optional<BookEntity> foundBook = bookRepository.findByBookId("101");

        assertFalse(foundBook.isPresent());
    }
}


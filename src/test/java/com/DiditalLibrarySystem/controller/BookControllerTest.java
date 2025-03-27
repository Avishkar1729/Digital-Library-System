package com.DiditalLibrarySystem.controller;

import com.DiditalLibrarySystem.entity.BookEntity;
import com.DiditalLibrarySystem.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.springframework.test.context.junit.jupiter.SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll(); // Clear database before tests

        baseUrl = "http://localhost:" + port + "/api/books";

        BookEntity book = new BookEntity();
        book.setBookId("101");
        book.setTitle("Spring Boot Guide");
        book.setAuthor("Sahil");
        book.setGenre("Technology");
        book.setAvailabilityStatus("Available");

        bookRepository.save(book);
    }

    @Test
    void testGetAllBooks() {
        ResponseEntity<BookEntity[]> response = restTemplate.getForEntity(baseUrl, BookEntity[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        BookEntity[] books = response.getBody();
        assertNotNull(books);
        assertEquals(1, books.length);
    }

    @Test
    void testGetBookById() {
        ResponseEntity<BookEntity> response = restTemplate.getForEntity(baseUrl + "/id/101", BookEntity.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        BookEntity book = response.getBody();
        assertNotNull(book);
        assertEquals("Spring Boot Guide", book.getTitle());
    }

    @Test
    void testGetBookByTitle() {
        ResponseEntity<BookEntity> response = restTemplate.getForEntity(baseUrl + "/title/Spring Boot Guide", BookEntity.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        BookEntity book = response.getBody();
        assertNotNull(book);
        assertEquals("Sahil", book.getAuthor());
    }

    @Test
    void testAddBook() {
        BookEntity newBook = new BookEntity();
        newBook.setBookId("102");
        newBook.setTitle("Java Basics");
        newBook.setAuthor("Jane");
        newBook.setGenre("Programming");
        newBook.setAvailabilityStatus("Available");

        ResponseEntity<BookEntity> response = restTemplate.postForEntity(baseUrl, newBook, BookEntity.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        BookEntity book = response.getBody();
        assertNotNull(book);
        assertEquals("Java Basics", book.getTitle());
    }

    @Test
    void testUpdateBook() {
        BookEntity updatedBook = new BookEntity();
        updatedBook.setTitle("Updated Spring Boot");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setGenre("Updated Genre");
        updatedBook.setAvailabilityStatus("Checked Out");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BookEntity> requestEntity = new HttpEntity<>(updatedBook, headers);

        ResponseEntity<BookEntity> response = restTemplate.exchange(baseUrl + "/101", HttpMethod.PUT, requestEntity, BookEntity.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        BookEntity book = response.getBody();
        assertNotNull(book);
        assertEquals("Updated Spring Boot", book.getTitle());
    }

    @Test
    void testDeleteBook() {
        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/101", HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<BookEntity> findResponse = restTemplate.getForEntity(baseUrl + "/id/101", BookEntity.class);
        assertEquals(HttpStatus.NOT_FOUND, findResponse.getStatusCode());
    }
}


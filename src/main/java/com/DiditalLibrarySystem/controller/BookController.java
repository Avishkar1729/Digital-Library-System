package com.DiditalLibrarySystem.controller;

import com.DiditalLibrarySystem.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.DiditalLibrarySystem.service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/id/{bookId}")
    public Optional<BookEntity> getBookById(@PathVariable String bookId) {
        return bookService.getBookById(bookId);
    }

    @GetMapping("/title/{bookTitle}")
    public Optional<BookEntity> getBookByTitle(@PathVariable String bookTitle) {
        return bookService.getBookByTitle(bookTitle);
    }

    @PostMapping
    public BookEntity addBook(@RequestBody BookEntity book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{bookId}")
    public BookEntity updateBook(@PathVariable String bookId, @RequestBody BookEntity bookDetails) {
        return bookService.updateBook(bookId, bookDetails);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
        return "Book deleted successfully";
    }
}

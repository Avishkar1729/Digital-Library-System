package com.DiditalLibrarySystem.service;

import com.DiditalLibrarySystem.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.DiditalLibrarySystem.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<BookEntity> getBookById(String bookId) {
        return bookRepository.findByBookId(bookId);
    }

    public Optional<BookEntity> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public BookEntity addBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public BookEntity updateBook(String bookId, BookEntity bookDetails) {
        return bookRepository.findByBookId(bookId).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setGenre(bookDetails.getGenre());
            book.setAvailabilityStatus(bookDetails.getAvailabilityStatus());
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void deleteBook(String id) {
        bookRepository.deleteByBookId(id);
    }
}

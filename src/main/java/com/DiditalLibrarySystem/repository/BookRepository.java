package com.DiditalLibrarySystem.repository;

import com.DiditalLibrarySystem.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<BookEntity, String> {
    Optional<BookEntity> findByBookId(String bookId);
    Optional<BookEntity> findByTitle(String title);
    Optional<BookEntity> deleteByBookId(String bookId);



}

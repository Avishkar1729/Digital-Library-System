package com.DiditalLibrarySystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book_data")
@Data
@NoArgsConstructor
public class BookEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    @NonNull
    private String bookId;

    @NonNull
    private String title;

    @NonNull
    private String author;

    private String genre;

    private String availabilityStatus;

}

package com.backend.test.bookstore.dto;

import com.backend.test.bookstore.entity.Book.AvailabilityStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewBookDTO {
    private Integer bookId;
    private String title;  // Renamed from bookName to match requirements
    private String author;
    private String genre;  // Added per requirements
    private Integer price;
    private AvailabilityStatus availabilityStatus;  // Added per requirements

    // Optional fields retained from original implementation:
    private Integer originPrice;
    private String description;
    private String imageUrl;
    private Integer inventory;
}

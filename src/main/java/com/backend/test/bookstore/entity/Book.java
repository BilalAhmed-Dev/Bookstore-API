package com.backend.test.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "books")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "bookId")
public class Book {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name="title") // Maps to "title" in requirements
    private String title; // Consider renaming to 'title' for clarity

    @Column(name="author")
    private String author;

    @Column(name="genre") // Added to meet requirements
    private String genre;

    @Column(name="price")
    private Integer price;

    @Column(name="availability_status") // Added to meet requirements
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    // Optional fields (retained from original implementation):
    @Column(name="origin_price")
    private Integer originPrice;

    @Column(name="inventory")
    private Integer inventory;

    @Column(name="description")
    private String description;

    @Column(name="image_url")
    private String imageUrl;

    public enum AvailabilityStatus {
        IN_STOCK,
        OUT_OF_STOCK
    }
}

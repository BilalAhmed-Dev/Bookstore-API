package com.backend.test.bookstore.dao;

import com.backend.test.bookstore.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> searchBooks(String title, String author, String genre);
    Book findOne(Integer id);
    List<Book> getBooks();
    void saveBook(Book book);
    void delete(Integer bookId);
}

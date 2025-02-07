package com.backend.test.bookstore.service;

import com.backend.test.bookstore.dto.NewBookDTO;
import com.backend.test.bookstore.entity.Book;
import com.backend.test.bookstore.utils.msgutils.Msg;

import java.util.List;

public interface BookService {

    List<Book> searchBooks(String title, String author, String genre);
    Book findBookById(Integer id);
    List<Book> getBooks();

    Msg addBook(NewBookDTO newBookDTO);

    Msg deleteBook(Integer bookId);

    Msg editBook(NewBookDTO newBookDTO);

}

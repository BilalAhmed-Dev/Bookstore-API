package com.backend.test.bookstore.controller;

import com.backend.test.bookstore.dto.NewBookDTO;
import com.backend.test.bookstore.entity.Book;
import com.backend.test.bookstore.service.BookService;
import com.backend.test.bookstore.utils.msgutils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books") // Base path for book-related endpoints
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getBooks")
    public List<Book> getBooks() {
        System.out.println("getBooks");
        return bookService.getBooks();
    }

    @GetMapping("/getBook")
    public Book getBook(@RequestParam("bookId") Integer bookId){
        System.out.println("getBook");
        return bookService.findBookById(bookId);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre
    ) {
        return bookService.searchBooks(title, author, genre);
    }

    @PostMapping("/addBook")
    public Msg addBook(@RequestBody NewBookDTO newBookDTO){
        System.out.println("addBook");
        return bookService.addBook(newBookDTO);
    }

    @DeleteMapping("/deleteBook")
    public Msg deleteBook(@RequestParam("bookId") Integer bookId){
        System.out.println("deleteBook");
        return bookService.deleteBook(bookId);
    }

    @PutMapping("/editBook")
    public Msg editBook(@RequestBody NewBookDTO newBookDTO){
        System.out.println("editBook");
        return bookService.editBook(newBookDTO);
    }
}

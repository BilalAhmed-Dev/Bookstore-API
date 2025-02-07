package com.backend.test.bookstore.daoimpl;
import com.backend.test.bookstore.dao.BookDao;
import com.backend.test.bookstore.entity.Book;
import com.backend.test.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findOne(Integer id){
        return bookRepository.getOne(id);
    }
    @Override
    public List<Book> searchBooks(String title, String author, String genre) {
        return bookRepository.searchBooks(title, author, genre);
    }

    @Override
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(Integer bookId) {
        bookRepository.deleteById(bookId);
    }
}

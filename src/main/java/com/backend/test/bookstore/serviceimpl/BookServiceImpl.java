package com.backend.test.bookstore.serviceimpl;

import com.backend.test.bookstore.dao.BookDao;
import com.backend.test.bookstore.dto.NewBookDTO;
import com.backend.test.bookstore.entity.Book;
import com.backend.test.bookstore.service.BookService;

import com.backend.test.bookstore.utils.Msg;
import com.backend.test.bookstore.utils.MsgUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao, JwtServiceImpl jwtService) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> searchBooks(String title, String author, String genre) {
        return bookDao.searchBooks(title, author, genre);
    }

    @Override
    public Book findBookById(Integer id){
        return bookDao.findOne(id);
    }
    @Override
    public List<Book> getBooks(){
        return bookDao.getBooks();
    }

    @Override
    public Msg addBook(NewBookDTO newBookDTO) {

            Book newBook= new Book();
            saveBook(newBookDTO, newBook);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_ADDBOOK);

    }

    @Override
    public Msg editBook(NewBookDTO newBookDTO) {
            Book book=bookDao.findOne(newBookDTO.getBookId());
            saveBook(newBookDTO, book);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_EDITBOOK);

    }

    private void saveBook(NewBookDTO newBookDTO, Book book) {
        book.setTitle(newBookDTO.getTitle());
        book.setAuthor(newBookDTO.getAuthor());
        book.setDescription(newBookDTO.getDescription());
        book.setInventory(newBookDTO.getInventory());
        book.setImageUrl(newBookDTO.getImageUrl());
        book.setPrice(newBookDTO.getPrice());
        book.setOriginPrice(newBookDTO.getOriginPrice());
        bookDao.saveBook(book);
    }

    @Override
    public Msg deleteBook(Integer bookId) {
            bookDao.delete(bookId);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_DELETEBOOK);

    }
}

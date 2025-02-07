package com.backend.test.bookstore.serviceimpl;

import com.backend.test.bookstore.constant.Constant;
import com.backend.test.bookstore.dao.BookDao;
import com.backend.test.bookstore.dto.NewBookDTO;
import com.backend.test.bookstore.entity.Book;
import com.backend.test.bookstore.service.BookService;

import com.backend.test.bookstore.utils.msgutils.Msg;
import com.backend.test.bookstore.utils.msgutils.MsgUtil;
import com.backend.test.bookstore.utils.sessionutils.SessionUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

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
        JSONObject auth = SessionUtil.getAuth();
        if(auth != null && Objects.equals(auth.getString(Constant.USER_TYPE), "admin")){
            Book newBook=new Book();
            saveBook(newBookDTO, newBook);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_ADDBOOK);
        }
        else {
            return MsgUtil.makeMsg(MsgUtil.ERROR,MsgUtil.ERROR_ADDBOOK);
        }
    }

    @Override
    public Msg editBook(NewBookDTO newBookDTO) {
        JSONObject auth = SessionUtil.getAuth();
        if(auth != null && Objects.equals(auth.getString(Constant.USER_TYPE), "admin")){
            Book book=bookDao.findOne(newBookDTO.getBookId());
            saveBook(newBookDTO, book);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_EDITBOOK);
        }
        else {
            return MsgUtil.makeMsg(MsgUtil.ERROR,MsgUtil.ERROR_EDITBOOK);
        }
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
        JSONObject auth = SessionUtil.getAuth();
        if(auth != null && Objects.equals(auth.getString(Constant.USER_TYPE), "admin")){
            bookDao.delete(bookId);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_DELETEBOOK);
        }
        else{
            return MsgUtil.makeMsg(MsgUtil.ERROR,MsgUtil.ERROR_DELETEBOOK);
        }
    }
}

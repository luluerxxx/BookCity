package main.java.com.bnuz.service;

import main.java.com.bnuz.pojo.Book;
import main.java.com.bnuz.pojo.Page;

import java.util.List;

public interface BookService {
    public void addBook(Book book);
    public void deleteBookById(Integer id);
    public void updateBook(Book book);
    public  Book queryBookById(Integer id);
    public List<Book> queryBooks();
    public Page<Book> page(int pageNo,int pageSize);
}

package edu.zyh.service;

import com.github.pagehelper.PageInfo;
import edu.zyh.domain.Book;

import java.util.List;

public interface BookService {
    Book findBookById(int id);

    double getBookPriceById(List<Integer> bookId, List<Integer> bookNum);

    List<Book> getBookByBookNameOrAuthor(String[] strings, int pageNum, int pageSize);

    int insertNewBook(Book newBook);

    int delBook(int bookId);

    PageInfo<Book> getAllBookByPage(int pageNum, int pageSize);
}

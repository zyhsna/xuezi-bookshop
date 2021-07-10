package edu.zyh.controller;

import com.github.pagehelper.PageInfo;
import edu.zyh.domain.Book;
import edu.zyh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/findBookById")
    public Book findBookById(int id, HttpServletRequest request) {
        Book book = bookService.findBookById(id);
        String serverName = request.getServerName();
        int port = request.getServerPort();
        return book;
    }

    @RequestMapping("/getBookPriceById")
    double getBookPriceById(@RequestParam("bookId") List<Integer> bookId, @RequestParam("purchaserId") String purchaserId, @RequestParam("bookNum") List<Integer> bookNum) {
        double priceById = bookService.getBookPriceById(bookId, bookNum);
        return priceById;
    }

    @RequestMapping("/getBookByBooknameOrAuthor")
    List<Book> getBookByBookNameOrAuthor(String Info, Integer pageNum, Integer pageSize) {
        String[] strings = Info.split(" ");
        List<Book> bookByBookNameOrAuthor = bookService.getBookByBookNameOrAuthor(strings, pageNum, pageSize);
        return bookByBookNameOrAuthor;
    }

    @RequestMapping("/insetNewBook")
    int insertNewBook(@RequestBody Book newBook) {
        int result = bookService.insertNewBook(newBook);
        return result;
    }


    @RequestMapping("/getAllBookByPage")
    List<Book> getAllBookByPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        PageInfo<Book> bookByPage = bookService.getAllBookByPage(pageNum, pageSize);
        List<Book> bookList = bookByPage.getList();
        return bookList;
    }

    @RequestMapping("/delBook")
    int delBook(int bookId) {
        int result = bookService.delBook(bookId);
        return result;
    }
}

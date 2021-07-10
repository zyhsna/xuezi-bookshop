package edu.zyh.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zyh.dao.BookMapper;
import edu.zyh.domain.Book;
import edu.zyh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book findBookById(int id) {
        return bookMapper.findBookById(id);
    }

    @Override
    public double getBookPriceById(List<Integer> bookId, List<Integer> bookNum) {
        double TotalFee = 0;
        for (int i = 0; i < bookId.size(); i++) {
            double bookPriceById = bookMapper.getBookPriceById(bookId.get(i));
            TotalFee += bookPriceById * bookNum.get(i);
        }
        return TotalFee;
    }

    @Override
    public List<Book> getBookByBookNameOrAuthor(String[] strings, int pageNum, int PageSize) {
        String bookName = strings[0];
        bookName = "%" + bookName + "%";
        String author = "%";
        if (strings.length > 1) {
            author = strings[1];
        }
        author = "%" + author + "%";
        try{
            List<Book> allBook =  bookMapper.getBookByBookNameOrAuthor(bookName, author);
            PageInfo<Book> pageInfo = new PageInfo(allBook);
            return pageInfo.getList();
        }
        finally {
            PageHelper.clearPage();
        }

    }

    @Override
    public int insertNewBook(Book newBook) {
        int result = bookMapper.insertNewBook(newBook);
        return result;
    }

    @Override
    public int delBook(int bookId) {
        int result = bookMapper.delBook(bookId);
        return result;
    }

    @Override
    public PageInfo<Book> getAllBookByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        try{
            List<Book> allBook = bookMapper.getAllBook();
            PageInfo<Book> pageInfo = new PageInfo(allBook);
            return pageInfo;
        }
        finally {
            PageHelper.clearPage();
        }
    }
}
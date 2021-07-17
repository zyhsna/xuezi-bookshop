package edu.zyh.controller;

import com.github.pagehelper.PageInfo;
import edu.zyh.domain.Book;
import edu.zyh.domain.JsonData;
import edu.zyh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/findBookById")
    public JsonData findBookById(int bookId) {
        Book book = bookService.findBookById(bookId);

        return JsonData.buildSuccess(book);
    }

    private Book findBookByIdForBooksList(int bookId) {
        Book book = bookService.findBookById(bookId);
        return book;
    }

    @RequestMapping("/findBooksByBookIds")
    public JsonData findBooksByBookIds(Integer[] bookId){
        System.out.println(Arrays.toString(bookId));
        List<Book> result = new LinkedList<>();
        for (Integer integer : bookId) {
            result.add(findBookByIdForBooksList(integer));
        }
        return JsonData.build(0, result);
    }


    @RequestMapping("/findBookByIdForOrder")
    public Book findBookByIdForOrder(int bookId) {
        Book book = bookService.findBookByIdForOrder(bookId);
        return book;
    }


    @RequestMapping("/getBookPriceById")
    double getBookPriceById(@RequestParam("bookId") List<Integer> bookId, @RequestParam("purchaserId") String purchaserId, @RequestParam("bookNum") List<Integer> bookNum) {
        double priceById = bookService.getBookPriceById(bookId, bookNum);
        return priceById;
    }

    @RequestMapping(value = "/findBookCoverImgById")
    public void  getBookCoverImage(int bookId, HttpServletResponse response) throws IOException {
        ServletOutputStream os = null;
        String source_prefix = "book-service/src/main/resources/image/";
        String source_suffix = ".jpg";
        String source = source_prefix + bookId + source_suffix;
        try {
//        读取图片
            BufferedImage image = ImageIO.read(new FileInputStream(new File(source)));
            response.setContentType("image/jpg");
            os = response.getOutputStream();
            if (image != null) {
                ImageIO.write(image, "png", os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }
    }

    @RequestMapping("/getBookByBookNameOrAuthor")
    public JsonData getBookByBookNameOrAuthor(String Info, Integer pageNum, Integer pageSize) {
        String[] strings = Info.split(" ");
        List<Book> bookByBookNameOrAuthor = bookService.getBookByBookNameOrAuthor(strings, pageNum, pageSize);
        return JsonData.build(0,bookByBookNameOrAuthor);
    }

    @RequestMapping("/getSearchedBookNum")
    public JsonData getSearchedBookNum(String Info) {
        String[] strings = Info.split(" ");
        int bookByBookNameOrAuthor = bookService.getSearchedBookNum(strings);
        return JsonData.build(0,bookByBookNameOrAuthor);
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

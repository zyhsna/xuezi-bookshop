package edu.zyh.service;

import edu.zyh.domain.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "book-service")
public interface BookService {

    @GetMapping("/api/v1/book/findBookById")
    Book findBookById(@RequestParam("id") int id);


    @RequestMapping("api/v1/book/getBookPriceById")
    double getBookPriceById(@RequestParam("bookId") List<Integer> bookId, @RequestParam("purchaserId") String purchaserId, @RequestParam("bookNum") List<Integer> bookNum);
}

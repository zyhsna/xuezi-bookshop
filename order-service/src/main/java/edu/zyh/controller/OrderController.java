package edu.zyh.controller;


import edu.zyh.domain.Book;
import edu.zyh.domain.BookOrder;
import edu.zyh.service.BookService;
import edu.zyh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pri/order")
public class OrderController {

    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;
    
    @RequestMapping("/makeOrder")
    public Object makeOrder(@RequestParam("bookId") List<Integer>bookId, @RequestParam("purchaserId") String purchaserId, @RequestParam("bookNum")List<Integer> bookNum) {
        double priceById = bookService.getBookPriceById(bookId, purchaserId, bookNum);
        int result = orderService.makeOrder(priceById, purchaserId, bookId, bookNum);
        return result;
    }

    @RequestMapping("/getBook")
    public void getBook(int bookId){
        Book bookById = bookService.findBookById(bookId);
        System.out.println(bookById);
    }

    @RequestMapping("/pay")
    public int payForTheOrder(Integer purchaserId, Integer orderId){
        int result = orderService.payForTheOrder(purchaserId, orderId);
        return result;
    }

    @RequestMapping("/getOrderByPurchaserId")
    public List<BookOrder> getOderByPurchaserId(Integer purchaserId, Integer pageNum, Integer pageSize){
        return orderService.getOrderByPurchaserId(purchaserId, pageNum, pageSize);
    }







}

package edu.zyh.controller;


import edu.zyh.domain.*;
import edu.zyh.service.BookService;
import edu.zyh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("api/v1/pri/order")
@CrossOrigin
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

    private List<OrderBookInfo> getBookIdListByOrderId(int orderId){
        return orderService.getBookOrderInfoOrderId(orderId);
    }

    @RequestMapping("/getOrderByPurchaserId")
    public JsonData getOderByPurchaserId(Integer purchaserId, Integer pageNum, Integer pageSize){
        List<BookOrder> bookOrderList = orderService.getOrderByPurchaserId(purchaserId, pageNum, pageSize);
        List<Order> orders = new LinkedList<>();
        for (BookOrder bookOrder : bookOrderList) {
            List<OrderBookInfo> bookIdListByOrderId = getBookIdListByOrderId(bookOrder.getOrderId());
            List<BookForOrder> bookList = new LinkedList<>();
            for (OrderBookInfo integer : bookIdListByOrderId) {
                Book bookById = bookService.findBookById(integer.getBookId());
                BookForOrder bookForOrder = new BookForOrder();
                bookForOrder.setBookName(bookById.getBookName());
                bookForOrder.setAuthor(bookById.getAuthor());
                bookForOrder.setId(bookById.getId());
                bookForOrder.setPrice(bookById.getPrice());
                bookForOrder.setNum(integer.getBookNum());
                bookList.add(bookForOrder);
            }
            Order order = new Order();
            order.setOrderId(bookOrder.getOrderId());
            order.setTotalFee(bookOrder.getTotalFee());
            order.setBookList(bookList);
            order.setState(bookOrder.getState());
            order.setOrderDate(bookOrder.getOrderDate());
            System.out.println(bookOrder.getOrderDate());
            orders.add(order);

        }
        return JsonData.buildSuccess(orders);
    }







}

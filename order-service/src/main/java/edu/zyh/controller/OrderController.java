package edu.zyh.controller;


import edu.zyh.domain.*;
import edu.zyh.service.BookService;
import edu.zyh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/pri/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/makeOrder")
    public JsonData makeOrder(@RequestParam("bookId") int[] bookId, @RequestParam("purchaserId") String purchaserId, @RequestParam("bookNum") int[] bookNum) {

        List<Integer> bookIds = Arrays.stream( bookId ).boxed().collect(Collectors.toList());
        List<Integer> bookNums = Arrays.stream( bookNum).boxed().collect(Collectors.toList());
        double priceById = bookService.getBookPriceById(bookIds, purchaserId, bookNums);
        int result = orderService.makeOrder(priceById, purchaserId, bookIds, bookNums);
        if (result >0 )
            return JsonData.build(result,null);
        else
            return JsonData.buildError(1, "error");
    }

    @RequestMapping("/getBook")
    public void getBook(int bookId) {
        Book bookById = bookService.findBookByIdForOrder(bookId);
        System.out.println(bookById);
    }

    @RequestMapping("/pay")
    public JsonData payForTheOrder(Integer userId, Integer orderId) {
        int result = orderService.payForTheOrder(userId, orderId);
        if (result == 1)
            return JsonData.build(0, null);
        else
            return JsonData.buildError(1, null);
    }

    private List<OrderBookInfo> getBookIdListByOrderId(int orderId) {
        return orderService.getBookOrderInfoOrderId(orderId);
    }

    @RequestMapping("/getOrderByPurchaserId")
    public JsonData getOderByPurchaserId(Integer purchaserId, Integer pageNum, Integer pageSize) {
        List<BookOrder> bookOrderList = orderService.getOrderByPurchaserId(purchaserId, pageNum, pageSize);
        List<Order> orders = new LinkedList<>();
        for (BookOrder bookOrder : bookOrderList) {
            List<OrderBookInfo> bookIdListByOrderId = getBookIdListByOrderId(bookOrder.getOrderId());
            List<BookForOrder> bookList = new LinkedList<>();
            for (OrderBookInfo integer : bookIdListByOrderId) {
                Book bookById = bookService.findBookByIdForOrder(integer.getBookId());
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

    @RequestMapping("getOrderNum")
    public JsonData getOrderNum(Integer userId){
        int result = orderService.getOrderNum(userId);
        return JsonData.buildError(0,result);
    }


}

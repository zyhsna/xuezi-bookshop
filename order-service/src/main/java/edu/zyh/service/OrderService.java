package edu.zyh.service;

import edu.zyh.domain.BookOrder;
import edu.zyh.domain.OrderBookInfo;

import java.util.List;

public interface OrderService {
     int payForTheOrder(int userId, int orderId);

    int makeOrder(double priceById, String purchaserId, List<Integer> bookId, List<Integer> bookNum);

    List<BookOrder> getOrderByPurchaserId(Integer purchaserId, Integer pageNum, Integer pageSize);

    List<OrderBookInfo> getBookOrderInfoOrderId(int orderId);

    int getOrderNum(Integer userId);
}

package edu.zyh.service;

import edu.zyh.domain.BookOrder;

import java.util.List;

public interface OrderService {
     int payForTheOrder(int purchaserId, int orderId);

    int makeOrder(double priceById, String purchaserId, List<Integer> bookId, List<Integer> bookNum);

    List<BookOrder> getOrderByPurchaserId(Integer purchaserId, Integer pageNum, Integer pageSize);
}

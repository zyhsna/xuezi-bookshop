package edu.zyh.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zyh.dao.OrderMapper;
import edu.zyh.domain.BookOrder;
import edu.zyh.domain.OrderBookInfo;
import edu.zyh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int payForTheOrder(int userId, int orderId) {
        int result = orderMapper.payForTheOrder(userId, orderId);
        return result;
    }

    @Override
    public int makeOrder(double priceById, String purchaserId, List<Integer> bookId, List<Integer> bookNum) {
        int parseInt = Integer.parseInt(purchaserId);

        BookOrder bookOrder = new BookOrder();
        bookOrder.setPurchaserId(parseInt);
        bookOrder.setTotalFee(priceById);
        bookOrder.setState(0);
        String nowTime= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        bookOrder.setOrderDate(nowTime);
        orderMapper.makeOrder(bookOrder);
        int orderId = bookOrder.getOrderId();

        for (int i = 0; i < bookId.size(); i++) {
            orderMapper.insertOrderBookInfo(orderId, parseInt, bookId.get(i), bookNum.get(i));
            orderMapper.updateSales(bookNum.get(i), bookId.get(i));
        }
        return orderId;
    }

    @Override
    public List<BookOrder> getOrderByPurchaserId(Integer purchaserId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<BookOrder> allBook = orderMapper.getOrderByPurchaserId(purchaserId);
            PageInfo pageInfo = new PageInfo(allBook);
            return pageInfo.getList();
        } finally {
            PageHelper.clearPage();
        }
    }

    @Override
    public List<OrderBookInfo> getBookOrderInfoOrderId(int orderId) {
        return orderMapper.getBookOrderInfoByOrderId(orderId);
    }

    @Override
    public int getOrderNum(Integer userId) {
        return orderMapper.getOrderNum(userId);

    }
}

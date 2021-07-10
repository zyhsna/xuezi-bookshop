package edu.zyh.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zyh.dao.OrderMapper;
import edu.zyh.domain.BookOrder;
import edu.zyh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int payForTheOrder(int purchaserId, int orderId) {
        int result =  orderMapper.payForTheOrder(purchaserId, orderId);
        return result;
    }

    @Override
    public int makeOrder(double priceById, String purchaserId, List<Integer> bookId,List<Integer> bookNum) {
        int parseInt = Integer.parseInt(purchaserId);
        BookOrder bookOrder = new BookOrder();
        bookOrder.setPurchaserId(parseInt);
        bookOrder.setTotalFee(priceById);
        bookOrder.setState(0);
        orderMapper.makeOrder(bookOrder);
        int orderId = bookOrder.getOrderId();

        //insert into order_book_info
        for(int i=0;i<bookId.size();i++){
            orderMapper.insertOrderBookInfo(orderId, parseInt, bookId.get(i), bookNum.get(i));
        }


        return orderId;
    }

    @Override
    public List<BookOrder> getOrderByPurchaserId(Integer purchaserId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        try{
            List<BookOrder> allBook = orderMapper.getOrderByPurchaserId(purchaserId);
            PageInfo pageInfo = new PageInfo(allBook);
            return pageInfo.getList();
        }
        finally {
            PageHelper.clearPage();
        }
    }
}

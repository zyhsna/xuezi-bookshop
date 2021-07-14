package edu.zyh.dao;


import edu.zyh.domain.BookOrder;
import edu.zyh.domain.OrderBookInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    @Insert({"insert into book_order(total_fee, purchaser_id, state) value (#{bookOrder.totalFee}, #{bookOrder.purchaserId}, #{bookOrder.state});"})
    @Options(useGeneratedKeys = true, keyProperty = "bookOrder.orderId", keyColumn = "order_id")
    int makeOrder(@Param("bookOrder") BookOrder bookOrder);

    @Insert("insert into order_book_info(order_id, purchaser_id, book_id, book_num) value(#{orderId}, #{purchaserId}, #{bookId}, #{bookNum})")
    void insertOrderBookInfo(@Param("orderId") int orderId, @Param("purchaserId")int purchaserId, @Param("bookId")int bookId, @Param("bookNum")int bookNum);

    @Update("update book_order set state = 1 where order_id = #{orderId} and purchaser_id = #{purchaserId}")
    int payForTheOrder(@Param("purchaserId") int purchaserId,@Param("orderId") int orderId);

    @Select("select * from book_order where purchaser_id = #{purchaserId}")
    List<BookOrder> getOrderByPurchaserId(Integer purchaserId);

    @Select("select * from order_book_info where order_id = #{orderId}")
    List<OrderBookInfo> getBookOrderInfoByOrderId(int orderId);
}

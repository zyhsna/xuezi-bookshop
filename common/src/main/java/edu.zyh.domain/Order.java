package edu.zyh.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Order {

    private int orderId;
    private double totalFee;
    private int state;
    private List<BookForOrder> bookList;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String orderDate;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", totalFee=" + totalFee +
//                ", date=" + date +
                ", state=" + state +
                ", bookList=" + bookList +
                '}';
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }



    public void setState(int state) {
        this.state = state;
    }



    public int getOrderId() {
        return orderId;
    }

    public double getTotalFee() {
        return totalFee;
    }

//    public Date getDate() {
//        return date;
//    }

    public int getState() {
        return state;
    }

    public List<BookForOrder> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookForOrder> bookList) {
        this.bookList = bookList;
    }
}

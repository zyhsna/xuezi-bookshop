package edu.zyh.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BookOrder {

  private int orderId;
  private double totalFee;
  private int purchaserId;
  private int state;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date orderDate;

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }


  public double getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(double totalFee) {
    this.totalFee = totalFee;
  }


  public int getPurchaserId() {
    return purchaserId;
  }

  public void setPurchaserId(int purchaserId) {
    this.purchaserId = purchaserId;
  }


  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

}

package edu.zyh.domain;


public class BookOrder {

  private int orderId;
  private double totalFee;
  private long purchaserId;
  private long state;


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


  public long getPurchaserId() {
    return purchaserId;
  }

  public void setPurchaserId(long purchaserId) {
    this.purchaserId = purchaserId;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }

}

package edu.zyh.domain;


public class OrderBookInfo {

  private long infoId;
  private long orderId;
  private long purchaserId;
  private long bookId;
  private long bookNum;


  public long getInfoId() {
    return infoId;
  }

  public void setInfoId(long infoId) {
    this.infoId = infoId;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public long getPurchaserId() {
    return purchaserId;
  }

  public void setPurchaserId(long purchaserId) {
    this.purchaserId = purchaserId;
  }


  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }


  public long getBookNum() {
    return bookNum;
  }

  public void setBookNum(long bookNum) {
    this.bookNum = bookNum;
  }

}

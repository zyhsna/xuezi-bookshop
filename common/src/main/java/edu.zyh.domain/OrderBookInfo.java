package edu.zyh.domain;


public class OrderBookInfo {

  private int infoId;
  private int orderId;
  private int purchaserId;
  private int bookId;
  private int bookNum;

  @Override
  public String toString() {
    return "OrderBookInfo{" +
            "infoId=" + infoId +
            ", orderId=" + orderId +
            ", purchaserId=" + purchaserId +
            ", bookId=" + bookId +
            ", bookNum=" + bookNum +
            '}';
  }

  public void setInfoId(int infoId) {
    this.infoId = infoId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public void setPurchaserId(int purchaserId) {
    this.purchaserId = purchaserId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public void setBookNum(int bookNum) {
    this.bookNum = bookNum;
  }

  public int getInfoId() {
    return infoId;
  }

  public int getOrderId() {
    return orderId;
  }

  public int getPurchaserId() {
    return purchaserId;
  }

  public int getBookId() {
    return bookId;
  }

  public int getBookNum() {
    return bookNum;
  }
}

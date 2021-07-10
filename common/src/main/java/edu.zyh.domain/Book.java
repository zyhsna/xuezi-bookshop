package edu.zyh.domain;


public class Book {

  private int id;
  private String bookName;
  private double price;
  private String author;
  private String coverImg;
  private String summary;

  //test mark the source server


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }


  public String getCoverImg() {
    return coverImg;
  }

  public void setCoverImg(String coverImg) {
    this.coverImg = coverImg;
  }


  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  @Override
  public String toString() {
    return "Book{" +
            "id=" + id +
            ", bookName='" + bookName + '\'' +
            ", price=" + price +
            ", author='" + author + '\'' +
            ", coverImg='" + coverImg + '\'' +
            ", summary='" + summary + '\'' +
            '}';
  }
}

package edu.zyh.domain;


public class BookForOrder {
    private int id;
    private String bookName;
    private double price;
    private String author;

    private int num;

    @Override
    public String toString() {
        return "BookForOrder{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", num=" + num +
                '}';
    }

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



    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }



}

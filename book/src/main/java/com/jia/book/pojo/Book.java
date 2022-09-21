package com.jia.book.pojo;

public class Book {
    private Integer id;
    private String bookImg;
    private Double price;
    private String bookName;
    private String author;
    private Integer bookCount;
    private Integer saleCount;
    private Integer bookStatus;  //0表示表示异常书籍  1表示正常书籍

    public Book(){}



    public Book(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookImg='" + bookImg + '\'' +
                ", price=" + price +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", bookCount=" + bookCount +
                ", saleCount=" + saleCount +
                ", bookStatus=" + bookStatus +
                '}';
    }
}

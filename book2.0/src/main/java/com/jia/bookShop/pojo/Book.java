package com.jia.bookShop.pojo;

public class Book {
    private Integer bookId;
    private String bookName;
    private String bookImg;
    private Double price;
    private String author;
    private Integer bookCount;
    private Integer saleCount;
    private Integer bookStatus;

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookImg='" + bookImg + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", bookCount=" + bookCount +
                ", saleCount=" + saleCount +
                ", bookStatus=" + bookStatus +
                '}';
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public Book(Integer bookId, String bookName, String bookImg, Double price, String author, Integer bookCount, Integer saleCount, Integer bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookImg = bookImg;
        this.price = price;
        this.author = author;
        this.bookCount = bookCount;
        this.saleCount = saleCount;
        this.bookStatus = bookStatus;
    }

    public Book() {
    }
}

package com.example.tryingp.Models;

import java.time.LocalDate;

public class Book {
    private String isbn; // The ISBN of the book
    private String title; // The title of the book
    private double purchasePrice, totalPurchased; // The purchase price of the book and the total amount purchased

    private double originalPrice; // The original price of the book
    private double sellPrice; // The selling price of the book
    private String author, category, supplier; // The author, category, and supplier of the book
    private int stock; // The number of copies of the book in stock

    private LocalDate purchaseDate; // The date when the book was purchased

    // A default constructor
    public Book(){}

    // A constructor with isbn, title, purchase price, selling price, and category
    public Book(String isbn,String title,double purchasePrice,double sellPrice,String category) {
        this.isbn = isbn;
        this.title = title;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
        this.category = category;
    }

    // A constructor with isbn, title, purchase price, selling price, category, and stock
    public Book(String isbn,String title,double purchasePrice,double sellPrice,String category, int stock) {
        this.isbn = isbn;
        this.title = title;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
        this.category = category;
        this.stock = stock;
    }

    // A constructor with all attributes of the book
    public Book(String isbn, String title, double purchasePrice, double originalPrice, double sellPrice, String author, String category, String supplier, int stock, LocalDate purchaseDate) {
        this.isbn = isbn;
        this.title = title;
        this.purchasePrice = purchasePrice;
        this.originalPrice = originalPrice;
        this.sellPrice = sellPrice;
        this.author = author;
        this.category = category;
        this.supplier = supplier;
        this.stock = stock;
        this.purchaseDate = purchaseDate;
        this.totalPurchased = stock * purchasePrice;
    }

    // Getter method for ISBN
    public String getIsbn() {
        return isbn;
    }

    // Getter method for totalPurchased
    public double getTotalPurchased() {
        return totalPurchased;
    }

    // Setter method for totalPurchased
    public void setTotalPurchased(double totalPurchased) {
        this.totalPurchased = totalPurchased;
    }

    // Getter method for title
    public String getTitle() {
        return title;
    }

    // Getter method for author
    public String getAuthor() {
        return author;
    }

    // Getter method for purchasePrice
    public double getPurchasePrice() {
        return purchasePrice;
    }

    // Getter method for sellPrice
    public double getSellPrice() {
        return sellPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return this.isbn + "," + this.title + "," + this.purchasePrice +
                "," + this.originalPrice + "," + this.sellPrice + "," + this.author + ","
                + this.category + "," + this.supplier + "," + this.stock + "," + this.purchaseDate.toString();
    }

    //another toString method, used in printing the bills
    public String toStringBill() {
        return "ISBN: " + getIsbn() +
                " ,Title: " + getTitle() +
                " ,Author: " + getAuthor();
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
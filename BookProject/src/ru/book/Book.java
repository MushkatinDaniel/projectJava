package ru.book;

import static java.lang.Math.sqrt;

public class Book {


    public String title;
    public int releaseYear;
    public String author;
    public int pages;

    public Book(String title, int releaseYear, String author, int pages) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.author = author;
        this.pages = pages;
    }

    public boolean isBig() {
        if (pages > 500) {
            return true;
        } else {
            return false;
        }
    }

    public boolean matches(String word) {
        if (title.contains(word) || author.contains(word) || Author.surname.contains(word)) {
            return true;
        } else {
            return false;
        }

    }

    public int estimatePrice() {
        double ratingSqrt = Math.sqrt((Author.rating));
        int price = (int) Math.floor((pages * 3) * ratingSqrt);
        if (price >= 250) {
            return price;
        } else {
            return 250;
        }

    }
}


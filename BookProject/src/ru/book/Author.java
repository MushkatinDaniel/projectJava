package ru.book;

public class Author {

    public String name;
    public static String surname;
    public static int rating;

    public Author(String name, String surname, int rating) {
        this.name = name;
        Author.surname = surname;
        Author.rating = rating;
    }
}


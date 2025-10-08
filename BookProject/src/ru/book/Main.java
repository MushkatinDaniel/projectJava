package ru.book;

public class Main {
    public static void main(String[] args) {

        Book book = new Book("Жизнь взаймы", 1959, "Э. М. Ремарк", 384);
        System.out.println(book.isBig());
        System.out.println(book.matches("Жизнь"));

        Book book1 = new Book("Java. Полное руководство", 2012, "Г. Шилдт", 1345);
        System.out.println(book1.isBig());
        System.out.println(book1.matches("Java"));

        Author author = new Author("Эрих", "Ремарк", 100);
        System.out.println(book.matches("Ре"));

        System.out.println("Стоимость книги " + "\"" + book.title + "\"" + " " + book.estimatePrice() + " рублей.");

        Author author1 = new Author("Герберт", "Шилдт", 90);
        System.out.println(book1.matches("Ши"));

        System.out.println("Стоимость книги " + "\"" + book1.title + "\"" + " " + book1.estimatePrice() + " рублей.");

    }
}
//сделать методы сет и гет для доступа к приватным полям
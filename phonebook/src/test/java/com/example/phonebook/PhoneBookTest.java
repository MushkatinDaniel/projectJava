package com.example.phonebook;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PhoneBookTest {

    // --- add ---

    @Test
    void addFirstContactReturnsOne() {
        PhoneBook book = new PhoneBook();
        assertEquals(1, book.add("Alice", "111"));
    }

    @Test
    void addSecondContactReturnsTwo() {
        PhoneBook book = new PhoneBook();
        book.add("Alice", "111");
        assertEquals(2, book.add("Bob", "222"));
    }

    // --- findByNumber ---

    @Test
    void findByNumberReturnsName() {
        PhoneBook book = new PhoneBook();
        book.add("Alice", "111");
        assertEquals("Alice", book.findByNumber("111"));
    }

    @Test
    void findByNumberUnknownReturnsNull() {
        PhoneBook book = new PhoneBook();
        assertNull(book.findByNumber("999"));
    }

    // --- findByName ---

    @Test
    void findByNameReturnsNumber() {
        PhoneBook book = new PhoneBook();
        book.add("Alice", "111");
        assertEquals("111", book.findByName("Alice"));
    }

    @Test
    void findByNameUnknownReturnsNull() {
        PhoneBook book = new PhoneBook();
        assertNull(book.findByName("Ghost"));
    }

    // --- printAllNames ---

    @Test
    void printAllNamesPrintsAlphabetically() {
        PhoneBook book = new PhoneBook();
        book.add("Charlie", "333");
        book.add("Alice", "111");
        book.add("Bob", "222");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            book.printAllNames();
        } finally {
            System.setOut(originalOut);
        }

        String expected = "Alice" + System.lineSeparator()
                + "Bob" + System.lineSeparator()
                + "Charlie" + System.lineSeparator();
        assertEquals(expected, out.toString());
    }
}

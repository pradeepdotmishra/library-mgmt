package com.library.mgmt;

import com.library.mgmt.exception.BookNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BookStokeTest {

    @Test
    @DisplayName("empty library")
    public void emptyLibraryTest() {

        BookStoke bookStoke = new BookStoke(Collections.emptySet());
        assertTrue(bookStoke.getBookSet().isEmpty());
    }

    @Test
    @DisplayName("the list of books in the library")
    public void bookListTest() {

        Book book1 = new Book("111", "Book1");
        Book book2 = new Book("222", "Book2");

        Set<Book> books = new HashSet<>(Arrays.asList(book1, book2));
        BookStoke bookStoke = new BookStoke(books);

        assertEquals(books, bookStoke.getBookSet());
    }

    @Test
    @DisplayName("the book not found in the library")
    public void bookNotFoundInLibraryTest() {

        Book book1 = new Book("111", "Book1");
        Set<Book> books = new HashSet<>(Arrays.asList(book1));
        BookStoke bookStoke = new BookStoke(books);

        assertThrows(BookNotFoundException.class, () -> bookStoke.borrow("222"));
    }

    @Test
    @DisplayName("the book is borrowed and removed from the library")
    public void removeBookFromLibraryTest() {

        Set<Book> books = new HashSet<>(Arrays.asList(new Book("111", "Book1")));
        BookStoke bookStoke = new BookStoke(books);
        bookStoke.borrow("111");

        assertTrue(bookStoke.getBookSet().isEmpty());
    }

    @Test
    @DisplayName("the library reflects the updated stock of the book after return")
    public void returnBookTest() {

        Set<Book> books = new HashSet<>(Arrays.asList(new Book("111", "Book1")));
        BookStoke bookStoke = new BookStoke(books);
        Book book = new Book("456", "Book2");
        bookStoke.returnBook(book);

        assertTrue(bookStoke.getBookSet().contains(book));
    }

}
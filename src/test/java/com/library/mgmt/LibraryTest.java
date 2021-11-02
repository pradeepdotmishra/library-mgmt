package com.library.mgmt;

import com.library.mgmt.exception.BookLimitReachedException;
import com.library.mgmt.exception.BookNotFoundException;
import com.library.mgmt.exception.DuplicateBorrowingBookException;
import com.library.mgmt.exception.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    BookStoke bookStoke;

    @BeforeEach
    public void setUp() {

        Book book1 = new Book("123", "Book1");
        Book book1Copy = new Book("111", "Book1");
        Book book2 = new Book("456", "Book2");
        Book book3 = new Book("678", "Book3");
        Book book4 = new Book("910", "Book4");
        Book book5 = new Book("101", "Book5");
        Set<Book> books = new HashSet<>(Arrays.asList(book1, book1Copy, book2, book3, book4, book5));
        bookStoke = new BookStoke(books);
    }

    @AfterEach
    public void cleanUp() {

        bookStoke = new BookStoke(Collections.emptySet());
    }

    @Test
    @DisplayName("Book is not present in the library")
    public void bookNotPresentTest() {

        User user = new User("1");

        Library library = new Library(bookStoke, new HashSet<>(Arrays.asList(user)));

        assertThrows(BookNotFoundException.class, () -> library.issueBook("1", "566"));
    }

    @Test
    @DisplayName("User not Found")
    public void userNotFoundTest() {

        User user = new User("1");

        Library library = new Library(bookStoke, new HashSet<>(Arrays.asList(user)));

        assertThrows(UserNotFoundException.class, () -> library.issueBook("2", "456"));
    }

    @Test
    @DisplayName("Book added to user's borrowed list")
    public void bookAddedToBorrowedListTest() {

        User user = new User("1");

        Library library = new Library(bookStoke, new HashSet<>(Arrays.asList(user)));
        library.issueBook("1", "123");

        Book book = new Book("123", "Book1");
        assertEquals(user.getBorrowedBooks(), new HashSet<>(Arrays.asList(book)));
    }

    @Test
    @DisplayName("Each User has a borrowing limit of 2 books at any point of time")
    public void userShouldHaveABorrowingLimitOfTwoBooks() {

        User user = new User("1");

        Library library = new Library(bookStoke, new HashSet<>(Arrays.asList(user)));
        library.issueBook("1", "101");
        library.issueBook("1", "678");

        assertThrows(BookLimitReachedException.class, () -> library.issueBook("1", "910"));
    }

    @Test
    @DisplayName("Only 1 copy of a book can be borrowed by a User at any point of time")
    public void shouldThrowWhenUserBorrowTwoCopiesOfSameBook() {

        User user = new User("1");

        Library library = new Library(bookStoke, new HashSet<>(Arrays.asList(user)));
        library.issueBook("1", "123");

        assertThrows(DuplicateBorrowingBookException.class, () -> library.issueBook("1", "111"));
    }

    @Test
    @DisplayName("return one book to the library")
    public void shouldBeAbleToReturnABorrowedBook() {

        User user = new User("1");

        Library library = new Library(bookStoke, new HashSet<>(Arrays.asList(user)));
        library.issueBook("1", "123");

        library.returnBook("1", "123");

        assertTrue(user.getBorrowedBooks().isEmpty());
        assertEquals(1, library.getBookStoke().getBookSet().stream().filter(book -> book.getId().equals("123")).count());
    }

    @Test
    @DisplayName("Invalid User return a borrowed book")
    public void invalidUserReturnBookTest() {

        User user = new User("1");

        Library library = new Library(bookStoke, new HashSet<>(Arrays.asList(user)));
        library.issueBook("1", "123");

        assertThrows(UserNotFoundException.class, () -> library.returnBook("2", "123"));

    }

    @Test
    @DisplayName("Invalid Book return by borrowed user")
    public void invalidBookReturnedTest() {

        User user = new User("1");

        Library library = new Library(bookStoke, new HashSet<>(Arrays.asList(user)));
        library.issueBook("1", "123");

        assertThrows(BookNotFoundException.class, () -> library.returnBook("1", "456"));
    }
}

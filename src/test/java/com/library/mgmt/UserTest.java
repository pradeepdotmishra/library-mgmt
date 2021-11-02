package com.library.mgmt;

import com.library.mgmt.exception.BookNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("Only 1 copy of a book can be borrowed by a User at any point of time")
    public void shouldReturnTrueIfUserAlreadyHasACopyOfTheBook() {

        User user = new User("1");
        user.add(new Book("123", "Book1"));

        assertTrue(user.isUserHasACopyOfBook(new Book("456", "Book1")));
    }

    @Test
    @DisplayName("True - if user already have another copy of a book")
    public void shouldReturnFalseIfUserDoesNotHasACopyOfTheBook() {

        User user = new User("1");
        user.add(new Book("123", "Book1"));

        assertFalse(user.isUserHasACopyOfBook(new Book("456", "Book2")));
    }

    @Test
    @DisplayName("False - if user don't have a copy of that book")
    public void shouldThrowWhenBookIsNotBorrowedByTheUser() {

        User user = new User("1");
        user.add(new Book("123", "Book1"));
        user.add(new Book("456", "Book2"));

        assertThrows(BookNotFoundException.class, () -> user.getBookById("555"));
    }

    @Test
    @DisplayName("fetch borrowed book of a user by userID.")
    public void fetchBorrowedBookOfUserTest() {

        User user = new User("1");
        Book book = new Book("123", "Book1");
        user.add(book);

        assertEquals(book, user.getBookById("123"));
    }

    @Test
    @DisplayName("Each User has a borrowing limit of 2 books at any point of time")
    public void shouldReturnTrueIfUserHasAlreadyBorrowedTwoBooks() {

        User user = new User("1");
        user.add(new Book("123", "Book1"));
        user.add(new Book("456", "Book2"));

        assertTrue(user.isUserHasTwoBooks());
    }
}

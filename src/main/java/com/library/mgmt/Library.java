package com.library.mgmt;

import com.library.mgmt.exception.BookLimitReachedException;
import com.library.mgmt.exception.DuplicateBorrowingBookException;
import com.library.mgmt.exception.UserNotFoundException;

import java.util.Optional;
import java.util.Set;

public class Library {
    private final BookStoke bookStoke;
    private Set<User> userSet;

    public Library(BookStoke bookStoke, Set<User> userSet) {
        this.bookStoke = bookStoke;
        this.userSet = userSet;
    }

    public BookStoke getBookStoke() {
        return bookStoke;
    }

    public void issueBook(String userId,String bookId){
        User user = getUserById(userId);

        if(user.isUserHasTwoBooks()){
            throw new BookLimitReachedException("Single User can have maximum of 2 Books");
        }

        Book book = bookStoke.borrow(bookId);

        if (user.isUserHasACopyOfBook(book)){
            throw new DuplicateBorrowingBookException("User can not borrow two copies of same Book");
        }
        user.add(book);
    }

    public void returnBook(String userId,String bookId){
        User user = getUserById(userId);
        Book book = user.getBookById(bookId);
        user.remove(book);
        bookStoke.returnBook(book);
    }

    public User getUserById(String userId){
        Optional<User> user = userSet
                .stream()
                .filter(u->u.getId().equals(userId))
                .findAny();
        user.orElseThrow(() -> new UserNotFoundException("User not found."));
        return user.get();
    }
}

package com.library.mgmt;

import com.library.mgmt.exception.BookNotFoundException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class User {

    private final String id;
    private final Set<Book> borrowedBooks;

    public User(String id) {
        this.id = id;
        borrowedBooks = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void add(Book book) {
        borrowedBooks.add(book);
    }

    public void remove(Book book) {
        borrowedBooks.remove(book);
    }

    public boolean isUserHasTwoBooks() {
        return borrowedBooks.size() >= 2;
    }

    public boolean isUserHasACopyOfBook(Book book) {
        Optional<Book> optionalBook = borrowedBooks
                .stream()
                .filter(b -> b.getName().equals(book.getName()))
                .findAny();
        return optionalBook.isPresent();
    }

    public Book getBookById(String bookId) {
        Optional<Book> optionalBook = borrowedBooks
                .stream()
                .filter(b -> b.getId().equals(bookId))
                .findAny();
        optionalBook.orElseThrow(() -> new BookNotFoundException("Book not brrowed by User"));
        return optionalBook.get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || obj.getClass()!=getClass()) return false;
        User user= (User) obj;
        return id.equals(user.id);
    }
}

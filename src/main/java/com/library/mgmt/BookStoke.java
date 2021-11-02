package com.library.mgmt;

import com.library.mgmt.exception.BookNotFoundException;

import java.util.Optional;
import java.util.Set;

public class BookStoke {
    Set<Book> bookSet;

    public BookStoke(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }

    public Set<Book> getBookSet() {
        return bookSet;
    }

    public Book borrow(String id) {
        Optional<Book> optionalBook = bookSet
                .stream()
                .filter(b -> b.getId().equals(id))
                .findAny();
        optionalBook.orElseThrow(() -> new BookNotFoundException("Book not found"));
        optionalBook.ifPresent(b -> bookSet.remove(b));
        return optionalBook.get();
    }

    public void returnBook(Book book) {
        bookSet.add(book);
    }
}

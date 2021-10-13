package com.gia.bookclub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gia.bookclub.models.Book;
import com.gia.bookclub.models.User;
import com.gia.bookclub.repository.BookRepository;

@Service
public class BookService {
@Autowired
		BookRepository bookRepository;
public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
}
// returns all the books
public List<Book> allBook() {
    return bookRepository.findAll();
}
// creates a book
public Book createBook(Book b) {
    return bookRepository.save(b);
}
// retrieves a book
public Book oneBook(Long id) {
    Optional<Book> optionalBook= bookRepository.findById(id);
    if(optionalBook.isPresent()) {
        return optionalBook.get();
    } else {
        return null;
    }
}

public Book updateBook(Long id, String title, String author,  String thoughts, User user) {
	Book book = new Book(title, author, thoughts, user);
	book.setId(id);
	bookRepository.save(book);
	return null;
}
public Book updateBook(Book book) {
	return bookRepository.save(book);
	
}
public void deleteBook(Long id) {
	bookRepository.deleteById(id);
	
}
}
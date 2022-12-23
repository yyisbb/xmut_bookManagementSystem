package wang.gravity.bookmanagementsystem.service;

import org.springframework.stereotype.Repository;

import java.util.List;

import wang.gravity.bookmanagementsystem.pojo.Book;


@Repository
public interface BookService {
    List<Book> getBookList();

    void deleteBooks(int[] ids);

    Book addBook(Book book);


    Book getBookInfo(Book book);

    Book getBookBorrowContent(Book book);

    void updateBookInfo(Book book);
}

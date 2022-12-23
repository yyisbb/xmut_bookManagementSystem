package wang.gravity.bookmanagementsystem.service;

import org.springframework.stereotype.Repository;

import java.util.List;

import wang.gravity.bookmanagementsystem.pojo.Borrow;
import wang.gravity.bookmanagementsystem.pojo.User;


@Repository
public interface BorrowService {
    //借阅图书
    void borrowBook(int bookId, User user);

    //借阅图书
    void backBook(int bookId, User user);

    List<Borrow> getBorrowList(User user);
}

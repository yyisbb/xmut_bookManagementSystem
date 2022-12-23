package wang.gravity.bookmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.exception.MyException;
import wang.gravity.bookmanagementsystem.mapper.BookMapper;
import wang.gravity.bookmanagementsystem.mapper.BorrowMapper;
import wang.gravity.bookmanagementsystem.mapper.UserMapper;
import wang.gravity.bookmanagementsystem.pojo.Book;
import wang.gravity.bookmanagementsystem.pojo.Borrow;
import wang.gravity.bookmanagementsystem.pojo.User;
import wang.gravity.bookmanagementsystem.service.BorrowService;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public synchronized void borrowBook(int bookNo, User user) {
        if (bookNo == 0) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }
        //查询图书是否存在
        Book book = bookMapper.getBookByBookNo(bookNo);
        //不存在
        if (book == null) {
            throw new MyException(ErrorCode.BOOK_NOT_FOUND_ERROR);
        }
        //查看是否借过
        Borrow oldBorrow = borrowMapper.getBorrowByBookNo(bookNo, user.getId());
        if (oldBorrow != null && oldBorrow.getId() != 0) {
            throw new MyException(ErrorCode.BORROW_EXIST_ERROR);
        }
        //是否超出借阅次数
        if (user.getBorrowNum() >= 5) {
            throw new MyException(ErrorCode.BORROW_NUM_EXCEED_LIMIT_ERROR);
        }
        //查看图书库存
        if (book.getBookQuantity() == 0) {
            throw new MyException(ErrorCode.QUANTITY_EMPTY_ERROR);
        }
        borrowMapper.borrowBook(new Borrow(0, bookNo, new Date(), null, user.getId(), ""));
        userMapper.addBorrowNum(user.getId(), user.getBorrowNum() + 1);
        //减少库存 增加图书借阅次数
        bookMapper.borrowBook(bookNo, book.getBookBorrowNum() + 1, book.getBookQuantity() <= 0 ? 0 : book.getBookQuantity() - 1);
    }

    @Override
    public synchronized void backBook(int bookNo, User user) {
        if (bookNo == 0) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }
        //查询图书是否存在
        Book book = bookMapper.getBookByBookNo(bookNo);
        //不存在
        if (book == null) {
            throw new MyException(ErrorCode.BOOK_NOT_FOUND_ERROR);
        }
        //查看是否借过
        Borrow oldBorrow = borrowMapper.getBorrowByBookNo(bookNo, user.getId());
        if (oldBorrow == null || oldBorrow.getId() == 0) {
            throw new MyException(ErrorCode.BORROW_NOT_FOUND_ERROR);
        }
        //借过
        oldBorrow.setBackTime(new Date());

        borrowMapper.backBook(oldBorrow);
        //增加用户借书次数
        userMapper.addBorrowNum(user.getId(), user.getBorrowNum() <= 0 ? 0 : user.getBorrowNum()-1);
        //减少库存 增加图书借阅次数
        bookMapper.borrowBook(bookNo, book.getBookBorrowNum(), book.getBookQuantity() + 1);
    }

    @Override
    public List<Borrow> getBorrowList(User user) {
        return borrowMapper.getBorrowList(user.getId());
    }
}

package wang.gravity.bookmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.exception.MyException;
import wang.gravity.bookmanagementsystem.mapper.BookMapper;
import wang.gravity.bookmanagementsystem.mapper.BorrowMapper;
import wang.gravity.bookmanagementsystem.pojo.Book;
import wang.gravity.bookmanagementsystem.pojo.Borrow;
import wang.gravity.bookmanagementsystem.pojo.User;
import wang.gravity.bookmanagementsystem.service.BookService;
import wang.gravity.bookmanagementsystem.utils.UserUtil;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;
    @Autowired
    BorrowMapper borrowMapper;

    @Override
    public List<Book> getBookList() {
        return bookMapper.getAllBook();
    }

    @Override
    public void deleteBooks(int[] ids) {
        if (ids == null) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }
        if (ids.length != 0) {
            bookMapper.deleteBooks(ids);
        }
    }

    @Override
    public synchronized Book addBook(Book book) {
        if (book == null) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }

        if (book.getBookNo() == 0 ||
                book.getBookAuthor() == null ||
                book.getBookName() == null ||
                book.getBookQuantity() == 0 ||
                book.getBookCategoryId() == 0
        ) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }
        if (book.getBookCover() == null) {
            throw new MyException(ErrorCode.COVER_PARAM_NULL_ERROR);
        }

        book.setCreatedTime(new Date());

        Book oldBook = bookMapper.getBookByBookNo(book.getBookNo());
        if (oldBook != null && oldBook.getId() != 0) {
            throw new MyException(ErrorCode.BOOK_EXIST_ERROR);
        }

        bookMapper.addBook(book);
        if (book.getId() == 0) {
            throw new MyException(ErrorCode.DB_ERROR);
        }

        return book;
    }

    @Override
    public Book getBookInfo(Book param) {
        if (param == null) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }
        Book book = bookMapper.getBookByBookId(param.getId());

        if (param.getBookNo() != 0) {
            book = bookMapper.getBookByBookNo(param.getBookNo());
        }

        if (book == null || book.getId() == 0) {
            throw new MyException(ErrorCode.BOOK_NOT_FOUND_ERROR);
        }
        return book;
    }

    @Override
    public Book getBookBorrowContent(Book param) {
        if (param == null) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }
        //判断书存不存在
        Book book = bookMapper.getBookByBookNo(param.getBookNo());
        if (book == null || book.getId() == 0) {
            throw new MyException(ErrorCode.BOOK_NOT_FOUND_ERROR);
        }

        User user = UserUtil.getLoginUser();
        //查看是否借过
        Borrow oldBorrow = borrowMapper.getBorrowByBookNo(param.getBookNo(), user.getId());
        if (oldBorrow == null || oldBorrow.getId() == 0) {
            throw new MyException(ErrorCode.BORROW_NOT_FOUND_ERROR);
        }

        return book;
    }

    @Override
    public void updateBookInfo(Book param) {
        if (param == null || param.getId() == 0) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }
        int count = bookMapper.updateBookInfo(param);
        if (count == 0) {
            throw new MyException(ErrorCode.DB_ERROR);
        }
    }
}

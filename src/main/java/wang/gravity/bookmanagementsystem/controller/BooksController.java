package wang.gravity.bookmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import wang.gravity.bookmanagementsystem.annotation.AuthCheck;
import wang.gravity.bookmanagementsystem.annotation.UserLogAnnotation;
import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.constant.UserLogConst;
import wang.gravity.bookmanagementsystem.dto.DeleteForm;
import wang.gravity.bookmanagementsystem.exception.MyException;
import wang.gravity.bookmanagementsystem.pojo.Book;
import wang.gravity.bookmanagementsystem.service.BookService;
import wang.gravity.bookmanagementsystem.utils.ResultUtil;

@RestController
public class BooksController {
    @Autowired
    BookService bookService;

    @GetMapping("/getBooksList")
    public ResultUtil<List<Book>> getBooksList() {
        return ResultUtil.success(bookService.getBookList());
    }

    @PostMapping("/getBookInfo")
    public ResultUtil<Book> getBookInfo(@RequestBody Book book) {
        return ResultUtil.success(bookService.getBookInfo(book));
    }

    @PostMapping("/getBookContent")
    public ResultUtil<Book> getBorrowBookContent(@RequestBody Book book) {
        return ResultUtil.success(bookService.getBookBorrowContent(book));
    }

    @AuthCheck
    @PostMapping("/deleteBooks")
    @UserLogAnnotation(operation = UserLogConst.DELETE_BOOK)
    public ResultUtil<String> deleteBooks(@RequestBody DeleteForm deleteForm) {
        bookService.deleteBooks(deleteForm.getIds());
        return ResultUtil.success();
    }

    @AuthCheck
    @PostMapping("/addBook")
    @UserLogAnnotation(operation = UserLogConst.ADD_BOOK)
    public ResultUtil<String> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return ResultUtil.success();
    }

    @AuthCheck
    @PostMapping("/updateBookInfo")
    @UserLogAnnotation(operation = UserLogConst.UPDATE_BOOK)
    public ResultUtil<String> updateBookInfo(@RequestBody Book book) {
        bookService.updateBookInfo(book);
        return ResultUtil.success();
    }


}

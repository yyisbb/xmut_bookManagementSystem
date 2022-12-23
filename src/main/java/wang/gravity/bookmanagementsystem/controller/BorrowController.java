package wang.gravity.bookmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import wang.gravity.bookmanagementsystem.annotation.UserLogAnnotation;
import wang.gravity.bookmanagementsystem.constant.UserLogConst;
import wang.gravity.bookmanagementsystem.pojo.Book;
import wang.gravity.bookmanagementsystem.pojo.Borrow;
import wang.gravity.bookmanagementsystem.pojo.User;
import wang.gravity.bookmanagementsystem.service.BorrowService;
import wang.gravity.bookmanagementsystem.utils.ResultUtil;
import wang.gravity.bookmanagementsystem.utils.UserUtil;


@RestController
public class BorrowController {
    @Autowired
    BorrowService borrowService;

    @PostMapping("/borrowBook")
    @UserLogAnnotation(operation = UserLogConst.BORROW_BOOK)
    public ResultUtil<String> borrowBook(@RequestBody Book book) {
        User user = UserUtil.getLoginUser();
        borrowService.borrowBook(book.getBookNo(),user);
        return ResultUtil.success();
    }

    @PostMapping("/backBook")
    @UserLogAnnotation(operation = UserLogConst.BACK_BOOK)
    public ResultUtil<String> backBook(@RequestBody Book book) {
        User user = UserUtil.getLoginUser();
        borrowService.backBook(book.getBookNo(),user);
        return ResultUtil.success();
    }


    @GetMapping("/getBorrowList")
    public ResultUtil<List<Borrow>> getBorrowList() {
        User user = UserUtil.getLoginUser();
        return ResultUtil.success(borrowService.getBorrowList(user));
    }
}

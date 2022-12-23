package wang.gravity.bookmanagementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import wang.gravity.bookmanagementsystem.pojo.Borrow;

@Mapper
public interface BorrowMapper {
    //借阅图书
    void borrowBook(Borrow borrow);

    Borrow getBorrowByBookNo(int bookNo,int userId);

    //归还图书
    void backBook(Borrow borrow);

    //查询借阅记录
    List<Borrow> getBorrowList(int userId);
}

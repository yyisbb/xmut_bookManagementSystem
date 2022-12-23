package wang.gravity.bookmanagementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import wang.gravity.bookmanagementsystem.pojo.Book;

@Mapper
public interface BookMapper {
    /**
     * 获取所有图书列表
     *
     * @return 图书集合
     */
    List<Book> getAllBook();

    List<Book> getBookByCategoryId(int categoryId);

    void deleteBooks(int[] ids);

    int addBook(Book book);

    Book getBookByBookNo(int bookNo);

    Book getBookByBookId(int bookId);

    //图书借阅
    void borrowBook(int bookNo,int bookBorrowNum,int bookQuantity);

    int  updateBookInfo(Book book);
}

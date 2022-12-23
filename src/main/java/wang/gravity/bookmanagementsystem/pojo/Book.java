package wang.gravity.bookmanagementsystem.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;
    private int bookNo;
    private String bookName;
    private String bookAuthor;
    private int bookQuantity;
    private int bookBorrowNum;
    private String bookCover;
    private int bookCategoryId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    private String categoryName;
    private String bookContent;
    private String bookDescription;
}

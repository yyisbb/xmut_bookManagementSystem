package wang.gravity.bookmanagementsystem.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    private int id;
    private int bookNo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date backTime;
    private int userId;
    private String bookName;
}

package wang.gravity.bookmanagementsystem.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notice {
    private int id;
    private String title;
    private String content;
    private String type;
    private int state;
    private String recipientId;
    private int managerId;
    private String managerName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
}

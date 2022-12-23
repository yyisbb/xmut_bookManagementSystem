package wang.gravity.bookmanagementsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Info {
    private int bookTotal;
    private int returnedBookNum;
    private int userTotal;
    private int categoryTotal;
}

package wang.gravity.bookmanagementsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


@SpringBootApplication
@MapperScan("wang.gravity.bookmanagementsystem.mapper")
public class BookManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookManagementSystemApplication.class, args);
    }

}

package wang.gravity.bookmanagementsystem.service;

import org.springframework.stereotype.Repository;

import java.util.List;

import wang.gravity.bookmanagementsystem.dto.PasswordForm;
import wang.gravity.bookmanagementsystem.pojo.User;


@Repository
public interface UserService {
    List<User> getAllUser();

    void initAdmin();

    String login(User user);

    User getUserByUserName(String username);

    String register(User user);

    void deleteUsers(int[] ids);

    String addStudent(User user);

    String resetPassword(PasswordForm passwordForm);

    String updatePassword(PasswordForm passwordForm);

    void disableUser(PasswordForm passwordForm);


}

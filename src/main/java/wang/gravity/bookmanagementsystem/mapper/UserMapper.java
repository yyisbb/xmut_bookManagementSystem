package wang.gravity.bookmanagementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import wang.gravity.bookmanagementsystem.pojo.User;


@Mapper
public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return List<User>
     */
    List<User> findAllUser();

    List<User> findAdmin();

    /**
     * 根据ID查询用户
     *
     * @return User
     */
    User findUserById(String id);

    /**
     * 登录接口
     *
     * @param user 用户的账号密码
     * @return token
     */
    User login(User user);

    /**
     * 根据用户名获取token
     *
     * @param username 用户名
     * @return 用户
     */
    User getUserByUserName(String username);

    /**
     * 用户查重
     *
     * @param username  用户名
     * @param studentId 学号
     * @return 用户
     */
    List<User> getUserByUserNameOrStudentId(String username, String studentId);

    /**
     * 用户注册接口
     *
     * @param user 传入一个用户对象
     * @return 注册成功返回ID
     */
    void register(User user);


    /**
     * 根据ID删除用户(批量)
     *
     * @param ids 多个用户的id
     */
    void deleteUsers(int[] ids);


    void resetPassword(String id, String newPassword);

    void disableUser(int id, int status);

    //新增图书借阅
    void addBorrowNum(int id,int borrowNum);

}

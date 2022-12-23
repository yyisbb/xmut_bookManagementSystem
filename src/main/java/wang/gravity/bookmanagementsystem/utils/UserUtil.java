package wang.gravity.bookmanagementsystem.utils;

import wang.gravity.bookmanagementsystem.pojo.User;

public class UserUtil {
    /**
     * 线程变量，存放user实体类信息，即使是静态的与其他线程也是隔离的
     */
    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    /**
     * 获取当前登录用户
     * @return
     */
    public static User getLoginUser() {
        return userThreadLocal.get();
    }

    public static void setLoginUser(User user) {
        userThreadLocal.set(user);
    }

    public static void removeUser(){
        userThreadLocal.remove();
    }
}

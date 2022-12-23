package wang.gravity.bookmanagementsystem.constant;

public class Check {
    public static final String PASSWORD_REGEX = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";
    public static final String STUDENT_ID_REGEX = "^[0-9]{6,18}$";
    public static final String USERNAME_REGEX = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    public static final String NICKNAME_REGEX = "^[\\u4e00-\\u9fa5]{0,}$";
}

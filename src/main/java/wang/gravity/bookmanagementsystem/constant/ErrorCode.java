package wang.gravity.bookmanagementsystem.constant;

public enum ErrorCode {
    //其他失败
    FAILED(-1, "失败"),
    //成功10000
    SUCCESS(10000, "成功"),
    //和TOKEN相关的20000
    TOKEN_NULL_ERROR(20001, "TOKEN校验失败,TOKEN为空"),
    TOKEN_USER_NOT_FOUND_ERROR(20002, "TOKEN校验失败,用户不存在"),
    PERMISSION_ERROR(20003, "权限不足"),
    //业务30000
    USERNAME_PASSWORD_ERROR(30000, "用户名或密码错误"),

    USER_NOT_FOUND_ERROR(30001, "用户不存在"),
    PASSWORD_FORMAT_ERROR(30002, "密码格式错误，密码必须是包含大写字母、小写字母、数字、特殊符号（不是字母，数字，下划线，汉字的字符）的8位以上组合。"),
    USERNAME_FORMAT_ERROR(30003, "用户名格式错误，用户名必须是QQ邮箱。"),
    NICKNAME_FORMAT_ERROR(30003, "姓名格式错误，用户名必须是纯中文。"),
    STUDENT_ID_FORMAT_ERROR(30004, "学号格式错误，学号必须是数字的6到18位以上组合。"),
    REGISTER_ERROR(30005, "注册失败"),
    PARAM_NULL_ERROR(30006, "参数不允许为空"),
    USER_EXIST_ERROR(30007, "用户已存在,请更换学号或用户名"),
    USER_STATUS_ERROR(30008, "用户状态异常,请联系管理员"),
    CATEGORY_NOT_FOUND_ERROR(30009, "删除分类异常,该分类不存在或该分类下存在图书"),
    NAME_FORMAT_ERROR(30010, "分类名格式错误，必须是纯中文。"),
    CATEGORY_EXIST_ERROR(30011, "分类已存在,请更换分类名"),
    UPLOAD_ERROR(30011, "程序错误，请重新上传文件"),
    DB_ERROR(30012, "数据库异常"),
    BOOK_NOT_FOUND_ERROR(30013, "图书不存在"),
    BORROW_EXIST_ERROR(30014, "无法重复借阅图书"),
    BORROW_NUM_EXCEED_LIMIT_ERROR(30015, "借阅次数超过限制"),
    BORROW_NOT_FOUND_ERROR(30016, "查无此书的借阅记录,或已归还"),
    QUANTITY_EMPTY_ERROR(30017, "该图书库存不足,请联系管理员新增"),
    COVER_PARAM_NULL_ERROR(30018, "图书封面未上传"),
    BOOK_EXIST_ERROR(30015, "图书已存在"),
    ;

    private final int code;
    private final String message;

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }
}

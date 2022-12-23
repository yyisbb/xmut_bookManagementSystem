package wang.gravity.bookmanagementsystem.constant;

public enum UserLogConst {
    LOGIN("登录"),
    RESET_PASSWORD("重设密码"),
    UPDATE_PASSWORD("修改密码"),
    DISABLE_USER("停用账户"),
    ADD_USER("新增账户"),
    DELETE_USER("删除账户"),
    FILE_UPLOAD("文件上传"),
    UPDATE_CATEGORY("修改分类"),
    ADD_CATEGORY("新增分类"),
    DELETE_CATEGORY("删除分类"),
    BORROW_BOOK("借阅"),
    BACK_BOOK("还书"),
    ADD_BOOK("新增图书"),
    DELETE_BOOK("删除图书"),
    UPDATE_BOOK("修改图书"),
    SEND_NOTICE("发布通知"),
    ;

    private final String message;

    private UserLogConst(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}


package wang.gravity.bookmanagementsystem.exception;

import wang.gravity.bookmanagementsystem.constant.ErrorCode;


public class MyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected int errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public MyException() {
        super();
    }

    public MyException(ErrorCode error) {
        super(error.getMessage());
        this.errorCode = error.getCode();
        this.errorMsg = error.getMessage();
    }

    public MyException(ErrorCode error, Throwable cause) {
        super(error.getMessage(), cause);
        this.errorCode = error.getCode();
        this.errorMsg = error.getMessage();
    }

    public MyException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public MyException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public MyException(int errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public int getErrorCode() {
        return errorCode;
    }


    public String getErrorMsg() {
        return errorMsg;
    }
}

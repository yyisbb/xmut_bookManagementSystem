package wang.gravity.bookmanagementsystem.utils;
import lombok.Data;
import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.constant.UserLogConst;

@Data
public class ResultUtil<T> {

    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    private ResultUtil() {
    }

    public static <T> ResultUtil<T> success() {
        ResultUtil<T> resultUtil = new ResultUtil<>();
        resultUtil.setCode(ErrorCode.SUCCESS.getCode());
        resultUtil.setMessage(ErrorCode.SUCCESS.getMessage());
        resultUtil.setData((T) ErrorCode.SUCCESS.getMessage());
        return resultUtil;
    }

    public static <T> ResultUtil<T> success(T data) {
        ResultUtil<T> resultUtil = success();
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> ResultUtil<T> success(String message, T data) {
        ResultUtil<T> resultUtil = success();
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> ResultUtil<T> success(Integer code, String message, T data) {
        ResultUtil<T> resultUtil = new ResultUtil<>();
        resultUtil.setCode(code);
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> ResultUtil<T> fail() {
        ResultUtil<T> resultUtil = new ResultUtil<>();
        resultUtil.setCode(ErrorCode.FAILED.getCode());
        resultUtil.setMessage(ErrorCode.FAILED.getMessage());
        return resultUtil;
    }

    public static <T> ResultUtil<T> fail(T data) {
        ResultUtil<T> resultUtil = fail();
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> ResultUtil<T> fail(String message, T data) {
        ResultUtil<T> resultUtil = fail();
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> ResultUtil<T> fail(Integer code, String message) {
        ResultUtil<T> resultUtil = fail();
        resultUtil.setCode(code);
        resultUtil.setMessage(message);
        return resultUtil;
    }

    public static <T> ResultUtil<T> fail(Integer code, String message, T data) {
        ResultUtil<T> resultUtil = new ResultUtil<>();
        resultUtil.setCode(code);
        resultUtil.setMessage(message);
        resultUtil.setData(data);
        return resultUtil;
    }
}
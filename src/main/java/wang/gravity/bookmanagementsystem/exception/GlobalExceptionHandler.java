package wang.gravity.bookmanagementsystem.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import wang.gravity.bookmanagementsystem.utils.ResultUtil;

@ControllerAdvice
public class GlobalExceptionHandler<E> {

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ResultUtil<String> exceptionHandler(HttpServletRequest req, MyException e) {
        return ResultUtil.fail(e.getErrorCode(), e.getMessage());
    }
}

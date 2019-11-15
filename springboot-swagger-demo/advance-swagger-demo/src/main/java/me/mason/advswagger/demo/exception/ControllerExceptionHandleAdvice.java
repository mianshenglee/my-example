package me.mason.advswagger.demo.exception;

import lombok.extern.slf4j.Slf4j;
import me.mason.advswagger.demo.vo.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理
 *
 * @author mason
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandleAdvice {
    /**
     * UniRuntimeException处理
     * @param req 请求
     * @param res 响应
     * @param e 异常对象
     * @return
     */
    @ExceptionHandler(value={UniRuntimeException.class})
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handleException(HttpServletRequest req, HttpServletResponse res, UniRuntimeException e){
        log.error("出错信息",e);
        return ResponseResult.err(e);
    }

    /**
     * 未定义的Exception处理
     * @param req 请求
     * @param res 响应
     * @param e 异常对象
     * @return
     */
    @ExceptionHandler(value={Exception.class})
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handleException(HttpServletRequest req, HttpServletResponse res, Exception e){
        log.error("出错信息",e);
        return ResponseResult.err(UniExceptionEnums.SYSTEM_ERROR,e);
    }
}

package online.heycm.auth.config.exception;

import lombok.extern.slf4j.Slf4j;
import online.heycm.utils.exception.ServiceException;
import online.heycm.utils.result.ApiResult;
import online.heycm.utils.result.Result;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * @Description 全局异常处理
 * @Author heycm@qq.com
 * @Date 2020-07-08 14:59
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 通用异常处理
    @ExceptionHandler(Exception.class)
    public ApiResult error(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("[开始][通用异常处理][API: {}][Method: {}][结束]", request.getRequestURI(), request.getMethod(), e);
        response.setStatus(500);
        return Result.error("系统异常");
    }

    // 自定义异常处理
    @ExceptionHandler(ServiceException.class)
    public ApiResult error(ServiceException e, HttpServletRequest request, HttpServletResponse response) {
        log.error("[开始][自定义异常处理][API: {}][Method: {}][结束]", request.getRequestURI(), request.getMethod(), e);
        return Result.error(e.getMessage());
    }

    // 使用 json 请求体调用接口，校验异常抛出 MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult error(MethodArgumentNotValidException e, HttpServletRequest request, HttpServletResponse response) {
        log.error("[开始][参数校验异常处理][类型: JSON][API: {}][Method: {}][结束]", request.getRequestURI(), request.getMethod(), e);
        return Result.error(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    // 使用form data方式调用接口，校验异常抛出 BindException
    @ExceptionHandler(BindException.class)
    public ApiResult error(BindException e, HttpServletRequest request, HttpServletResponse response) {
        log.error("[开始][参数校验异常处理][类型: FormData][API: {}][Method: {}][结束]", request.getRequestURI(), request.getMethod(), e);
        return Result.error(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    // 处理单个参数校验失败抛出的异常
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult error(ConstraintViolationException e, HttpServletRequest request, HttpServletResponse response) {
        log.error("[开始][参数校验异常处理][类型: 单个参数][API: {}][Method: {}][结束]", request.getRequestURI(), request.getMethod(), e);
        return Result.error(e.getMessage());
    }

}

package com.like4u.server.exception;
import com.like4u.server.infrastructrue.comon.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {UserPasswordNotMatchException.class})
    public AjaxResult handleUserPasswordNotMatchException(UserPasswordNotMatchException e){
        if (log.isErrorEnabled()) {
            log.warn(e.getMessage(), e);
        }
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception ex) {

        return AjaxResult.error(ex.getMessage());
    }
    @ExceptionHandler(value = {Exception401.class})
    public AjaxResult handleException401(Exception401 e){
        return AjaxResult.error(401,e.getMessage());
    }
}
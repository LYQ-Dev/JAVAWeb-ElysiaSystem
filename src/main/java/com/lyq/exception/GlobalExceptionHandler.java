package com.lyq.exception;

import com.lyq.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//- 定义全局异常处理器非常简单，就是定义一个类，在类上加上一个注解@RestControllerAdvice，
//加上这个注解就代表我们定义了一个全局异常处理器。
//- 在全局异常处理器当中，需要定义一个方法来捕获异常，在这个方法上需要加上注解@ExceptionHandler。
//通过@ExceptionHandler注解当中的value属性来指定我们要捕获的是哪一类型的异常。
@RestControllerAdvice
public class GlobalExceptionHandler {
    //controller里面的异常异常会再往上抛，抛给全局异常处理器
    //处理异常
    @ExceptionHandler
    public Result ex(Exception e){//方法形参中指定能够处理的异常类型
        e.printStackTrace();//打印堆栈中的异常信息
        //捕获到异常之后，响应一个标准的Result
        return Result.error("对不起,操作失败,请联系管理员");
    }
    
}
//package com.example.blog_app.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.Date;
//import java.util.LinkedHashMap;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//  @ExceptionHandler({MethodArgumentNotValidException.class})
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  public ErrorResponse handleValidationException(MethodArgumentNotValidException e, WebRequest request){
//    System.out.println("========> handleValidationException");
//    ErrorResponse errorResponse = new ErrorResponse(
//            new Date(),
//            HttpStatus.BAD_REQUEST.value(),
//            request.getDescription(false).replace("uri=", ""),
//            "Payload validation failed",
//            "Dữ liệu đầu vào không hợp lệ",
//            new LinkedHashMap<>()
//            );
//    e.getBindingResult().getFieldErrors().forEach(error ->{
//      errorResponse.getDetails().put(error.getField(),error.getDefaultMessage());
//    });
//    return errorResponse;
//  }
//}

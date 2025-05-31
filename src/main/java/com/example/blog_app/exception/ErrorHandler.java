//package com.example.blog_app.exception;
//
//import jakarta.validation.ConstraintViolationException;
//import jakarta.validation.UnexpectedTypeException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class ErrorHandler extends ResponseEntityExceptionHandler {
//  @Override
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(
//          MethodArgumentNotValidException ex,
//          HttpHeaders headers,
//          HttpStatusCode status,
//          WebRequest request) {
//    var message ="Du lieu khong hop le";
//    var details = new LinkedHashMap<String,String>();
//
//    for(var error : ex.getFieldErrors()){
//      var key = error.getField();
//      var value = error.getDefaultMessage();
//      details.put(key,value);
//    }
//    var errorResponse = new ErrorResponse1(message, details);
//    return new ResponseEntity<>(errorResponse,headers,status);
//  }
//
//
//  @ExceptionHandler({ConstraintViolationException.class})
//  public ResponseEntity<Object> ConstraintViolationException(
//          ConstraintViolationException exception){
//    var message = "du lieu khong hop le";
//    var details = new LinkedHashMap<String,String>();
//    for(var error : exception.getConstraintViolations()){
//      var key = error.getPropertyPath().toString();
//      var value = error.getMessage();
//      details.put(key,value);
//    }
//    var errorResponse = new ErrorResponse1(message,details);
//    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//  }
//
//  @ExceptionHandler({UnexpectedTypeException.class})
//  public ResponseEntity<Object> UnexpectedTypeException(
//          UnexpectedTypeException exception
//  ){
//    Map<String, String> details = new LinkedHashMap<>();
//    details.put("error", "Dữ liệu không hợp lệ");
//    details.put("message", exception.getMessage()); // Thêm thông tin từ exception
//    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
//  }
//}

package com.example.blog_app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashMap;

@Getter
@Setter
public class ErrorResponse {
  private Date timestamp;

  private int status;

  private String path;
  //  loi la gi nhu the nao
  private String error;
  //  loi o dau va nhu the nao
  private String message;

//  public ErrorResponse(LinkedHashMap<String, String> details, String message) {
//  }
//  private Map<String,String> details;

//  public ErrorResponse(Map<String,String> details,String message){
//    this.timestamp = LocalDateTime.now().toString();
//    this.details= details;
//    this.message = message;
//  }
}

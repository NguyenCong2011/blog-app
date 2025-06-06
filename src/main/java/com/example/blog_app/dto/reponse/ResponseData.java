package com.example.blog_app.dto.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseData<T> {
  private final int status;
  private final String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  //  DELETE, PUT,PATCH
  public ResponseData(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public ResponseData(int status, String message, T data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }
}

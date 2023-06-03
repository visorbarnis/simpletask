package com.example.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class RecordNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(RecordNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String contractNotFoundHandler(RecordNotFoundException ex) {
    return ex.getMessage();
  }
}
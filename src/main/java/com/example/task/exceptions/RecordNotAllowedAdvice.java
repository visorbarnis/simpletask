package com.example.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class RecordNotAllowedAdvice {

  @ResponseBody
  @ExceptionHandler(RecordNotAllowedException.class)
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  String contractNotFoundHandler(RecordNotAllowedException ex) {
    return ex.getMessage();
  }
}
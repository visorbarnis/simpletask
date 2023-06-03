package com.example.task.exceptions;

public class RecordNotAllowedException extends RuntimeException {

  public RecordNotAllowedException(Long id) {
    super("Record " + id+ " not allowed to change. Probably you try to change status t opending through the API request. ");
  }

  public RecordNotAllowedException(Integer id) {
    super("Could not change record " + id);
  }

  public RecordNotAllowedException(String message) {
    super(message);
  }


}
package com.example.task.exceptions;

public class RecordNotFoundException extends RuntimeException {

  public RecordNotFoundException(Long id) {
    super("Could not find record " + id);
  }

  public RecordNotFoundException(Integer id) {
    super("Could not find record " + id);
  }

  public RecordNotFoundException(String recordNumber) {
    super("Could not find record with number " + recordNumber);
  }


}
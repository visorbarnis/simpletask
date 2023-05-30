package com.example.task.services;

import com.example.task.domains.RecordTodo;
import com.example.task.domains.dtos.RecordDto;

import java.util.List;

public interface RecordsService {

    List<RecordDto> findAllDtos();

    RecordTodo add(RecordTodo recordTodo);

}

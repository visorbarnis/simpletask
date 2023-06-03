package com.example.task.services;

import com.example.task.domains.dtos.RecordDto;

import java.util.List;
import java.util.Optional;

public interface RecordsService {

    List<RecordDto> findAllDtos();

    Optional<RecordDto> findDtoById(int id);

    Optional<List<RecordDto>> findDtoByName(String recordnName);

    Optional<List<RecordDto>> findDtoByNotDoneStatus();

    RecordDto add(RecordDto recordDto);

    Optional<RecordDto> update(RecordDto recordDto);


    void deleteById(int recordId);

    void deleteAll();


}

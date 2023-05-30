package com.example.task.services;


import com.example.task.domains.RecordTodo;
import com.example.task.domains.dtos.RecordDto;
import com.example.task.repositories.RecordRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class RecordServiceImpl implements RecordsService {


    @Autowired
    RecordRepository recordRepository;


    @Override
    public List<RecordDto> findAllDtos() {

        return recordRepository.findAll()
                .stream().map(RecordDto::new
                ).collect(Collectors.toList());
    }


    @Override
    public RecordTodo add(RecordTodo recordTodo) {
        return recordRepository.save(recordTodo);
    }



}

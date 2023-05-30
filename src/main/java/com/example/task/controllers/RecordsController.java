package com.example.task.controllers;


import com.example.task.domains.RecordTodo;
import com.example.task.domains.dtos.RecordDto;
import com.example.task.services.RecordsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Provide API for records crud operations.
 */

@RestController
@RequestMapping("/records")
@Log4j2
public class RecordsController {

    @Autowired
    RecordsService recordsService;


    @GetMapping()
    public List<RecordDto> findAll() {

        RecordTodo recordTodo = new RecordTodo();
        recordTodo.setId(1L);
        recordTodo.setDescription("dedscreiption");
        recordTodo.setName("name");
        recordsService.add(recordTodo);

        return recordsService.findAllDtos();
    }






}

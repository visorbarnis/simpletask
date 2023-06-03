package com.example.task.controllers;


import com.example.task.domains.dtos.RecordDto;
import com.example.task.exceptions.RecordNotFoundException;
import com.example.task.services.RecordsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @Operation(summary = "Find all ToDo records")
    @GetMapping()
    public List<RecordDto> findAll() {
        return recordsService.findAllDtos();
    }


    @GetMapping("find")
    public List<RecordDto> findRecordByName(@RequestParam("record_name") String recordName) {
        return recordsService.findDtoByName(recordName).orElseThrow(() -> new RecordNotFoundException(recordName));
    }

    @GetMapping("find_not_done")
    public List<RecordDto> findRecordByNotDoneStatus() {
        return recordsService.findDtoByNotDoneStatus().orElseThrow(() -> new RecordNotFoundException("No records with not done"));
    }


    @Operation(summary = "Add new record into the todo list")
    @PostMapping()
    public RecordDto createRecord(@RequestBody RecordDto recordDto) {
        return recordsService.add(recordDto);
    }

    @GetMapping("{id}")
    public RecordDto findRecordById(@PathVariable int id) {
        return recordsService.findDtoById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Operation(summary = "Update record with a new data")
    @PutMapping("{id}")
    public RecordDto updateRecord(@RequestBody RecordDto recordDto, @PathVariable int id) {
        return recordsService.update(recordDto)
                .orElseThrow(() -> new RecordNotFoundException(recordDto.getId()));
    }

    @Operation(summary = "Delete record by ID")
    @DeleteMapping("/{id}")
    void deleteCustomer(@PathVariable int id) {
        recordsService.deleteById(id);
    }

    @Operation(summary = "Delete all records")
    @DeleteMapping()
    void deleteAllCustomer() {
        recordsService.deleteAll();
    }


}

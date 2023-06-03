package com.example.task.generators;


import com.example.task.domains.RecordTodo;
import com.example.task.domains.enums.RecordStatus;
import com.example.task.repositories.RecordRepository;
import com.example.task.services.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Used to generate some instances in database, to use it in tests
 */

@Service
public class GenerateRecordService {


    private final List<RecordTodo> generatedTodoList = new ArrayList<>();

    @Autowired
    RecordsService recordsService;

    @Autowired
    RecordRepository recordRepository;

    /**
     * Return non persisted record
     *
     */
    public RecordTodo getNewRecordTodoInstance() {
        final RecordTodo recordTodo = new RecordTodo();
        fillRandomsValues(recordTodo);
        generatedTodoList.add(recordTodo);
        return recordTodo;
    }

    /**
     * Return persisted contract
     *
     */
    public RecordTodo getNewRecordTodoPersistent() {
        final RecordTodo recordTodo = new RecordTodo();
        fillRandomsValues(recordTodo);
        generatedTodoList.add(recordTodo);
        return recordRepository.save(recordTodo);
    }


    public void cleanGeneratedInstances() {
        recordRepository.deleteAll(generatedTodoList);
    }

    private void fillRandomsValues(RecordTodo recordTodo) {
        Random rand = new Random();
        int randNumber = rand.nextInt(10000) + 1;
        int randNumberPercent = rand.nextInt(100) + 1;
        recordTodo.setStatus(RecordStatus.NOT_DONE);
        recordTodo.setName("name_"+randNumber);
        recordTodo.setDescription("description_"+randNumber);
        recordTodo.setDueDate(LocalDateTime.now());
        recordTodo.setMarkDate(LocalDateTime.now());
        recordTodo.setCreationDate(LocalDateTime.now());
    }

}

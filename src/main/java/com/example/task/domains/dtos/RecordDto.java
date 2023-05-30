package com.example.task.domains.dtos;

import com.example.task.domains.RecordTodo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordDto {

    private Long id;
    private String name;
    private String description;
    private LocalDate triggerDate;

    public RecordDto(RecordTodo recordTodo){
        this.id= recordTodo.getId();
        this.name = recordTodo.getName();
        this.description= recordTodo.getDescription();
        this.triggerDate = recordTodo.getTriggerDate();
    }



}
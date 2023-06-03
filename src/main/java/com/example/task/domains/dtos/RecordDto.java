package com.example.task.domains.dtos;

import com.example.task.domains.RecordTodo;
import com.example.task.domains.enums.RecordStatus;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class RecordDto {

    public final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Long id;
    private String name;
    private String description;
    private LocalDateTime dueDate;
    private LocalDateTime markDate;
    private LocalDateTime creationDate;
    private RecordStatus status;

    public RecordDto(){}

    public RecordDto(RecordTodo recordTodo){
        this.id= recordTodo.getId();
        this.name = recordTodo.getName();
        this.description= recordTodo.getDescription();
        this.dueDate = recordTodo.getDueDate();
        this.markDate = recordTodo.getMarkDate();
        this.creationDate=recordTodo.getCreationDate();
        this.status=recordTodo.getStatus();
    }

}
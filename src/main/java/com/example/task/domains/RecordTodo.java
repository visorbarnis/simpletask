package com.example.task.domains;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "records")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class RecordTodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "trigger_date")
    private LocalDate triggerDate;

}
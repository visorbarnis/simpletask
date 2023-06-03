package com.example.task.domains;

import com.example.task.domains.enums.RecordStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(name = "du_date")
    private LocalDateTime dueDate;

    @Column(name = "mark_date")
    private LocalDateTime markDate;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RecordStatus status;

}


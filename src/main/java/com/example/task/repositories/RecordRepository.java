package com.example.task.repositories;

import com.example.task.domains.RecordTodo;
import com.example.task.domains.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<RecordTodo, Integer> {

    Optional<RecordTodo> findById(Long id);
    Optional<List<RecordTodo>> findByName(String name);
    Optional<List<RecordTodo>> findByStatus(RecordStatus status);

}
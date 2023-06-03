package com.example.task.services;

import com.example.task.domains.RecordTodo;
import com.example.task.domains.dtos.RecordDto;
import com.example.task.domains.enums.RecordStatus;
import com.example.task.exceptions.RecordNotAllowedException;
import com.example.task.repositories.RecordRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class RecordServiceImpl implements RecordsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int UPDATE_RATE_MLS = 1000;
    private static final String NOT_ALLOWED_MESSAGE = "Record not allowed to change. Probably you try to change " +
            " status to \"pending\"  through the API request.";

    @Autowired
    RecordRepository recordRepository;

    @Override
    public List<RecordDto> findAllDtos() {

        return recordRepository.findAll()
                .stream().map(RecordDto::new
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<RecordDto> findDtoById(int id) {
        return recordRepository.findById(id).map(RecordDto::new)
                .or(Optional::empty);
    }

    @Override
    public Optional<List<RecordDto>> findDtoByName(String recordnName) {
        Optional<List<RecordTodo>> records = recordRepository.findByName(recordnName);
        return records.map(recordTodos -> recordTodos.stream().map(RecordDto::new).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<RecordDto>> findDtoByNotDoneStatus() {
        Optional<List<RecordTodo>> records = recordRepository.findByStatus(RecordStatus.NOT_DONE);
        return records.map(recordTodos -> recordTodos.stream().map(RecordDto::new).collect(Collectors.toList()));
    }

    @Override
    public RecordDto add(RecordDto recordDto) {

        if (isNotAllowedToChange(recordDto)) {
            throw new RecordNotAllowedException(NOT_ALLOWED_MESSAGE);
        }

        RecordTodo recordTodo = new RecordTodo(
                recordDto.getId(),
                recordDto.getName(),
                recordDto.getDescription(),
                recordDto.getDueDate(),
                recordDto.getMarkDate(),
                recordDto.getCreationDate(),
                recordDto.getStatus()
        );

        recordTodo = recordRepository.save(recordTodo);
        recordDto.setId(recordTodo.getId());
        return recordDto;
    }

    @Override
    @Transactional
    public Optional<RecordDto> update(RecordDto recordDto) {

        if (isNotAllowedToChange(recordDto)) {
            throw new RecordNotAllowedException(NOT_ALLOWED_MESSAGE);
        }

        return recordRepository.findById(recordDto.getId())
                .map(recordItem -> {
                    recordItem.setName(recordDto.getName());
                    recordItem.setDescription(recordDto.getDescription());
                    recordItem.setDueDate(recordDto.getDueDate());
                    recordItem.setMarkDate(recordDto.getMarkDate());
                    recordItem.setCreationDate(recordDto.getCreationDate());
                    recordItem.setStatus(recordDto.getStatus());
                    recordRepository.saveAndFlush(recordItem);
                    return recordDto;
                });
    }


    private boolean isNotAllowedToChange(RecordDto recordDto) {
        Optional<RecordTodo> record = recordRepository.findById(recordDto.getId());

        if(record.isEmpty()){
            return false;
        }

        if(record.get().getStatus().equals(RecordStatus.PAST_DUE)){
            return true;
        }
        return false;
    }


    @Override
    @Transactional
    public void deleteById(int recordId) {
        recordRepository.deleteById(recordId);
    }

    @Override
    @Transactional
    public void deleteAll() {
        recordRepository.deleteAll(recordRepository.findAll());
    }

    @Scheduled(fixedRate = UPDATE_RATE_MLS)
    public void updateStatuses() {

        Optional<List<RecordTodo>> records = recordRepository.findByStatus(RecordStatus.NOT_DONE);

        if (records.isEmpty()) {
            return;
        }
        records.get().forEach(record -> {
            LocalDateTime lt = LocalDateTime.now();
            if (record.getDueDate().isBefore(lt)) {
                record.setStatus(RecordStatus.PAST_DUE);
                recordRepository.save(record);
            }
        });
    }


}

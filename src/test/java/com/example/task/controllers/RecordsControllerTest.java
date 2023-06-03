package com.example.task.controllers;

import com.example.task.configurations.BaseSpringContainer;
import com.example.task.domains.RecordTodo;
import com.example.task.generators.GenerateRecordService;
import com.example.task.services.RecordsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RecordsControllerTest extends BaseSpringContainer {


    @Autowired
    GenerateRecordService generateRecordService;

    @Autowired
    RecordsService recordsService;


    @Test
    void notFindAll() throws Exception {
        this.mockMvc.perform(get("/asdasdasdasd"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_empty() throws Exception {

        recordsService.deleteAll();
        this.mockMvc.perform(get("/records"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void findAll_full_list() throws Exception {

        generateRecordService.getNewRecordTodoPersistent();
        generateRecordService.getNewRecordTodoPersistent();
        MvcResult result = this.mockMvc.perform(get("/records"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resStr = result.getResponse().getContentAsString();
        Assertions.assertTrue(resStr.contains("dueDate"));
    }

    @Test
    void findRecordById() throws Exception {

        RecordTodo recordTodo = generateRecordService.getNewRecordTodoPersistent();

        MvcResult result = this.mockMvc.perform(get("/records/" + recordTodo.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resStr = result.getResponse().getContentAsString();
        Assertions.assertTrue(resStr.contains("dueDate"));
        Assertions.assertTrue(resStr.contains(recordTodo.getName()));
    }

    @Test
    void getRecordTodosList() throws Exception {

        RecordTodo  recordTodo = generateRecordService.getNewRecordTodoPersistent();

        MvcResult result = this.mockMvc.perform(get("/records"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resStr = result.getResponse().getContentAsString();
        Assertions.assertTrue(resStr.contains("dueDate"));
        Assertions.assertTrue(resStr.contains(recordTodo.getName()));
    }

    @Test
    void findRecordTodoByName() throws Exception {

        RecordTodo recordTodo = generateRecordService.getNewRecordTodoPersistent();
        MvcResult result = this.mockMvc.
                perform(get("/records/find?record_name=" + recordTodo.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resStr = result.getResponse().getContentAsString();
        Assertions.assertTrue(resStr.contains("dueDate"));
        Assertions.assertTrue(resStr.contains(recordTodo.getName()));

    }

    @AfterEach
    private void clean() {
        generateRecordService.cleanGeneratedInstances();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
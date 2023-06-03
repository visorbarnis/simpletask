package com.example.task.suits;


import com.example.task.controllers.RecordsControllerTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Task Test Suite")
@SelectClasses({
        RecordsControllerTest.class})
public class SmokeSuitTest {

}

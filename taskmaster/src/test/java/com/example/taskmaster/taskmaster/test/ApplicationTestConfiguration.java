package com.example.taskmaster.taskmaster.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.example.taskmaster.taskmaster"})
@PropertySource("classpath:test.properties")
public class ApplicationTestConfiguration {

}

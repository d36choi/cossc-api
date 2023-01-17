package com.api.cossc.service;

import com.api.cossc.domain.QuizEntity;
import com.api.cossc.repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@Sql("/db/cossc/data.sql")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Testcontainers
class QuizServiceTest {
    @Autowired
    public QuizRepository quizRepository;

    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("cossc")
            .withUsername("root")
            .withPassword("root");
//            .withInitScript("db/cossc/data.sql");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        System.out.println("mysql.getJdbcUrl() = " + mysql.getJdbcUrl());
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Test
    public void test() {
        List<QuizEntity> list = quizRepository.findAll();

        list.forEach(System.out::println);
    }
}
package com.api.cossc.service;

import com.api.cossc.domain.QuizEntity;
import com.api.cossc.dto.DailyQuizRequest;
import com.api.cossc.dto.DailyQuizResponse;
import com.api.cossc.dto.QuizResponse;
import com.api.cossc.repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

//@Sql("/db/cossc/data.sql")
@SpringBootTest
//@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Testcontainers
class QuizServiceTest {
    @Autowired
    public QuizRepository quizRepository;

    @Autowired
    public QuizServiceImpl quizService;

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

        DailyQuizRequest dailyQuizRequest = new DailyQuizRequest();
        dailyQuizRequest.setTagId(1L);
        dailyQuizRequest.setUserId(1L);
        DailyQuizResponse dailyQuiz = quizService.getDailyQuiz(dailyQuizRequest);
        for (QuizResponse response : dailyQuiz.getQuizResponses()) {
            System.out.println("response = " + response);
        }
    }
}
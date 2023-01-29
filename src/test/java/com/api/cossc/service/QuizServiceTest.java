package com.api.cossc.service;

import com.api.cossc.domain.QuizType;
import com.api.cossc.domain.TagEntity;
import com.api.cossc.dto.quiz.DailyQuizRequest;
import com.api.cossc.dto.quiz.QuizCreationRequest;
import com.api.cossc.dto.quiz.QuizCreationResponse;
import com.api.cossc.repository.QuizRepository;
import com.api.cossc.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
//@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Testcontainers
class QuizServiceTest {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizServiceImpl quizService;

    @Autowired
    private TagRepository tagRepository;

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

    @BeforeEach
    public void flush() {
        quizRepository.deleteAll();
    }

    @DisplayName("quiz가 부족할때 예외를 던진다")
    @Test
    public void test() {
        DailyQuizRequest dailyQuizRequest = new DailyQuizRequest();
        dailyQuizRequest.setTagId(1L);
        dailyQuizRequest.setUserId(1L);
        Throwable throwable = catchThrowable(() -> quizService.getDailyQuiz(dailyQuizRequest));

        assertThat(throwable).isInstanceOf(Exception.class).hasMessageContaining("부족");
    }

    @DisplayName("quiz를 저장할 수 있다")
    @Test
    public void should_create() {
        tagRepository.save(TagEntity.of(1L, "python"));
        tagRepository.save(TagEntity.of(2L, "python2"));
        tagRepository.save(TagEntity.of(3L, "python3"));

        QuizCreationRequest quizCreationRequest = QuizCreationRequest.of(null, "test", "test", QuizType.OX, 1L);
        QuizCreationResponse quizCreationResponse = quizService.create(quizCreationRequest);


        assertThat(quizCreationResponse).isNotNull();
    }
}
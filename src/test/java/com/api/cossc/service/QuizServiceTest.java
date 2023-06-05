package com.api.cossc.service;

import com.api.cossc.ContainerInitialization;
import com.api.cossc.domain.QuizEntity;
import com.api.cossc.domain.QuizType;
import com.api.cossc.dto.quiz.QuizCreationRequest;
import com.api.cossc.dto.quiz.QuizCreationResponse;
import com.api.cossc.dto.quiz.QuizDeletionRequest;
import com.api.cossc.dto.quiz.QuizDeletionResponse;
import com.api.cossc.repository.QuizRepository;
import com.api.cossc.security.CustomUserDetails;
import com.api.cossc.service.quiz.QuizService;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Testcontainers
@Ignore
class QuizServiceTest extends ContainerInitialization {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizService quizService;

    @BeforeAll
    public static void setup(@Autowired DataSource dataSource) {
        try (Connection conn = dataSource.getConnection()) {
            // you'll have to make sure conn.autoCommit = true (default for e.g. H2)
            // e.g. url=jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1;MODE=MySQL
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("/db/cossc/test.sql"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @BeforeEach
    public void flush() {
        quizRepository.deleteAll();
    }

    @DisplayName("quiz가 부족할때 예외를 던진다")
    @Test
    public void should_throw_exception_if_dont_have_enough_quiz() {
        UserDetails userDetails = new CustomUserDetails(1L, "108717693410798874648", Collections.emptyList());
        Throwable throwable = catchThrowable(() -> quizService.getDailyQuiz(userDetails));

        assertThat(throwable).isInstanceOf(Exception.class).hasMessageContaining("부족");
    }

    @DisplayName("quiz를 저장할 수 있다")
    @Test
    public void should_create() {

        QuizCreationRequest quizCreationRequest = QuizCreationRequest.of(null, "test", "test", QuizType.OX, "test", "test", 1L);
        QuizCreationResponse quizCreationResponse = quizService.create(quizCreationRequest);


        assertThat(quizCreationResponse).isNotNull();
    }
    @DisplayName("quiz를 수정할 수 있다")
    @Test
    public void should_update() {
        //given

        QuizCreationRequest quizCreationRequest = QuizCreationRequest.of(null, "test", "test", QuizType.OX, "test", "test", 1L);
        QuizCreationResponse quizCreationResponse = quizService.create(quizCreationRequest);

        System.out.println("quizCreationResponse.getId() = " + quizCreationResponse.getId());

        //when
        String updated = "updated";
        QuizCreationRequest updateQuizCreationRequest = QuizCreationRequest.of(quizCreationResponse.getId(), updated, updated, QuizType.OX, "test", "test", 1L);

        quizService.create(updateQuizCreationRequest);

        //then
        QuizEntity shouldBeUpdated = quizRepository.findById(quizCreationResponse.getId()).orElseThrow(() -> new IllegalArgumentException("should have 1L"));

        assertThat(shouldBeUpdated.getId()).isEqualTo(quizCreationResponse.getId());
        assertThat(shouldBeUpdated.getTitle()).isEqualTo(updated);
        assertThat(shouldBeUpdated.getDescription()).isEqualTo(updated);
    }

    @DisplayName("quiz를 삭제할 수 있다")
    @Test
    public void should_delete() {
        //given
        QuizCreationRequest quizCreationRequest = QuizCreationRequest.of(null, "test", "test", QuizType.OX, "test", "test", 1L);
        QuizCreationResponse quizCreationResponse = quizService.create(quizCreationRequest);

        System.out.println("quizCreationResponse.getId() = " + quizCreationResponse.getId());

        //when
        QuizDeletionResponse quizDeletionResponse = quizService.delete(QuizDeletionRequest.of(quizCreationResponse.getId()));

        //then
        assertThat(quizRepository.findById(quizDeletionResponse.getId())).isSameAs(Optional.empty());
    }

    @DisplayName("daily quiz를 가져올 수 있다")
    @Test
    public void should_get_daily_quiz() {

        IntStream.range(0, 3).forEach(i -> {
            quizService.create(QuizCreationRequest.of(null, "test" + i, "test", QuizType.OX, "test", "test", 1L));
        });

        UserDetails userDetails = new CustomUserDetails(1L, "108717693410798874648", Collections.emptyList());
        assertThatCode(() -> quizService.getDailyQuiz(userDetails)).doesNotThrowAnyException();

    }

}
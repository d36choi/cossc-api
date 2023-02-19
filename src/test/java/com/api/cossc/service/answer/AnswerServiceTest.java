package com.api.cossc.service.answer;

import com.api.cossc.ContainerInitialization;
import com.api.cossc.domain.AnswerEntity;
import com.api.cossc.dto.answer.AnswerCreationRequest;
import com.api.cossc.dto.answer.AnswerCreationResponse;
import com.api.cossc.dto.answer.AnswerDeletionRequest;
import com.api.cossc.dto.answer.AnswerDeletionResponse;
import com.api.cossc.repository.answer.AnswerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Testcontainers
class AnswerServiceTest extends ContainerInitialization {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerService answerService;

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
        answerRepository.deleteAll();
    }

    @DisplayName("answer를 생성할 수 있다")
    @Test
    public void should_create_answer() throws Exception {

        AnswerCreationResponse answerCreationResponse = answerService.create(new AnswerCreationRequest("test", "test", "test"));

        AnswerEntity answerEntity = answerRepository.findById(answerCreationResponse.getId()).orElseThrow(Exception::new);

        assertThat(answerEntity.getText()).isEqualTo("test");
    }

    @DisplayName("answer를 제거할 수 있다")
    @Test
    public void should_delete_answer() throws Exception {

        AnswerCreationResponse answerCreationResponse = answerService.create(new AnswerCreationRequest("test", "test", "test"));

        AnswerDeletionResponse delete = answerService.delete(new AnswerDeletionRequest(answerCreationResponse.getId()));

        AnswerEntity answerEntity = answerRepository.findById(answerCreationResponse.getId()).orElse(null);

        assertThat(answerEntity).isNull();
    }


}
package jwp.dao;

import core.jdbc.ConnectionManager;
import jwp.model.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class QuestionDaoTest {
    @BeforeEach
    public void setup() {
        // SQL 스크립트를 읽어서 DB에 적용할 준비
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        // classpath 경로에 있는 jwp.sql파일을 추가해서,
        // 이 안에 있는 명령어들을 준비하기
        populator.addScript(new ClassPathResource("jwp.sql"));
        // 방금 준비한 populator을 이용해서
        // 실제 연결된 DB에 SQL을 실행하기
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void insertTest() {
        Question question = new Question("nykwon7777", "하이", "하이", LocalDateTime.of(2025, 4, 20, 15, 30, 0), 2);
    Assertions.assertDoesNotThrow(() -> QuestionDao.getInstance().insert(question));
    }

    @Test
    public void selectTest() {
        Question question = new Question("nykwon7777", "하이", "하이", LocalDateTime.of(2025, 4, 20, 15, 30, 0), 2);
        QuestionDao.getInstance().insert(question);

        Question findQuestion = Assertions.assertDoesNotThrow(() -> QuestionDao.getInstance().findByQuestionId(question.getQuestionId()));
        Assertions.assertEquals(findQuestion.getQuestionId(), question.getQuestionId());

    }
}
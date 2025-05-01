package jwp.model;

import core.jdbc.ConnectionManager;
import jwp.dao.QuestionDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.List;

class QuestionDaoTest {
    
    @BeforeEach
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void insertTest() {
        Question question = new Question("writer", "title", "contents");
        QuestionDao questionDao = new QuestionDao();
        Assertions.assertDoesNotThrow(() -> questionDao.insert(question));
    }

    @Test
    public void selectTest() {
        saveUser();
        QuestionDao questionDao = new QuestionDao();
        Question findQuestion = Assertions.assertDoesNotThrow(() -> questionDao.findByQuestionId(1L));
        Assertions.assertEquals(findQuestion.getQuestionId(), 1L);
    }

    @Test
    public void selectAllTest() {
        saveUser();
        QuestionDao questionDao = new QuestionDao();
        List<Question> findQuestions = Assertions.assertDoesNotThrow(() -> questionDao.findAll());

        Assertions.assertEquals(findQuestions.size(), 5);
    }

    private static void saveUser() {
        Question question = new Question("writer", "title", "contents");
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(question);
    }
}
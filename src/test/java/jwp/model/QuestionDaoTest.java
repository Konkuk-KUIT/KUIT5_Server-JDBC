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

public class QuestionDaoTest {
    @BeforeEach
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void selectAllTest() {

        QuestionDao questionDao = new QuestionDao();
        List<Question> findQuestions = Assertions.assertDoesNotThrow(() -> questionDao.findAll());

        Assertions.assertEquals(findQuestions.size(), 4);
    }
}

package jwp.model;


import core.jdbc.ConnectionManager;
import java.sql.SQLException;
import java.util.List;
import jwp.dao.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class UserDaoTest {

    User user = new User("kongoose", "password", "건구스", "Kongoose@email.com");

    @BeforeEach
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void insertTest() {
        User user = new User("kongoose", "password", "건구스", "Kongoose@email.com");
        UserDao userDao = new UserDao();
        Assertions.assertDoesNotThrow(() -> userDao.insert(user));
    }

    @Test
    public void selectTest() {
        saveUser();
        UserDao userDao = new UserDao();
        User findUser = Assertions.assertDoesNotThrow(() -> userDao.findByUserId(user.getUserId()));

        Assertions.assertEquals(findUser.getUserId(), user.getUserId());
    }

    @Test
    public void selectAllTest() {
        saveUser();
        UserDao userDao = new UserDao();
        List<User> findUsers = Assertions.assertDoesNotThrow(() -> userDao.findAll());

        Assertions.assertEquals(findUsers.size(), 2);
    }

    @Test
    public void updateTest() {
        saveUser();
        UserDao userDao = new UserDao();
        User updateUser = new User("kongoose", "updatePassword", "updateName", "update@email.com");

        Assertions.assertDoesNotThrow(() -> userDao.update(updateUser));
        User findUser = Assertions.assertDoesNotThrow(() -> userDao.findByUserId(updateUser.getUserId()));
        Assertions.assertEquals(findUser, updateUser);
    }

    private static void saveUser() {
        User user = new User("kongoose", "password", "name", "javajigi@email.com");
        UserDao userDao = new UserDao();
        try {
            userDao.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package jwp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jwp.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
        //TODO 구현 하기
    }

    public User findByUserId(String userId) throws SQLException {
        //TODO 구현 하기
        return null;
    }

    public void update(User user) throws SQLException {
        //TODO 구현 하기
    }

    public List<User> findAll() throws SQLException {
        //TODO 구현 하기
        return new ArrayList<>();
    }
}
package jwp.controller;

import core.mvc.Controller;
import java.sql.SQLException;
import java.util.Map;
import jwp.dao.UserDao;
import jwp.model.User;

public class CreateUserController implements Controller {
    private final UserDao userDao = new UserDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        User user = new User(params.get("userId"),
                params.get("password"),
                params.get("name"),
                params.get("email"));
        userDao.insert(user);
        return "redirect:/user/list";
    }
}
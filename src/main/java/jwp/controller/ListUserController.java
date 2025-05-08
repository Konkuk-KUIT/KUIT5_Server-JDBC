package jwp.controller;

import core.mvc.Controller;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpSession;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

public class ListUserController implements Controller {
    private final UserDao userDao = new UserDao();
    private HttpSession session;

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        if (UserSessionUtils.isLogined(session)) {
            model.put("users", userDao.findAll());
            return "/user/list.jsp";
        }
        return "redirect:/user/loginForm";
    }
}
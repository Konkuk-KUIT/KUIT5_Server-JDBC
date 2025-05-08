package jwp.controller;

import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.UserDao;
import jwp.model.User;

public class CreateUserController implements Controller {
    private final UserDao userDao = new UserDao();

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User(request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email"));
        userDao.insert(user);
        return new JspView("redirect:/user/list");
    }
}
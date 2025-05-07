package jwp.controller;

import core.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.UserDao;
import jwp.model.User;

public class UpdateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User modifiedUser = new User(
                req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        UserDao userDao = new UserDao();
        userDao.update(modifiedUser);
        return "redirect:/user/list";
    }
}

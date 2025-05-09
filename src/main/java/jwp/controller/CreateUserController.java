package jwp.controller;

import core.mvc.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.UserDao;
import jwp.model.User;

public class CreateUserController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User(request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email"));
        UserDao userDao = new UserDao();
        userDao.insert(user);
        return jspView("redirect:/user/list");
    }
}
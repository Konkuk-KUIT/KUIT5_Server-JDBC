package jwp.controller;

import core.mvc.AbstractController;
import core.mvc.view.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.UserDao;
import jwp.model.User;

public class CreateUserController extends AbstractController {
    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User(request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email"));
        userDao.insert(user);
        return jspView("redirect:/user/list");
    }
}
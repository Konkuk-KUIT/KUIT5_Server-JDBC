package jwp.controller;

import core.mvc.controller.AbstractController;
import core.mvc.view.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.UserDao;
import jwp.model.User;

public class UpdateUserController extends AbstractController {

    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User modifiedUser = new User(
                req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        userDao.update(modifiedUser);
        return jspView("redirect:/user/list");
    }
}

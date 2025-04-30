package jwp.controller;

import core.mvc.controller.AbstractController;
import core.mvc.view.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwp.dao.UserDao;
import jwp.model.User;

public class LoginController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User loginUser = new User(userId, password);
        UserDao userDao = new UserDao();
        User user = userDao.findByUserId(userId);

        if (user != null && user.isSameUser(loginUser)) {
            session.setAttribute("user", user);
            return jspView("redirect:/")
                    .addObject("user", user);
        }
        return jspView("redirect:/user/loginFailed");
    }
}

package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.*;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends AbstractController {
    private final UserDao userDao = new UserDao();
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User user = userDao.findByUserId(userId);

        if (user != null && user.matchPassword(password)) {
            session.setAttribute("user", user);
            return jspView("redirect:/").addObject("user", user);
        }
        return jspView("redirect:/user/loginFailed");
    }
}

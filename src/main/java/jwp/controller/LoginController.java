package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;

import core.mvc.ModelAndView;
import core.mvc.RedirectView;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User loginUser = new User(userId, password);
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user != null && user.isSameUser(loginUser)) {
            session.setAttribute("user", user);
            return new ModelAndView(String.valueOf(new RedirectView("/")));
        }
        return new ModelAndView(String.valueOf(new RedirectView("/user/loginFailed")));
    }
}

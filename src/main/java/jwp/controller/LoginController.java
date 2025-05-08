package jwp.controller;

import core.mvc.*;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        User loginUser = new User(userId, password);
        User user = UserDao.getInstance().findByUserId(userId);
//        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user != null && user.isSameUser(loginUser)) {
            session.setAttribute("user", user);
            return jspView("redirect:/");
        }
        return jspView("redirect:/user/loginFailed");
    }
}

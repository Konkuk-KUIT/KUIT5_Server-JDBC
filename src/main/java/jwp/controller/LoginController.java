package jwp.controller;

import core.mvc.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jwp.dao.UserDao;
import jwp.model.User;

public class LoginController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return jspView("redirect:/user/loginForm");
        }

        HttpSession session = request.getSession();
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        if (userId == null || password == null) {
            return jspView("redirect:/user/loginForm");
        }

        System.out.println(userId + " 로그인 입장");

        User loginUser = new User(userId, password);
        User user = new UserDao().findByUserId(userId);

        if (user != null && user.isSameUser(loginUser)) {
            session.setAttribute("user", user);
            System.out.println("로그인 성공");
            return jspView("redirect:/");
        }

        System.out.println("로그인 실패");
        return jspView("redirect:/user/loginFailed");
    }
}

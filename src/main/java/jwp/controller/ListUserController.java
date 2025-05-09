package jwp.controller;

import core.mvc.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

public class ListUserController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDao userDao = new UserDao();
        HttpSession session = request.getSession();
        if (UserSessionUtils.isLogined(session)) {
            request.setAttribute("users", userDao.findAll());
            return jspView("/user/list.jsp").addObject("users", userDao.findAll());
        }
        return jspView("redirect:/user/loginForm");
    }
}
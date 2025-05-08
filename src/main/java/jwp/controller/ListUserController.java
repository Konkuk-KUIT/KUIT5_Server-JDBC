package jwp.controller;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.View;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListUserController implements Controller {

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        UserDao userDao = new UserDao();
        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {
            req.setAttribute("users", userDao.findAll());
            return new JspView( "/user/list.jsp");
        }
        return new JspView("redirect:/user/loginForm");
    }
}
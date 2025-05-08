package jwp.controller;

import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

public class ListUserController implements Controller {
    private final UserDao userDao = new UserDao();

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        if (UserSessionUtils.isLogined(session)) {
            request.setAttribute("users", userDao.findAll());
            return new JspView("/user/list.jsp");
        }
        return new JspView("redirect:/user/loginForm");
    }
}
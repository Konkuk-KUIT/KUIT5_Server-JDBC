package jwp.controller;

import core.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

public class ListUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        UserDao userDao = new UserDao();
        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {
            req.setAttribute("users", userDao.findAll());
            return "/user/list.jsp";
        }
        return "redirect:/user/loginForm";
    }
}
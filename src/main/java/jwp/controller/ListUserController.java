package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwp.util.UserSessionUtils;

public class ListUserController implements Controller {

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {
            req.setAttribute("users", MemoryUserRepository.getInstance().findAll());
            return new JspView("redirect:/user/list");
            //return "/user/list.jsp";
        }
        return new JspView("redirect:/user/loginForm");
    }
}
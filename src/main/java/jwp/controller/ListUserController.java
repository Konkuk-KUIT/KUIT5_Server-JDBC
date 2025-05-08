package jwp.controller;

import core.mvc.AbstractController;
import core.mvc.view.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

public class ListUserController extends AbstractController {
    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        if (UserSessionUtils.isLogined(session)) {
            return jspView("/user/list.jsp")
                    .addObject("users", userDao.findAll());
        }
        return jspView("redirect:/user/loginForm");
    }
}
package jwp.controller;

import core.mvc.controller.AbstractController;
import core.mvc.view.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

public class ListUserController extends AbstractController {
    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {
            return jspView("redirect:/user/list")
                    .addObject("users", userDao.findAll());
        }
        return jspView("redirect:/user/loginForm");
    }
}
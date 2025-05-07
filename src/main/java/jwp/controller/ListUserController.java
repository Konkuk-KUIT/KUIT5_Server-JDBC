package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListUserController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        if (UserSessionUtils.isLogined(session)) {
            return jspView("/user/list.jsp")
                    .addObject("users", MemoryUserRepository.getInstance().findAll());
        }
        return jspView("redirect:/user/loginForm");
    }
}
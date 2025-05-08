package jwp.controller;

import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.mvc.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        return new ModelAndView(String.valueOf(new RedirectView("/")));
    }
}

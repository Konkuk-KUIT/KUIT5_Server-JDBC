package jwp.controller;

import core.mvc.Controller;
import core.mvc.RequestMapping;
import core.mvc.controller.AbstractController;
import core.mvc.view.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController extends AbstractController {

    @RequestMapping("/user/logout")
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        return jspView("redirect:/");
    }
}

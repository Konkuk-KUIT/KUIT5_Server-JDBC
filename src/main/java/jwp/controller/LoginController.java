package jwp.controller;

import core.mvc.Controller;
import core.mvc.RequestMapping;
import core.mvc.controller.AbstractController;
import core.mvc.view.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwp.dao.UserDao;
import jwp.model.User;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController extends AbstractController {
    private final UserDao userDao = new UserDao();

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User loginUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.isSameUser(loginUser)) {
            session.setAttribute("user", user);
            return jspView("redirect:/")
                    .addObject("user", user);
        }
        return jspView("redirect:/user/loginFailed");
    }

    @RequestMapping("/user/login")
    public ModelAndView getLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return jspView("redirect:/user/loginFailed");
    }
}

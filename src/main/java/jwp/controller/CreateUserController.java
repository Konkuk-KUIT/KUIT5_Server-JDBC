package jwp.controller;

import core.mvc.Controller;
import core.mvc.RequestMapping;
import core.mvc.controller.AbstractController;
import core.mvc.view.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.UserDao;
import jwp.model.User;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreateUserController extends AbstractController {
    private final UserDao userDao = new UserDao();

    @RequestMapping(value = "/user/signup", method = RequestMethod.POST)
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        userDao.insert(user);
        return jspView("redirect:/user/list");
    }
}
package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.controller.AbstractController;
import core.mvc.view.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.model.User;

public class UpdateUserController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User modifiedUser = new User(
                req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        MemoryUserRepository.getInstance().update(modifiedUser);
        return jspView("redirect:/user/list");
    }
}

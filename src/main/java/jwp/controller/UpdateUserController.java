package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User modifiedUser = new User(
                request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email"));
        MemoryUserRepository.getInstance().update(modifiedUser);
        return jspView("redirect:/user/list");
    }
}

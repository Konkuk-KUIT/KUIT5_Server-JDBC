package jwp.controller;

import core.mvc.*;
import jwp.dao.UserDao;
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
//        MemoryUserRepository.getInstance().update(modifiedUser);
        UserDao.getInstance().update(modifiedUser);
        return jspView("redirect:/user/list");
    }
}

package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User modifiedUser = new User(
                req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
//        MemoryUserRepository.getInstance().update(modifiedUser);
        new UserDao().update(modifiedUser);
        return "redirect:/user/list";
    }
}

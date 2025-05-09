package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.*;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        //MemoryUserRepository.getInstance().addUser(user);
        UserDao userDao = new UserDao();
        try {
            userDao.insert(user);
            System.out.println("user 회원가입 완료");
        } catch (Exception e) {
            System.out.println("회원가입 실패: " + e.getMessage());
            e.printStackTrace();
        }

        return jspView("redirect:/user/list");
    }
}
package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User(request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email"));
        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("user 회원가입 완료");
        return jspView("redirect:/user/list");
    }
}
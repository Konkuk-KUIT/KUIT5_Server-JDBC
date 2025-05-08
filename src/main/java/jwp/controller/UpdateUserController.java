package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.*;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User modifiedUser = new User(
                request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email"));
//        MemoryUserRepository.getInstance().update(modifiedUser);
        new UserDao().update(modifiedUser);
        // 세션에 있는 사용자 정보도 갱신
        HttpSession session = request.getSession();
        session.setAttribute("user", modifiedUser);

        return jspView( "redirect:/user/list");
    }
}
